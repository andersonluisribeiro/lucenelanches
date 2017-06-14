package br.com.entelgy.lucenelanches.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.entelgy.lucenelanches.models.Snack;
import br.com.entelgy.lucenelanches.models.TypeOfSnack;

public interface SnackRepository extends CrudRepository<Snack, Long>{

	List<Snack> findByType(TypeOfSnack type);	
	
}
