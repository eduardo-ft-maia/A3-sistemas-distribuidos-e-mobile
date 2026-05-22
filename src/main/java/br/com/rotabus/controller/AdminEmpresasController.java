package br.com.rotabus.controller;

import br.com.rotabus.model.Empresa;
import br.com.rotabus.service.EmpresaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/empresas")
public class AdminEmpresasController {

    private final EmpresaService empresaService;

    public AdminEmpresasController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public String listar(
            Model model,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Empresa> empresas = empresaService.listar(pageable);

        model.addAttribute("empresas", empresas);
        model.addAttribute("paginaAtiva", "empresas");

        return "admin/empresas/listar";
    }

    @PostMapping
    public String cadastrar(Empresa empresa) {
        empresaService.cadastrar(empresa);

        return "redirect:/admin/empresas";
    }

    @PostMapping("/editar")
    public String editar(
            @RequestParam Long id,
            Empresa empresa
    )
    {
        empresaService.editar(id, empresa);

        return "redirect:/admin/empresas";
    }

    @PostMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            empresaService.deletar(id);

            return ResponseEntity.ok().build();

        } catch (Exception ex) {

            return ResponseEntity.badRequest().build();
        }
    }
}