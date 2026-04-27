package sistema.sistemaestoque.repository;

import sistema.sistemaestoque.models.Usuario; // 2. Import do seu modelo
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario, String> {
    Usuario findByNome(String nome);
    // Pode deixar vazio, o S pring já faz o trabalho pesado!
}