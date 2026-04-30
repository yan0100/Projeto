package sistema.sistemaestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.sistemaestoque.models.*;
import sistema.sistemaestoque.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // 1. REGRA DE NEGÓCIO: Calcular estoque total somando lotes
    public int calcularEstoqueTotal(String produtoId) {
        List<Lote> lotes = loteRepository.findByProdutoId(produtoId);
        return lotes.stream().mapToInt(Lote::getQuantidade).sum();
    }

    // 2. ALERTA: Buscar lotes que vencem nos próximos 30 dias
    public List<Lote> buscarLotesVencendo() {
        LocalDate limite = LocalDate.now().plusDays(30);
        return loteRepository.findByDataValidadeBefore(limite);
    }

    // 3. MOVIMENTAÇÃO COM RASTREABILIDADE (Cargo e Motivo)
    public void registrarMovimentacao(Usuario usuario, Produto produto, int qtd, String motivo) {
        // Validação básica de permissão via código
        if (usuario.getCargo().equals("VENDEDOR") && qtd > 0) {
            throw new RuntimeException("Vendedores só podem registrar saídas!");
        }

        Movimentacao mov = new Movimentacao();
        mov.setUsuario(usuario);
        mov.setProduto(produto);
        mov.setQuantidade(qtd);
        mov.setMotivo(motivo);
        mov.setData(LocalDate.now());

        movimentacaoRepository.save(mov);
    }

    // 4. LISTAGEM GERAL PARA O DASHBOARD
    public List<Produto> listarProdutosComAlerta() {
        return produtoRepository.findAll();
    }
    public List<Lote> listarLotesComProdutos() {
        return loteRepository.findAll();
    }
}