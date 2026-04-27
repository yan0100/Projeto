package sistema.sistemaestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.sistemaestoque.models.Movimentacao;
import sistema.sistemaestoque.repository.MovimentacaoRepository;
import java.util.List;
import java.util.UUID;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository repository;

    public Movimentacao registrar(Movimentacao mov) {
        // Se o ID vier vazio, nós geramos um automático aqui
        if (mov.getId() == null || mov.getId().isEmpty()) {
            mov.setId(UUID.randomUUID().toString());
        }
        return repository.save(mov);
    }
}