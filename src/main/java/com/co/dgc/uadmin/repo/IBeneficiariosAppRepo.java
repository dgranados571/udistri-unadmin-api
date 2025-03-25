package com.co.dgc.uadmin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.dgc.uadmin.entity.BeneficiariosApp;

public interface IBeneficiariosAppRepo extends JpaRepository<BeneficiariosApp, Long> {
		
	@Query("FROM BeneficiariosApp ba WHERE ba.id_procesamiento = ?1")
	public List<BeneficiariosApp> getBeneficiariosPorIdProcesamiento(String id_procesamiento);
	
	@Query("FROM BeneficiariosApp ba WHERE ba.id_procesamiento = ?1 AND ba.identificacion_ben = ?2")
	public List<BeneficiariosApp> getBeneficiariosPorIdProcesamientoDocumento(String id_procesamiento, String identificacionBen);

}
