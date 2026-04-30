@Controller
public class DashboardController {
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Usuario logado = (Usuario) session.getAttribute("usuario");
        if (logado == null) return "redirect:/";

        model.addAttribute("usuario", logado);
        model.addAttribute("lotes", estoqueService.listarTodosLotes());
        model.addAttribute("alertas", estoqueService.buscarLotesVencendo());
        return "dashboard";
    }
}