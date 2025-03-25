package com.co.dgc.uadmin.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.dgc.uadmin.entity.NotificacionesApp;
import com.co.dgc.uadmin.repo.INotificacionesAppRepo;

@Service
public class NotificacionesAppService implements INotificacionesAppService {
	
	@Autowired
	INotificacionesAppRepo iNotificacionesAppRepo;

	@Override
	public List<NotificacionesApp> getNotificaciones() {
		return iNotificacionesAppRepo.findAll();
	}
	
	@Override
	public List<NotificacionesApp> getNotificacionesPorEvento(String nombre_evento) {
		return iNotificacionesAppRepo.getNotificacionesPorEvento(nombre_evento);
	}

	@Override
	public void registraEventoNotificacion(NotificacionesApp notificacionesApp) {
		iNotificacionesAppRepo.save(notificacionesApp);		
	}

}
