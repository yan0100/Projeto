package sistema.sistemaestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.sistemaestoque.models.Categoria;
import sistema.sistemaestoque.repository.CategoriaRepository;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria buscarPorNome(String nome) {
        // Você precisará criar este método no seu CategoriaRepository
        return repository.findByNome(nome);
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }
}