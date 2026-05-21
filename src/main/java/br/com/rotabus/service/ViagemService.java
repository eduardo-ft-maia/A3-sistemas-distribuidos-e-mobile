package br.com.rotabus.service;

import br.com.rotabus.model.Usuario;
import br.com.rotabus.model.Viagem;
import br.com.rotabus.repository.CidadeRepository;
import br.com.rotabus.repository.UsuarioRepository;
import br.com.rotabus.repository.ViagemRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final CidadeRepository cidadeRepository;
    private final CustomUserDetailsService usuarioService;

    public ViagemService(
            ViagemRepository viagemRepository,
            CidadeRepository cidadeRepository,
            CustomUserDetailsService usuarioService
    )
    {
        this.viagemRepository = viagemRepository;
        this.cidadeRepository = cidadeRepository;
        this.usuarioService = usuarioService;
    }

    public Viagem buscarPorId(Long id) {
        return viagemRepository.findById(id).orElseThrow();
    }

    public List<Viagem> listarViagensEmpresa() {
        Usuario usuario = usuarioService.usuarioLogado();

        if (usuarioService.usuarioLogadoEhAdmin()) {
            return viagemRepository.findAll();
        }

        return viagemRepository.findByEmpresa(usuario.getEmpresa());
    }

    public void cadastrar(
            Long cidadeOrigemId,
            Long cidadeDestinoId,
            LocalDateTime horarioSaida,
            LocalDateTime horarioChegada,
            BigDecimal valorPassagem,
            BigDecimal distanciaKm
    ) {
        Usuario usuario = usuarioService.usuarioLogado();

        Viagem viagem = new Viagem();

        viagem.setEmpresa(usuario.getEmpresa());

        viagem.setCidadeOrigem(cidadeRepository.findById(cidadeOrigemId).orElseThrow());
        viagem.setCidadeDestino(cidadeRepository.findById(cidadeDestinoId).orElseThrow());
        viagem.setHorarioSaida(horarioSaida);
        viagem.setHorarioChegada(horarioChegada);
        viagem.setValorPassagem(valorPassagem);
        viagem.setDistanciaKm(distanciaKm);

        viagem.setCriadoEm(LocalDateTime.now());

        viagemRepository.save(viagem);
    }

    public void editar(
            Long id,
            Long cidadeOrigemId,
            Long cidadeDestinoId,
            LocalDateTime horarioSaida,
            LocalDateTime horarioChegada,
            BigDecimal valorPassagem,
            BigDecimal distanciaKm
    ) {
        Viagem viagem = viagemRepository.findById(id).orElseThrow();

        viagem.setCidadeOrigem(cidadeRepository.findById(cidadeOrigemId).orElseThrow());
        viagem.setCidadeDestino(cidadeRepository.findById(cidadeDestinoId).orElseThrow());
        viagem.setHorarioSaida(horarioSaida);
        viagem.setHorarioChegada(horarioChegada);
        viagem.setValorPassagem(valorPassagem);
        viagem.setDistanciaKm(distanciaKm);

        viagemRepository.save(viagem);
    }

    public void deletar(Long id) {
        viagemRepository.deleteById(id);
    }

    public List<Viagem> buscar(String origem, String destino, LocalDate data, LocalTime horario) {
        LocalDateTime inicioBusca = LocalDateTime.of(data, horario);
        LocalDateTime fimDia = data.atTime(23, 59, 59);

        return viagemRepository.findByCidadeOrigemNomeIgnoreCaseAndCidadeDestinoNomeIgnoreCaseAndHorarioSaidaBetween(
                origem,
                destino,
                inicioBusca,
                fimDia
        );
    }
}