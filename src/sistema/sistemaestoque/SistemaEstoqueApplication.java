package sistema.sistemaestoque;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import sistema.sistemaestoque.models.*;
import sistema.sistemaestoque.services.*;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "sistema.sistemaestoque.repository")
@EntityScan(basePackages = "sistema.sistemaestoque.models")
public class SistemaEstoqueApplication {

    public static void main(String[] args) {
        // Credenciais do Neo4j Aura
        System.setProperty("logging.level.org.springframework.data.neo4j", "ERROR");
        System.setProperty("spring.neo4j.uri", "neo4j+s://b66256f5.databases.neo4j.io");
        System.setProperty("spring.neo4j.authentication.username", "b66256f5");
        System.setProperty("spring.neo4j.authentication.password", "rbKcSnDl7j-mKaH87SWU3LPeb9J4oLy0zGL6KPLkurA");
        System.setProperty("spring.neo4j.database", "b66256f5");

        SpringApplication.run(SistemaEstoqueApplication.class, args);
    }

    @Bean
    CommandLineRunner menuInterativo(
            ProdutoService produtoService,
            UsuarioService usuarioService,
            MovimentacaoService movService,
            CategoriaService categoriaService,
            UnidadeMedidaService unidadeService) {

        return args -> {
            Scanner scanner = new Scanner(System.in);
            Usuario usuarioLogado = null;

            // --- LOGIN / CRIAÇÃO DE USUÁRIO ---
            while (usuarioLogado == null) {
                System.out.println("\n=== LOGIN SISTEMA ESTOQUE ===");
                System.out.print("Nome de usuário: ");
                String nomeBusca = scanner.nextLine();

                // Busca usuário no banco para lincar as movimentações futuras
                usuarioLogado = usuarioService.buscarPorNome(nomeBusca);

                if (usuarioLogado == null) {
                    System.out.print("Usuário não encontrado! Deseja criar '" + nomeBusca + "'? (s/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("s")) {
                        System.out.print("Digite um ID único para este usuário (ex: user01): ");
                        String idUser = scanner.nextLine();
                        // Cadastra o usuário e já o loga
                        usuarioLogado = usuarioService.cadastrar(new Usuario(idUser, "Operador", nomeBusca));
                        System.out.println("Usuário criado com sucesso!");
                    }
                }
            }

            int opcao = 0;
            while (opcao != 9) {
                System.out.println("\n------------------------------------");
                System.out.println("USUÁRIO LOGADO: " + usuarioLogado.getNome());
                System.out.println("1. Cadastrar Novo Produto");
                System.out.println("2. Registrar Entrada (Compra)");
                System.out.println("3. Registrar Venda (Saída)");
                System.out.println("4. Listar Estoque");
                System.out.println("9. Sair");
                System.out.print("Escolha: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    opcao = 0;
                }

                switch (opcao) {
                    case 1: // CADASTRAR PRODUTO
                        System.out.print(" ID do Produto (ex: 001): ");
                        String idP = scanner.nextLine();
                        System.out.print(" Nome do Produto: ");
                        String nomeP = scanner.nextLine();
                        System.out.print(" Preço: ");
                        float precoP = Float.parseFloat(scanner.nextLine());

                        // LÓGICA DE CATEGORIA (Reutiliza ou cria novo nó)
                        System.out.print(" Nome da Categoria : ");
                        String nomeCat = scanner.nextLine();
                        Categoria cat = categoriaService.buscarPorNome(nomeCat);
                        if (cat == null) {
                            System.out.print("Categoria nova! Digite um ID para ela: ");
                            cat = categoriaService.salvar(new Categoria(scanner.nextLine(), nomeCat));
                        }

                        // LÓGICA DE UNIDADE (Reutiliza ou cria novo nó)
                        System.out.print("Sigla da Unidade (ex: kg, un, m,l): ");
                        String siglaUn = scanner.nextLine();
                        UnidadeMedida un = unidadeService.buscarPorSigla(siglaUn);
                        if (un == null) {
                            System.out.print(" Unidade nova! Digite um ID para ela: ");
                            un = unidadeService.salvar(new UnidadeMedida(scanner.nextLine(), siglaUn));
                        }

                        // Montagem do Produto com os links (Setas no Grafo)
                        Produto p = new Produto();
                        p.setId(idP);
                        p.setNome(nomeP);
                        p.setPreco(precoP);
                        p.setQuantidade(0); // Começa zerado, entrada via opção 2
                        p.setCategoria(cat);
                        p.setUnidadeMedida(un);

                        produtoService.salvar(p);
                        System.out.println(" Produto '" + nomeP + "' cadastrado e vinculado!");
                        break;

                    case 2: // ENTRADA
                    case 3: // VENDA
                        String tipoMov = (opcao == 2) ? "ENTRADA" : "SAIDA";
                        System.out.print("ID do Produto para " + tipoMov + ": ");
                        String idBusca = scanner.nextLine();

                        final Usuario userSession = usuarioLogado;

                        produtoService.buscarPorId(idBusca).ifPresentOrElse(prod -> {
                            System.out.print(" Quantidade: ");
                            int qtd = Integer.parseInt(scanner.nextLine());

                            if (tipoMov.equals("SAIDA") && prod.getQuantidade() < qtd) {
                                System.out.println(" Erro: Estoque insuficiente (Atual: " + prod.getQuantidade() + ")");
                            } else {
                                // 1. Atualiza quantidade no Nó Produto
                                int novaQtd = (tipoMov.equals("ENTRADA")) ? prod.getQuantidade() + qtd : prod.getQuantidade() - qtd;
                                prod.setQuantidade(novaQtd);
                                produtoService.salvar(prod);

                                // 2. Cria Nó Movimentação com os Links (Usuário e Produto)
                                Movimentacao mov = new Movimentacao();
                                mov.setId(UUID.randomUUID().toString()); // ID automático
                                mov.setData(LocalDate.now());
                                mov.setTipo(tipoMov);
                                mov.setQuantidade(qtd);
                                mov.setUsuario(userSession); // Link (Mov)-[:REALIZOU]->(User)
                                mov.setProduto(prod);       // Link (Mov)-[:AFETA]->(Prod)

                                movService.registrar(mov);
                                System.out.println("" + tipoMov + " concluída! Novo estoque: " + novaQtd);
                            }
                        }, () -> System.out.println(" Produto não encontrado!"));
                        break;

                    case 4: // LISTAGEM
                        System.out.println("\n=== ESTOQUE ATUAL ===");
                        produtoService.listarTodos().forEach(item ->
                                System.out.println("ID: " + item.getId() +
                                        " | Nome: " + item.getNome() +
                                        " | Qtd: " + item.getQuantidade() +
                                        " | Cat: " + (item.getCategoria() != null ? item.getCategoria().getNome() : "N/A")));
                        break;
                }
            }
            System.out.println("Saindo do sistema...");
            System.exit(0);
        };
    }
}