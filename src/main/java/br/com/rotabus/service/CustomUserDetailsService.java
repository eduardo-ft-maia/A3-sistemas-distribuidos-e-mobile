package br.com.rotabus.service;

import br.com.rotabus.enums.TipoUsuario;
import br.com.rotabus.model.Usuario;
import br.com.rotabus.repository.EmpresaRepository;
import br.com.rotabus.repository.UsuarioRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    public CustomUserDetailsService(
            UsuarioRepository usuarioRepository,
            EmpresaRepository empresaRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuário não encontrado"
                        )
                );

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(
                        List.of(
                                new SimpleGrantedAuthority(
                                        "ROLE_" + usuario.getRole()
                                )
                        )
                )
                .build();
    }

    public Usuario usuarioLogado() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return usuarioRepository
                .findByUsername(username)
                .orElseThrow();
    }

    public boolean usuarioLogadoEhAdmin() {
        return usuarioLogado()
                .getRole() == TipoUsuario.ADMIN;
    }

    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public void deletar(Long id) {
        Usuario usuarioLogado = usuarioLogado();

        if (usuarioLogado.getId().equals(id)) {
            throw new RuntimeException("Você não pode deletar o próprio usuário");
        }

        usuarioRepository.deleteById(id);
    }

    public void cadastrar(Usuario usuario, Long empresaId) {
        if (empresaId != null) {
            usuario.setEmpresa(empresaRepository.findById(empresaId).orElseThrow());
        } else {
            usuario.setEmpresa(null);
        }

        usuarioRepository.save(usuario);
    }

    public void editar(Long id, Usuario dadosUsuario, Long empresaId) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        usuario.setUsername(dadosUsuario.getUsername());
        usuario.setRole(dadosUsuario.getRole());

        if (empresaId != null) {
            usuario.setEmpresa(empresaRepository.findById(empresaId).orElseThrow());
        } else {
            usuario.setEmpresa(null);
        }

        if (dadosUsuario.getPassword() != null && !dadosUsuario.getPassword().isBlank()) {
            usuario.setPassword(dadosUsuario.getPassword());
        }

        usuarioRepository.save(usuario);
    }
}