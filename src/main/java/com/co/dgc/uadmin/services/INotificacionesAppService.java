package com.co.dgc.uadmin.services;

import java.util.List;

import com.co.dgc.uadmin.entity.NotificacionesApp;

public interface INotificacionesAppService {
	
	public List<NotificacionesApp> getNotificaciones();
	public List<NotificacionesApp> getNotificacionesPorEvento(String nombre_evento);
	public void registraEventoNotificacion(NotificacionesApp notificacionesApp);
	public void eliminarEventoNotificacion(NotificacionesApp notificacionesApp);

}
