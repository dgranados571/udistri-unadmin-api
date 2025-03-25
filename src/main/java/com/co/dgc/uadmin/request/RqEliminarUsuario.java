package com.co.dgc.uadmin.request;

import com.co.dgc.uadmin.entity.UserApp;

public class RqEliminarUsuario {

	private String usuarioApp;
	private UserApp usuarioElimina;
	
	public RqEliminarUsuario() {
		super();
	}
	
	public String getUsuarioApp() {
		return usuarioApp;
	}
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	public UserApp getUsuarioElimina() {
		return usuarioElimina;
	}
	public void setUsuarioElimina(UserApp usuarioElimina) {
		this.usuarioElimina = usuarioElimina;
	}
	
	
	
	
}
