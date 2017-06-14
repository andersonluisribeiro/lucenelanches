package br.com.entelgy.lucenelanches.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.entelgy.lucenelanches.models.Request;

public interface RequestRepository extends CrudRepository<Request, Long>{

}
