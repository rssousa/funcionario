package br.com.psm.funcionario.repository;

import br.com.psm.funcionario.entity.DepartamentoEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartamentoRepository extends CrudRepository<DepartamentoEntity, Long> {

}
