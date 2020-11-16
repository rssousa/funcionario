package br.com.psm.funcionario.service;

import br.com.psm.funcionario.dto.input.CargoInputDto;
import br.com.psm.funcionario.dto.ouput.CargoOutputDto;
import br.com.psm.funcionario.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public Optional<CargoOutputDto> salvar(CargoInputDto cargo) {
        return Optional.ofNullable(cargoRepository.save(cargo.toEntity()))
                .map(CargoOutputDto::new);
    }
}
