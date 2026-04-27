package sistema.sistemaestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.sistemaestoque.models.UnidadeMedida;
import sistema.sistemaestoque.repository.UnidadeMedidaRepository;
import java.util.List;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository repository;

    public UnidadeMedida salvar(UnidadeMedida unidade) {
        return repository.save(unidade);
    }
    public UnidadeMedida buscarPorSigla(String sigla) {
        return repository.findBySigla(sigla);
    }
    public List<UnidadeMedida> listarTodas() {
        return repository.findAll();
    }
}