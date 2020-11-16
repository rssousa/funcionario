package br.com.psm.funcionario.dto.input;

import br.com.psm.funcionario.entity.DepartamentoEntity;

public class DepartamentoInputDto {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DepartamentoEntity toEntity() {
        DepartamentoEntity departamento = new DepartamentoEntity();

        departamento.setNome(nome);

        return departamento;
    }
}
