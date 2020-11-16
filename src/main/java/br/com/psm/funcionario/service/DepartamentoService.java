package br.com.psm.funcionario.service;

import br.com.psm.funcionario.dto.input.DepartamentoInputDto;
import br.com.psm.funcionario.dto.ouput.DepartamentoOutputDto;

import java.util.Optional;

public interface DepartamentoService {

    Optional<DepartamentoOutputDto> salvar(DepartamentoInputDto departamento);
}
