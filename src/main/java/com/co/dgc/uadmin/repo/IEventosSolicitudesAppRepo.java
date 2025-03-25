package com.co.dgc.uadmin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.dgc.uadmin.entity.EventosSolicitudesApp;

public interface IEventosSolicitudesAppRepo extends JpaRepository<EventosSolicitudesApp, Long> {
	
	@Query("FROM EventosSolicitudesApp esa WHERE esa.id_procesamiento = ?1")
	public List<EventosSolicitudesApp> getESAPorIdProcesamiento(String id_procesamiento);
	
	@Query("FROM EventosSolicitudesApp esa WHERE esa.nombre_operacion = ?1 AND esa.resultado_operacion = ?2")
	public List<EventosSolicitudesApp> getESAPorOperacion(String nombreOperacion, String resultadoOperacion);

}
