package br.com.psm.funcionario.repository;

import br.com.psm.funcionario.entity.CargoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CargoRepository extends CrudRepository<CargoEntity, Long> {
}
