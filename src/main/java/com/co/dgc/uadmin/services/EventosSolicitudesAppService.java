package com.co.dgc.uadmin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.dgc.uadmin.entity.EventosSolicitudesApp;
import com.co.dgc.uadmin.repo.IEventosSolicitudesAppRepo;

@Service
public class EventosSolicitudesAppService implements IEventosSolicitudesAppService {
	
	@Autowired
	IEventosSolicitudesAppRepo iEventosSolicitudesAppRepo;

	@Override
	public void registraEventoSolicitud(EventosSolicitudesApp eventoSolicitudesApp) {
		iEventosSolicitudesAppRepo.save(eventoSolicitudesApp);
	}

	@Override
	public List<EventosSolicitudesApp> getESAPorIdProcesamiento(String idProsesamiento) {
		return iEventosSolicitudesAppRepo.getESAPorIdProcesamiento(idProsesamiento);
	}

	@Override
	public List<EventosSolicitudesApp> getESAPorOperacion(String nombreOperacion, String resultadoOperacion) {
		return iEventosSolicitudesAppRepo.getESAPorOperacion(nombreOperacion, resultadoOperacion);
	}

	@Override
	public void eliminarEventoSolicitud(EventosSolicitudesApp eventoSolicitudesApp) {
		iEventosSolicitudesAppRepo.delete(eventoSolicitudesApp);
	}

}
