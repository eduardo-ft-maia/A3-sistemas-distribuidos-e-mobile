package br.com.rotabus.service;

import br.com.rotabus.model.Viagem;
import br.com.rotabus.repository.CidadeRepository;
import br.com.rotabus.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final CidadeRepository cidadeRepository;

    public ViagemService(
            ViagemRepository viagemRepository,
            CidadeRepository cidadeRepository
    )
    {
        this.viagemRepository = viagemRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public Viagem buscarPorId(Long id) {
        return viagemRepository.findById(id).orElseThrow();
    }

    public List<Viagem> listarViagensEmpresa() {
        return viagemRepository.findAll();
    }

    public void cadastrar(
            Long cidadeOrigemId,
            Long cidadeDestinoId,
            LocalDateTime horarioSaida,
            LocalDateTime horarioChegada,
            BigDecimal valorPassagem,
            BigDecimal distanciaKm
    ) {
        Viagem viagem = new Viagem();

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
    public List<Viagem> buscar(String origem, String destino) {
        return viagemRepository
                .findByCidadeOrigemNomeAndCidadeDestinoNome(origem, destino);
    }
}