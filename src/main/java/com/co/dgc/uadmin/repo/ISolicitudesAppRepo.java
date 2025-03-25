package com.co.dgc.uadmin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.co.dgc.uadmin.entity.SolicitudesApp;

public interface ISolicitudesAppRepo extends JpaRepository<SolicitudesApp, Long>{
	
	@Query("FROM SolicitudesApp sa WHERE sa.id_procesamiento = ?1")
	public SolicitudesApp getSolicitudAppPorIdPocesamiento(String id_procesamiento);
	
	@Query("FROM SolicitudesApp sa WHERE sa.numero_identificacion = ?1")
	public List<SolicitudesApp> getSolicitudesAppPorNoDocumento(String numero_identificacion);
	
	@Query("FROM SolicitudesApp sa WHERE sa.numero_identificacion LIKE %?1%")	
	public List<SolicitudesApp> getSolicitudesAppContieneNoDocumento(String numero_identificacion);
	
	@Query("FROM SolicitudesApp sa WHERE sa.departamento_municipio = ?1")
	public List<SolicitudesApp> getSolicitudesAppPorIdDepartamentoMunicipio(String departamento_municipio);
	
	@Query("FROM SolicitudesApp sa WHERE sa.evento_actual = ?1")
	public List<SolicitudesApp> getSolicitudesAppPorEventoSolicitud(String evento_actual);
	
	@Query("FROM SolicitudesApp sa WHERE sa.evento_actual = ?1 AND sa.numero_identificacion LIKE %?2%")
	public List<SolicitudesApp> getSolicitudesAppPorEventoSolicitudYNoDocumento(String evento_actual, String numero_identificacion);

}
