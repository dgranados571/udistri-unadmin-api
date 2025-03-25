package com.co.dgc.uadmin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.dgc.uadmin.entity.MunicipiosApp;

public interface IMunicipiosAppRepo extends JpaRepository<MunicipiosApp, Long>  {
	
	@Query("FROM MunicipiosApp ma WHERE ma.id_departamento = ?1 ORDER BY ma.municipio")
	public List<MunicipiosApp> getMunicipiosPorIdDeDepartamento(String id_departamento);
	
	@Query("FROM MunicipiosApp ma WHERE ma.id_departamento = ?1 AND ma.id_municipio = ?2")
	public MunicipiosApp getMunicipiosPorIdDeDepartamentoIdDeMunicipio(String id_departamento, String id_municipio);

}
