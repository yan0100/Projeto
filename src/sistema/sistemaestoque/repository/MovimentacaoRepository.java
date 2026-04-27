package sistema.sistemaestoque.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import sistema.sistemaestoque.models.Movimentacao;

@Repository
public interface MovimentacaoRepository extends Neo4jRepository<Movimentacao, String> {
}