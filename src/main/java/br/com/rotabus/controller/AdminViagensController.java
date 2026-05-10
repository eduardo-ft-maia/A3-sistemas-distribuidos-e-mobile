package br.com.rotabus.controller;

import br.com.rotabus.service.CidadeService;
import br.com.rotabus.service.ViagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/viagens")
public class AdminViagensController {

    private final ViagemService viagemService;
    private final CidadeService cidadeService;

    public AdminViagensController(
            ViagemService viagemService,
            CidadeService cidadeService
    )
    {
        this.viagemService = viagemService;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("viagens", viagemService.listarViagensEmpresa());

        model.addAttribute("cidades", cidadeService.listar());

        return "admin/viagens/listar";
    }

    @PostMapping
    public String cadastrar(
            @RequestParam Long cidadeOrigemId,
            @RequestParam Long cidadeDestinoId,
            @RequestParam LocalDateTime horarioSaida,
            @RequestParam LocalDateTime horarioChegada,
            @RequestParam BigDecimal valorPassagem,
            @RequestParam BigDecimal distanciaKm
    )
    {
        viagemService.cadastrar(cidadeOrigemId, cidadeDestinoId, horarioSaida, horarioChegada, valorPassagem, distanciaKm);

        return "redirect:/admin/viagens";
    }

    @PostMapping("/editar")
    public String editar(
            @RequestParam Long id,
            @RequestParam Long cidadeOrigemId,
            @RequestParam Long cidadeDestinoId,
            @RequestParam LocalDateTime horarioSaida,
            @RequestParam LocalDateTime horarioChegada,
            @RequestParam BigDecimal valorPassagem,
            @RequestParam BigDecimal distanciaKm
    )
    {
        viagemService.editar(id, cidadeOrigemId, cidadeDestinoId, horarioSaida, horarioChegada, valorPassagem, distanciaKm);

        return "redirect:/admin/viagens";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        viagemService.deletar(id);
        return "redirect:/admin/viagens";
    }
}