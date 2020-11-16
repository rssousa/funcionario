package br.com.psm.funcionario.dto.ouput;

import br.com.psm.funcionario.entity.DepartamentoEntity;

public class DepartamentoOutputDto {

    private Long id;

    private String nome;

    public DepartamentoOutputDto(DepartamentoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
