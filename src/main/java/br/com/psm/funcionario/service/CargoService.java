package br.com.psm.funcionario.service;

import br.com.psm.funcionario.dto.input.CargoInputDto;
import br.com.psm.funcionario.dto.ouput.CargoOutputDto;

import java.util.Optional;

public interface CargoService {

    Optional<CargoOutputDto> salvar(CargoInputDto cargo);
}
