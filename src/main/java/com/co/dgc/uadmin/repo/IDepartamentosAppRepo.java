package com.co.dgc.uadmin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.co.dgc.uadmin.entity.DepartamentosApp;

public interface IDepartamentosAppRepo extends JpaRepository<DepartamentosApp, Long> {
	
	@Query("FROM DepartamentosApp da WHERE da.id_departamento = ?1")
	public DepartamentosApp getDepartamentosAppPorIdDepartamento(String id_departamento);

}
