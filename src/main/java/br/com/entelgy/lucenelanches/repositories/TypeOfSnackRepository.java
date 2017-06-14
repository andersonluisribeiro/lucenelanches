package br.com.entelgy.lucenelanches.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.entelgy.lucenelanches.models.TypeOfSnack;

public interface TypeOfSnackRepository extends CrudRepository<TypeOfSnack, Long>{

	TypeOfSnack findByDescription(String description);	
	
}
