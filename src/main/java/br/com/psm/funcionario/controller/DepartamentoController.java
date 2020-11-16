package br.com.psm.funcionario.controller;

import br.com.psm.funcionario.dto.input.DepartamentoInputDto;
import br.com.psm.funcionario.dto.ouput.DepartamentoOutputDto;
import br.com.psm.funcionario.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DepartamentoOutputDto> salvar(
            UriComponentsBuilder uriComponentsBuilder,
            @RequestBody DepartamentoInputDto departamentoInputDto) {

        Optional<DepartamentoOutputDto> algumDto = departamentoService.salvar(departamentoInputDto);

        UriComponents uriComponents = uriComponentsBuilder.path("/{id}")
                .buildAndExpand(algumDto.map(DepartamentoOutputDto::getId).orElseThrow());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}
