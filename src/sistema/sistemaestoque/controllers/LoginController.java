package sistema.sistemaestoque.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sistema.sistemaestoque.models.Usuario;
import sistema.sistemaestoque.repository.UsuarioRepository;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String loginPage() {
        return "login"; // Abre o arquivo login.html
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam String id, @RequestParam String senha, HttpSession session) {
        // Busca o usuário no Neo4j pelo ID (que pode ser o CPF ou login)
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            session.setAttribute("usuarioLogado", usuarioOpt.get());
            return "redirect:/dashboard"; // Sucesso! Vai para o painel
        }

        return "redirect:/?erro=true"; // Falhou! Volta para o login
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}