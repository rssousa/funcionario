package br.com.psm.funcionario.entity;

import br.com.psm.funcionario.dto.ouput.FuncionarioOutputDto;

public interface FuncionarioMapper {

    Long getId();

    String getDataAniversario();

    String getDocumento();

    String getNome();

    default FuncionarioOutputDto toDto() {
        FuncionarioOutputDto funcionarioOutputDto = new FuncionarioOutputDto();

        funcionarioOutputDto.setId(getId());
        funcionarioOutputDto.setDataNascimento(getDataAniversario());
        funcionarioOutputDto.setDocumento(getDocumento());
        funcionarioOutputDto.setNome(getNome());

        return  funcionarioOutputDto;
    }


}
