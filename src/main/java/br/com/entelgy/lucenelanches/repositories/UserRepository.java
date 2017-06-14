package br.com.entelgy.lucenelanches.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.entelgy.lucenelanches.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
