package br.com.psm.funcionario.repository;

import br.com.psm.funcionario.entity.FuncionarioDepartamentoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface FuncionarioDepartamentoRepository extends CrudRepository<FuncionarioDepartamentoEntity, Long> {

    @Query(" SELECT f from funcionario_departamento f " +
            " where f.funcionario.id = :idFuncionario")
    Optional<FuncionarioDepartamentoEntity> findByIdFuncionario(@Param("idFuncionario") Long idFuncionario);

    @Query(" SELECT f from funcionario_departamento f " +
            " where f.departamento.id = :idDepartamento")
    List<FuncionarioDepartamentoEntity> findByIdDepartamento(@Param("idDepartamento") Long idDepartamento);
}
