package br.com.rotabus.service;

import br.com.rotabus.model.Empresa;
import br.com.rotabus.repository.EmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Page<Empresa> listar(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public void cadastrar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public void editar(Long id, Empresa dadosEmpresa) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        empresa.setRazaoSocial(dadosEmpresa.getRazaoSocial());
        empresa.setNomeFantasia(dadosEmpresa.getNomeFantasia());
        empresa.setCnpj(dadosEmpresa.getCnpj());
        empresa.setTelefone(dadosEmpresa.getTelefone());
        empresa.setEmail(dadosEmpresa.getEmail());

        empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }
}