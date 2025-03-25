package com.co.dgc.uadmin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.co.dgc.uadmin.entity.UserApp;

public interface IUserAppRepo extends JpaRepository<UserApp, Long> {
	
	@Query("FROM UserApp ua WHERE ua.usuario = ?1")
	public UserApp obtieneUsuarioAPPporUsuario(String usuario);
	
	@Query("FROM UserApp ua WHERE ua.role = ?1")
	public List<UserApp> obtieneUsuarioAPPporRole(String role);

}
