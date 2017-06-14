package br.com.entelgy.lucenelanches.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.entelgy.lucenelanches.models.Cheese;

public interface CheeseRepository extends CrudRepository<Cheese, Long>{

}
