package br.com.rotabus.repository;

import br.com.rotabus.model.Empresa;
import br.com.rotabus.model.Viagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    Page<Viagem> findByCidadeOrigemNomeIgnoreCaseAndCidadeDestinoNomeIgnoreCaseAndHorarioSaidaBetween(
            String origem,
            String destino,
            LocalDateTime inicioBusca,
            LocalDateTime fimDia,
            Pageable pageable
    );

    Page<Viagem> findByEmpresa(Empresa empresa, Pageable pageable);
}