package br.com.rotabus.service;

import br.com.rotabus.model.Cidade;
import br.com.rotabus.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }
}
