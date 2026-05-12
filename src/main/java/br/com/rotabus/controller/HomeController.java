package br.com.rotabus.controller;

import br.com.rotabus.service.ViagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final ViagemService viagemService;

    public HomeController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/viagens/buscar")
    public String buscar(
            @RequestParam String origem,
            @RequestParam String destino,
            Model model
    ) {

        model.addAttribute(
                "viagens",
                viagemService.buscar(origem, destino)
        );

        return "resultado-busca";
    }
}