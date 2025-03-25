package com.co.dgc.uadmin.request;

import java.util.ArrayList;
import java.util.List;
import com.co.dgc.uadmin.dto.NotificacionesAppDto;

public class RqActualizaNotificacionesEventos {

	private String usuarioApp;
    private List<NotificacionesAppDto> listaNotificacion = new ArrayList<>();
    
	public RqActualizaNotificacionesEventos() {
		super();
	}

	public String getUsuarioApp() {
		return usuarioApp;
	}

	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}

	public List<NotificacionesAppDto> getListaNotificacion() {
		return listaNotificacion;
	}

	public void setListaNotificacion(List<NotificacionesAppDto> listaNotificacion) {
		this.listaNotificacion = listaNotificacion;
	}
	
	
    
    
}
