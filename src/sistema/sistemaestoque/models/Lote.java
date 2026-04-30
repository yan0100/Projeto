package sistema.sistemaestoque.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.time.LocalDate;

@Node("Lote")
public class Lote {
    @Id @GeneratedValue
    private Long internalId;
    private String codigo;
    private int quantidade;
    private LocalDate dataValidade;

    @Relationship(type = "LOTE_DE", direction = Relationship.Direction.OUTGOING)
    private Produto produto;

    public Lote(){}

    public Lote(long internalId, String codigo, int quantidade, LocalDate dataValidade) {
        this.internalId = internalId;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getInternalId() {
        return internalId;
    }
    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }
}