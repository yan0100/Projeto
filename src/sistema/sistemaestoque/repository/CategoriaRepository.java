package sistema.sistemaestoque.repository;

import sistema.sistemaestoque.models.Categoria;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoriaRepository extends Neo4jRepository<Categoria, String> {
    Categoria findByNome(String nome);
}