package sistema.sistemaestoque.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import sistema.sistemaestoque.models.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends Neo4jRepository<UnidadeMedida, String> {
    UnidadeMedida findBySigla(String sigla);
}