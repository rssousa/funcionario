package br.com.psm.funcionario.dto.input;

import br.com.psm.funcionario.entity.CargoEntity;

public class CargoInputDto {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CargoEntity toEntity() {
        CargoEntity cargoEntity = new CargoEntity();

        cargoEntity.setNome(this.nome);

        return cargoEntity;
    }
}
