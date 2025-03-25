package com.co.dgc.uadmin.dto;

import com.co.dgc.uadmin.entity.NotificacionesApp;

public class NotificacionesAppDto {
	
	private String nombreEvento;
	private String labelEvento;
	private boolean notificaUsuario;
	private String correosNotifica;
	
	public NotificacionesAppDto() {
		super();
	}
	

	public NotificacionesAppDto(NotificacionesApp notificacionesApp, String labelEvento) {
		super();
		this.nombreEvento = notificacionesApp.getNombre_evento();
		this.labelEvento = labelEvento;
		this.notificaUsuario = notificacionesApp.isNotifica_usuario();
		this.correosNotifica = notificacionesApp.getCorreos_notifica();
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getLabelEvento() {
		return labelEvento;
	}

	public void setLabelEvento(String labelEvento) {
		this.labelEvento = labelEvento;
	}

	public boolean isNotificaUsuario() {
		return notificaUsuario;
	}

	public void setNotificaUsuario(boolean notificaUsuario) {
		this.notificaUsuario = notificaUsuario;
	}

	public String getCorreosNotifica() {
		return correosNotifica;
	}

	public void setCorreosNotifica(String correosNotifica) {
		this.correosNotifica = correosNotifica;
	}

}
