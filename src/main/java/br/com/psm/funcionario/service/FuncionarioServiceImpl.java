package br.com.psm.funcionario.service;


import br.com.psm.funcionario.dto.input.FuncionarioInputDto;
import br.com.psm.funcionario.dto.ouput.FuncionarioOutputDto;
import br.com.psm.funcionario.entity.CargoEntity;
import br.com.psm.funcionario.entity.FuncionarioDepartamentoEntity;
import br.com.psm.funcionario.entity.FuncionarioEntity;
import br.com.psm.funcionario.entity.FuncionarioMapper;
import br.com.psm.funcionario.repository.CargoRepository;
import br.com.psm.funcionario.repository.DepartamentoRepository;
import br.com.psm.funcionario.repository.FuncionarioDepartamentoRepository;
import br.com.psm.funcionario.repository.FuncionarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository,
                                  CargoRepository cargoRepository,
                                  DepartamentoRepository departamentoRepository,
                                  FuncionarioDepartamentoRepository funcionarioDepartamentoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.departamentoRepository = departamentoRepository;
        this.funcionarioDepartamentoRepository = funcionarioDepartamentoRepository;
    }

    @Override
    public Optional<FuncionarioOutputDto> salvar(FuncionarioInputDto funcionarioInputDto) {
        return salvarFuncionarioEvincularAumDepartamento(funcionarioInputDto)
                .map(FuncionarioOutputDto::new);
    }

    private Optional<FuncionarioEntity> salvarFuncionarioEvincularAumDepartamento(FuncionarioInputDto funcionarioInputDto) {
        Optional<CargoEntity> algumCargo = cargoRepository.findById(funcionarioInputDto.getCargo());
        return algumCargo.map(cargo -> {
            FuncionarioEntity funcionario = funcionarioRepository.save(funcionarioInputDto.toEntity(cargo));
            vincularFuncionarioEmUmDepartamento(funcionario, funcionarioInputDto.getDepartamento());
            return funcionario;
        });
    }

    private void vincularFuncionarioEmUmDepartamento(FuncionarioEntity funcionario, Long idDepartamento) {
        departamentoRepository.findById(idDepartamento)
                .ifPresent(departamento -> {

                    FuncionarioDepartamentoEntity funcionarioDepartamento = new FuncionarioDepartamentoEntity();
                    funcionarioDepartamento.setFuncionario(funcionario);
                    funcionarioDepartamento.setDepartamento(departamento);

                    funcionarioDepartamentoRepository.save(funcionarioDepartamento);
                });
    }

    @Override
    public void remover(Long idFuncionario) {
        funcionarioRepository.deleteById(idFuncionario);
    }

    @Override
    public List<FuncionarioOutputDto> listar() {
        return funcionarioRepository.findAll().stream().map(FuncionarioOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<FuncionarioOutputDto> findById(Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).map(FuncionarioOutputDto::new);
    }

    @Override
    public List<FuncionarioOutputDto> findFuncionariosByDepartamento(Long idDepartamento) {
        return funcionarioRepository.findFuncionariosByDepartamento(idDepartamento)
                .stream()
                .map(FuncionarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void atualizar(Long idFuncionario, FuncionarioInputDto funcionarioInputDto) {
        funcionarioRepository.findById(idFuncionario)
                .ifPresent(funcionario -> {
                    cargoRepository.findById(Optional.ofNullable(funcionarioInputDto.getCargo()).orElse(0L))
                            .ifPresent(funcionario::setCargo);

                    Optional.ofNullable(funcionarioInputDto.getNome()).filter(StringUtils::isNotBlank)
                            .ifPresent(funcionario::setNome);

                    Optional.ofNullable(funcionarioInputDto.getDataNascimento()).filter(StringUtils::isNotBlank)
                            .ifPresent(dataNascimento -> {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                                funcionario.setDataAniversario(LocalDate.parse(dataNascimento, formatter));
                            });

                    Optional.ofNullable(funcionarioInputDto.getDocumento()).filter(StringUtils::isNotBlank)
                            .ifPresent(funcionario::setDocumento);

                    Optional.ofNullable(funcionarioInputDto.getDepartamento())
                            .ifPresent(idDepartamento -> funcionarioDepartamentoRepository.findByIdFuncionario(funcionario.getId())
                                    .ifPresent(funcionarioDepartamento -> {
                                        funcionarioDepartamento.setDataFim(LocalDate.now());
                                        funcionarioDepartamentoRepository.save(funcionarioDepartamento);
                                        vincularFuncionarioEmUmDepartamento(funcionario, idDepartamento);
                                    }));

                    funcionarioRepository.save(funcionario);
                });

    }

    @Override
    public Optional<FuncionarioOutputDto> desativar(Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setAtivo(Boolean.FALSE);
                    return funcionarioRepository.save(funcionario);
                }).map(FuncionarioOutputDto::new);
    }

    @Override
    public Optional<FuncionarioOutputDto> definirChefe(Long id) {
        return funcionarioDepartamentoRepository.findByIdFuncionario(id)
                .map(funcionarioDepartamento -> {
                    funcionarioDepartamento.setChefe(Boolean.TRUE);
                    deifinirOutrosFuncionariosComoNaoChefe(funcionarioDepartamento);
                    funcionarioDepartamentoRepository.save(funcionarioDepartamento);
                    return funcionarioDepartamento.getFuncionario();
                }).map(FuncionarioOutputDto::new);
    }

    private void deifinirOutrosFuncionariosComoNaoChefe(FuncionarioDepartamentoEntity funcionarioDepartamento) {
        funcionarioDepartamentoRepository.saveAll(funcionarioDepartamentoRepository
                .findByIdDepartamento(funcionarioDepartamento.getDepartamento().getId())
                .stream()
                .filter(fuDp -> !(fuDp.getId() == funcionarioDepartamento.getId()))
                .map(funcDep -> {
                    funcDep.setChefe(Boolean.FALSE);
                    return funcDep;
                }).collect(Collectors.toList()));
    }
}
