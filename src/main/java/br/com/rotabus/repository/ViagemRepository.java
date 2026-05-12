package br.com.rotabus.repository;

import br.com.rotabus.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    List<Viagem> findByCidadeOrigemNomeAndCidadeDestinoNome(
            String origem,
            String destino
    );
}