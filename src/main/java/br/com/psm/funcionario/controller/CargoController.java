package br.com.psm.funcionario.controller;

import br.com.psm.funcionario.dto.input.CargoInputDto;
import br.com.psm.funcionario.dto.ouput.CargoOutputDto;
import br.com.psm.funcionario.service.CargoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CargoOutputDto> salvar(UriComponentsBuilder uriComponentsBuilder,
                                                 @RequestBody CargoInputDto cargoInputDto) {

        Optional<CargoOutputDto> algumDto = cargoService.salvar(cargoInputDto);

        UriComponents uriComponents = uriComponentsBuilder.path("/{id}")
                .buildAndExpand(algumDto.map(CargoOutputDto::getId).orElseThrow());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
