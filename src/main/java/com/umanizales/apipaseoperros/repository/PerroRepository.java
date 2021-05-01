package com.umanizales.apipaseoperros.repository;

import com.umanizales.apipaseoperros.model.entities.Perro;
import org.springframework.data.repository.CrudRepository;

public interface PerroRepository extends CrudRepository<Perro,String> {
}
