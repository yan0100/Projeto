package sistema.sistemaestoque.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Usuario")
public class Usuaro {
    @Id
    private String id;
    private String cargo;
    private String nome;
    public Usuario(){}
    public Usuario(String id, String cargo, String nome) {
        this.id = id;
        this.cargo = cargo;
        this.nome = nome;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
