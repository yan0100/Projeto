package sistema.sistemaestoque.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Produto")
public class Produto {
    @Id
    private String id;
    private String nome;
    private float preco;
    private int quantidade;
    @Relationship(type= "PERTENCE_A")
    private Categoria categoria;
    @Relationship(type="MEDIDO_EM")
    private UnidadeMedida unidadeMedida;
    @ReadOnlyProperty
    @Relationship(type="AFETA", direction=Relationship.Direction.INCOMING)
    private List<Movimentacao> movimentacao;

    public Produto() {
        movimentacao = new ArrayList<>();
    }
    public Produto(String id, String nome, float preco, int quantidade, Categoria categoria, UnidadeMedida unidadeMedida, List<Movimentacao> movimentacao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.movimentacao = (movimentacao !=null) ? new ArrayList<>(movimentacao) : new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;

    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;

    }
    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }
    public void setMovimentacao(List<Movimentacao> movimentacao) {
        this.movimentacao = movimentacao;
    }
}
