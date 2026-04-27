package sistema.sistemaestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.sistemaestoque.models.Usuario;
import sistema.sistemaestoque.repository.UsuarioRepository;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Usado para o seu "Login" sem senha
    public Usuario buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}