package br.com.psm.funcionario.dto.ouput;

import br.com.psm.funcionario.entity.CargoEntity;

public class CargoOutputDto {

    private Long id;

    private String nome;

    public CargoOutputDto(CargoEntity cargoEntity) {
        this.id = cargoEntity.getId();
        this.nome = cargoEntity.getNome();
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
