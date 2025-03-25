package com.co.dgc.uadmin.request;

import com.co.dgc.uadmin.entity.UserApp;

public class RqActualizaContrasenia {

	private String usuarioApp;
	private UserApp usuarioEdita;
	
	public RqActualizaContrasenia() {
		super();
	}
	
	public String getUsuarioApp() {
		return usuarioApp;
	}
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	public UserApp getUsuarioEdita() {
		return usuarioEdita;
	}
	public void setUsuarioEdita(UserApp usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}
	
}
