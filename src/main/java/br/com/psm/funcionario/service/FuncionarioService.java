package br.com.psm.funcionario.service;

import br.com.psm.funcionario.dto.input.FuncionarioInputDto;
import br.com.psm.funcionario.dto.ouput.FuncionarioOutputDto;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    Optional<FuncionarioOutputDto> salvar(FuncionarioInputDto funcionario);

    void remover(Long idFuncionario);

    List<FuncionarioOutputDto> listar();

    Optional<FuncionarioOutputDto> findById(Long idFuncionario);

    List<FuncionarioOutputDto> findFuncionariosByDepartamento(Long idDepartamento);

    void atualizar(Long idFuncionario, FuncionarioInputDto funcionarioInputDto);

    Optional<FuncionarioOutputDto> desativar(Long id);

    Optional<FuncionarioOutputDto> definirChefe(Long id);
}
