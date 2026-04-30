package sistema.sistemaestoque.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import sistema.sistemaestoque.models.Movimentacao;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends Neo4jRepository<Movimentacao, String> {
    @Query("MATCH (m:Movimentacao) WHERE m.dataHora >= $inicio AND m.dataHora <= $fim RETURN m")
    List<Movimentacao> buscarPorPeriodo(LocalDate inicio, LocalDate fim);
}