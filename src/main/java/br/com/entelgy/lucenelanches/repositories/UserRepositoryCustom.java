package br.com.entelgy.lucenelanches.repositories;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepositoryCustom {
	public UserDetails loadUserByUsername(String username);
}
