package br.com.rotabus.service;

import br.com.rotabus.model.Estado;
import br.com.rotabus.repository.EstadoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(
            EstadoRepository estadoRepository
    ) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }
}