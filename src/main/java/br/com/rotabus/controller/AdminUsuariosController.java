package br.com.rotabus.controller;

import br.com.rotabus.model.Usuario;
import br.com.rotabus.service.CustomUserDetailsService;
import br.com.rotabus.service.EmpresaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuariosController {

    private final CustomUserDetailsService usuarioService;
    private final EmpresaService empresaService;

    public AdminUsuariosController(
            CustomUserDetailsService usuarioService,
            EmpresaService empresaService
    ) {
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
    }

    @GetMapping
    public String listar(
            Model model,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Usuario> usuarios = usuarioService.listar(pageable);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("empresas", empresaService.listarTodas());
        model.addAttribute("paginaAtiva", "usuarios");

        return "admin/usuarios/listar";
    }

    @PostMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);

            return ResponseEntity.ok().build();

        } catch (Exception ex) {

            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public String cadastrar(
            Usuario usuario,
            @RequestParam(required = false) Long empresaId
    ) {
        usuarioService.cadastrar(usuario, empresaId);

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/editar")
    public String editar(
            @RequestParam Long id,
            Usuario usuario,
            @RequestParam(required = false) Long empresaId
    ) {
        usuarioService.editar(id, usuario, empresaId);

        return "redirect:/admin/usuarios";
    }
}