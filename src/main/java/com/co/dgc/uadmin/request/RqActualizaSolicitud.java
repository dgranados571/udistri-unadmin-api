package com.co.dgc.uadmin.request;

public class RqActualizaSolicitud {
	
	private String idProcesamiento;
	private String nombres;
	private String apellidos;
	private String numeroIdentificacion;
	private String correo;
	private String telefono;
	private String matriculaInmobiliaria;
	private String municipio;
	
	public RqActualizaSolicitud() {
		super();
	}
	
	public String getIdProcesamiento() {
		return idProcesamiento;
	}
	public void setIdProcesamiento(String idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
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
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}
	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	
	

}
