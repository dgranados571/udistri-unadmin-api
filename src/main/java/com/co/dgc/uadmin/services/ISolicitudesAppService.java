package com.co.dgc.uadmin.services;

import java.util.List;
import com.co.dgc.uadmin.entity.SolicitudesApp;

public interface ISolicitudesAppService {

	public List<SolicitudesApp> listaSolicitudesApp();
	public void registraSolicitudApp(SolicitudesApp solicitudesApp);
	public void eliminaSolicitudApp(SolicitudesApp solicitudesApp);
	public SolicitudesApp getSolicitudAppPorId(long id);
	public SolicitudesApp getSolicitudAppPorIdPocesamiento(String idProsesamiento);
	public List<SolicitudesApp> getSolicitudesAppPorNoDocumento(String numero_identificacion);
	public List<SolicitudesApp> getSolicitudesAppContieneNoDocumento(String numero_identificacion);
	public List<SolicitudesApp> getSolicitudesAppPorIdDepartamentoMunicipio(String departamento_municipio);
	public List<SolicitudesApp> getSolicitudesAppPorEventoSolicitud(String evento_actual);
	public List<SolicitudesApp> getSolicitudesAppPorEventoSolicitudYNoDocumento(String evento_actual, String numero_identificacion);

}
