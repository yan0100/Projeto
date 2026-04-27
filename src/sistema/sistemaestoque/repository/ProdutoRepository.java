package sistema.sistemaestoque.repository;

import sistema.sistemaestoque.models.Produto;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends Neo4jRepository<Produto, String> {
}