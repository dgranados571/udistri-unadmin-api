package com.co.dgc.uadmin.services;

import java.util.List;

import com.co.dgc.uadmin.entity.EventosSolicitudesApp;

public interface IEventosSolicitudesAppService {
	
	public void registraEventoSolicitud(EventosSolicitudesApp eventoSolicitudesApp);
	public List<EventosSolicitudesApp> getESAPorIdProcesamiento(String idProsesamiento);
	public List<EventosSolicitudesApp> getESAPorOperacion(String nombreOperacion, String resultadoOperacion);
	public void eliminarEventoSolicitud(EventosSolicitudesApp eventoSolicitudesApp);
	

}
