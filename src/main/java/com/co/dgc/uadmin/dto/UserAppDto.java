package com.co.dgc.uadmin.dto;

import com.co.dgc.uadmin.entity.UserApp;

public class UserAppDto {
	
	private long id;
	private String usuario;
	private String contrasenia;	
	private String nombre;
	private String apellidos;	
	private String tipo_identificacion;	
	private String identificacion;	
	private String correo;	
	private String role;	
	private String fecha_registro;	
	private boolean usuario_activo;	
	private String id_procesamiento;	
	private String usuarioApp;
	private String iniciales_nombre;
	
	public UserAppDto() {
		super();
	}
	
	public UserAppDto(UserApp userApp) {
		super();
		this.id = userApp.getId();
		this.usuario = userApp.getUsuario();
		this.contrasenia = userApp.getContrasenia();
		this.nombre = userApp.getNombre();
		this.apellidos = userApp.getApellidos();
		this.tipo_identificacion = userApp.getTipo_identificacion();
		this.identificacion = userApp.getIdentificacion();
		this.correo = userApp.getCorreo();
		this.role = userApp.getRole();
		this.fecha_registro = userApp.getFecha_registro();
		this.usuario_activo = userApp.isUsuario_activo();
		this.id_procesamiento = userApp.getId_procesamiento();
		this.usuarioApp = userApp.getUsuarioApp();
		this.iniciales_nombre = this.nombre.substring(0, 1) + this.apellidos.substring(0, 1);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
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
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public boolean isUsuario_activo() {
		return usuario_activo;
	}
	public void setUsuario_activo(boolean usuario_activo) {
		this.usuario_activo = usuario_activo;
	}
	public String getId_procesamiento() {
		return id_procesamiento;
	}
	public void setId_procesamiento(String id_procesamiento) {
		this.id_procesamiento = id_procesamiento;
	}
	public String getUsuarioApp() {
		return usuarioApp;
	}
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	public String getIniciales_nombre() {		
		return iniciales_nombre;
	}
	public void setIniciales_nombre(String iniciales_nombre) {
		this.iniciales_nombre = iniciales_nombre;
	}

}
