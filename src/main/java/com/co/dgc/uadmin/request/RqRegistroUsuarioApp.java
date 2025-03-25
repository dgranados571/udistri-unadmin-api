package com.co.dgc.uadmin.request;

import java.util.Date;

public class RqRegistroUsuarioApp {
	
	private String nombres;
	private String apellidos; 
	private String tipoIdentificacion;
	private String identificacion;
	private String correo; 
	private String role;
	private String usuario; 
	private Date fechaRegistro; 
	private String usuarioApp;
	
	public RqRegistroUsuarioApp() {
		super();
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getUsuarioApp() {
		return usuarioApp;
	}
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	
	

}
