package sistema.sistemaestoque.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
@Node("UnidadeMedida")
public class UnidadeMedida {
    @Id
    private String id;
    private String sigla;
    public UnidadeMedida(String id, String sigla) {
        this.id = id;
        this.sigla = sigla;
    }
    public  UnidadeMedida() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}

