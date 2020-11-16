package br.com.psm.funcionario.repository;

import br.com.psm.funcionario.entity.FuncionarioEntity;
import br.com.psm.funcionario.entity.FuncionarioMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FuncionarioRepository extends CrudRepository<FuncionarioEntity, Long> {

    @Query("SELECT f FROM funcionario f WHERE f.ativo = true")
    List<FuncionarioEntity> findAll();

    @Query(value =
            " SELECT func.id as id," +
                    " func.data_aniversario as dataAniversario," +
                    " func.documento as documento," +
                    " func.nome as nome" +
                    " FROM  FUNCIONARIO_DEPARTAMENTO func_depar" +
                    " JOIN FUNCIONARIO func ON func.id = func_depar.funcionario_id" +
                    " JOIN DEPARTAMENTO dep ON dep.id = func_depar.departamento_id" +
                    " WHERE func_depar.departamento_id = :idDepartamento AND func.ativo = true AND func_depar.data_fim IS NULL",
            nativeQuery = true)
    List<FuncionarioMapper> findFuncionariosByDepartamento(@Param("idDepartamento") Long idDepartamento);
}
