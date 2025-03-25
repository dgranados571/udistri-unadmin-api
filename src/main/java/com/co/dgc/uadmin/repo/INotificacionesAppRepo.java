package com.co.dgc.uadmin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.dgc.uadmin.entity.NotificacionesApp;

public interface INotificacionesAppRepo extends JpaRepository<NotificacionesApp, Long> {
	
	@Query("FROM NotificacionesApp na WHERE na.nombre_evento = ?1")
	public List<NotificacionesApp> getNotificacionesPorEvento(String nombre_evento);

}
