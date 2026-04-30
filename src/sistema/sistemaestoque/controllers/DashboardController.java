
package sistema.sistemaestoque.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sistema.sistemaestoque.models.Usuario;
import sistema.sistemaestoque.services.EstoqueService;

@Controller
public class DashboardController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");

        if (logado == null) {
            return "redirect:/"; // Proteção: se não logou, volta pro início
        }

        model.addAttribute("usuario", logado);
        model.addAttribute("lotes", estoqueService.listarLotesComProdutos());
        model.addAttribute("alertas", estoqueService.buscarLotesVencendo());

        return "dashboard"; // Abre o arquivo dashboard.html
    }
}