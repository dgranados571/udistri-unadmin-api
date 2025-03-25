package com.co.dgc.uadmin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.co.dgc.uadmin.entity.SolicitudesApp;
import com.co.dgc.uadmin.repo.ISolicitudesAppRepo;

@Service
public class SolicitudesAppService implements ISolicitudesAppService {

	@Autowired
	ISolicitudesAppRepo iSolicitudesAppRepo;
	
	@Override
	public List<SolicitudesApp> listaSolicitudesApp() {
		return iSolicitudesAppRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public void registraSolicitudApp(SolicitudesApp solicitudesApp) {
		iSolicitudesAppRepo.save(solicitudesApp);
	}
	
	@Override
	public void eliminaSolicitudApp(SolicitudesApp solicitudesApp) {
		iSolicitudesAppRepo.delete(solicitudesApp);		
	}

	@Override
	public SolicitudesApp getSolicitudAppPorId(long id) {
		return iSolicitudesAppRepo.getById(id);
	}

	@Override
	public SolicitudesApp getSolicitudAppPorIdPocesamiento(String idProsesamiento) {
		return iSolicitudesAppRepo.getSolicitudAppPorIdPocesamiento(idProsesamiento);
	}

	@Override
	public List<SolicitudesApp> getSolicitudesAppPorNoDocumento(String numero_identificacion) {
		return iSolicitudesAppRepo.getSolicitudesAppPorNoDocumento(numero_identificacion);
	}

	@Override
	public List<SolicitudesApp> getSolicitudesAppPorIdDepartamentoMunicipio(String departamento_municipio) {
		return iSolicitudesAppRepo.getSolicitudesAppPorIdDepartamentoMunicipio(departamento_municipio);
	}

	@Override
	public List<SolicitudesApp> getSolicitudesAppPorEventoSolicitud(String evento_actual) {
		return iSolicitudesAppRepo.getSolicitudesAppPorEventoSolicitud(evento_actual);
	}

	@Override
	public List<SolicitudesApp> getSolicitudesAppContieneNoDocumento(String numero_identificacion) {
		return iSolicitudesAppRepo.getSolicitudesAppContieneNoDocumento(numero_identificacion);
	}

	@Override
	public List<SolicitudesApp> getSolicitudesAppPorEventoSolicitudYNoDocumento(String evento_actual, String numero_identificacion) {
		return iSolicitudesAppRepo.getSolicitudesAppPorEventoSolicitudYNoDocumento(evento_actual, numero_identificacion);
	}

}
