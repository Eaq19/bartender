package com.aldeamo.bar.v1.repository;

import com.aldeamo.bar.v1.model.Bar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarRepository extends CrudRepository<Bar, Long> {

}
