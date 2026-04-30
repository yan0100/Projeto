package sistema.sistemaestoque.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import sistema.sistemaestoque.models.Lote;
import java.time.LocalDate;
import java.util.List;

public interface LoteRepository extends Neo4jRepository<Lote, Long> {

    // Busca lotes vencendo antes de uma data específica
    List<Lote> findByDataValidadeBefore(LocalDate data);

    @Query("MATCH (l:Lote)-[:LOTE_DE]->(p:Produto) WHERE p.id = $produtoId RETURN l")
    List<Lote> findByProdutoId(String produtoId);
}