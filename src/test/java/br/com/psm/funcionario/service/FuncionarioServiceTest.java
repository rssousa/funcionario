package br.com.psm.funcionario.service;


import br.com.psm.funcionario.dto.input.FuncionarioInputDto;
import br.com.psm.funcionario.entity.CargoEntity;
import br.com.psm.funcionario.repository.CargoRepository;
import br.com.psm.funcionario.repository.DepartamentoRepository;
import br.com.psm.funcionario.repository.FuncionarioDepartamentoRepository;
import br.com.psm.funcionario.repository.FuncionarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class FuncionarioServiceTest {

    @MockBean
    private FuncionarioRepository funcionarioRepository;
    @MockBean
    private CargoRepository cargoRepository;

    private FuncionarioService funcionarioService;
    private FuncionarioInputDto funcionario;
    private DepartamentoRepository departamentoRepository;
    private FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;


    @Before
    public void setUp() {
        setUpService();
        setUpFuncionario();
    }

    private void setUpFuncionario() {
        funcionario = new FuncionarioInputDto();
        funcionario.setDataNascimento("26/02/1990");
        funcionario.setNome("João");
    }

    private void setUpService() {
        funcionarioService = new FuncionarioServiceImpl(
                funcionarioRepository,
                cargoRepository,
                departamentoRepository,
                funcionarioDepartamentoRepository
        );
    }

    @Test
    public void deveSalvarUmFuncionario() {
        funcionarioService.salvar(funcionario);

        verify(funcionarioRepository).save(funcionario.toEntity(new CargoEntity()));
    }

    @Test
    public void deveDeleterUmFuncionario() {
        funcionarioService.remover(1L);

        verify(funcionarioRepository).deleteById(1L);
    }

    @Test
    public void deveListarTodosFuncionarios() {
        funcionarioService.listar();

        verify(funcionarioRepository).findAll();
    }

    @Test
    public void deveListarFuncionariosDeUmDepartamento() {
        Long idDepartamento = 1L;
        funcionarioService.findFuncionariosByDepartamento(idDepartamento);

        verify(funcionarioRepository).findFuncionariosByDepartamento(idDepartamento);
    }



}
