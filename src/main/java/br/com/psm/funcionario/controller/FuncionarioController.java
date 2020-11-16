package br.com.psm.funcionario.controller;

import br.com.psm.funcionario.dto.input.FuncionarioInputDto;
import br.com.psm.funcionario.dto.ouput.FuncionarioOutputDto;
import br.com.psm.funcionario.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FuncionarioOutputDto> salvar(UriComponentsBuilder uriComponentsBuilder,
                                                       @RequestBody FuncionarioInputDto funcionarioInputDto) {

        Optional<FuncionarioOutputDto> algumDto = funcionarioService.salvar(funcionarioInputDto);

        UriComponents uriComponents = uriComponentsBuilder.path("/{id}")
                .buildAndExpand(algumDto.map(FuncionarioOutputDto::getId).orElseThrow());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioOutputDto> findById(Long id) {
        return funcionarioService.findById(id).map(ResponseEntity::ok).orElseThrow();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FuncionarioOutputDto>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listar());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(Long id, @RequestBody FuncionarioInputDto funcionarioInputDto) {
        funcionarioService.atualizar(id, funcionarioInputDto);
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity<List<FuncionarioOutputDto>> listarTodos(Long id) {
        return ResponseEntity.ok(funcionarioService.findFuncionariosByDepartamento(id));
    }

    @GetMapping("/{id}/desativar")
    public ResponseEntity<FuncionarioOutputDto> desativar(Long id) {
        return funcionarioService.desativar(id).map(ResponseEntity::ok).orElseThrow();
    }

    @GetMapping("/{id}/definir/chefe")
    public ResponseEntity<FuncionarioOutputDto> definirChefe(Long id) {
        return funcionarioService.definirChefe(id).map(ResponseEntity::ok).orElseThrow();
    }

}
