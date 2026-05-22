package br.com.rotabus.controller;

import br.com.rotabus.service.EstadoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DestinosController {

    private final EstadoService estadoService;

    public DestinosController(
            EstadoService estadoService
    ) {
        this.estadoService = estadoService;
    }

    @GetMapping("/destinos")
    public String index(
            Model model
    ) {
        model.addAttribute(
                "estados",
                estadoService.listar()
        );

        return "destinos/destinos";
    }

}