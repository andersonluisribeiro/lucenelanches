package br.com.entelgy.lucenelanches.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.entelgy.lucenelanches.models.User;

@Repository
public class UserDAO implements UserRepositoryCustom, UserDetailsService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
				.setParameter("username", username).getResultList();
		if(users.isEmpty()){
			throw new UsernameNotFoundException("Usuário " + username + " não encontrato");
		}
		return users.get(0);
	}

}
