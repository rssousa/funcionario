package br.com.psm.funcionario.repository;


import br.com.psm.funcionario.dto.ouput.FuncionarioOutputDto;
import br.com.psm.funcionario.entity.FuncionarioEntity;
import br.com.psm.funcionario.entity.FuncionarioMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@Sql(value = "/carregar-dados.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/limpar-dados.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class FuncionarioRepositoryTests {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void deveRecuperarOsFuncionarioDoDepartamento() {
        List<FuncionarioOutputDto> funcionarios = funcionarioRepository.findFuncionariosByDepartamento(1L)
                .stream().map(FuncionarioMapper::toDto).collect(Collectors.toList());

        assertThat(funcionarios, hasSize(2));
    }

}
