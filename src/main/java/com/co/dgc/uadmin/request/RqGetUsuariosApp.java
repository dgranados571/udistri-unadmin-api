package com.co.dgc.uadmin.request;

public class RqGetUsuariosApp {
	
	private String usuarioApp;
	private String role;
	
	public RqGetUsuariosApp() {
		super();
	}
	public String getUsuarioApp() {
		return usuarioApp;
	}
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
