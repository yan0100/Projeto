package sistema.sistemaestoque.models;

import java.time.LocalDate;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("Movimentacao")
public class Movimentacao {
    @Id
    private String id;
    private LocalDate data;
    private String motivo;
    private int quantidade;
    private String tipo;
    @Relationship(type="REALIZOU", direction = Direction.OUTGOING)
    private Usuario usuario;
    @Relationship(type = "AFETA", direction = Direction.OUTGOING)
    private Produto produto;
    public Movimentacao(){}
    public Movimentacao(String id, LocalDate data, String motivo, int quantidade, String tipo, Usuario usuario, Produto produto) {
        this.id = id;
        this.data = data;
        this.motivo = motivo;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.usuario = usuario;
        this.produto = produto;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getTipo() {
        return tipo;

    }
    public void setTipo(String tipo) {
        this.tipo = tipo;

    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
