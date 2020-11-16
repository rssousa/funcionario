package br.com.psm.funcionario.service;

import br.com.psm.funcionario.dto.input.DepartamentoInputDto;
import br.com.psm.funcionario.dto.ouput.DepartamentoOutputDto;
import br.com.psm.funcionario.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Optional<DepartamentoOutputDto> salvar(DepartamentoInputDto departamentoInputDto) {
        return Optional.ofNullable(departamentoRepository.save(departamentoInputDto.toEntity()))
                .map(DepartamentoOutputDto::new);
    }

}
