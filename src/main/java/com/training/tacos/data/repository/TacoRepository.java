package com.training.tacos.data.repository;

import com.training.tacos.data.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
