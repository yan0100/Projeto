package sistema.sistemaestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.sistemaestoque.models.Produto;
import sistema.sistemaestoque.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        // O Spring já entende que se o Categoria e UnidadeMedida
        // tiverem IDs, ele deve apenas criar a "seta" (link) no banco.
        return repository.save(produto);
    }

    public Optional<Produto> buscarPorId(String id) {
        return repository.findById(id);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }
}