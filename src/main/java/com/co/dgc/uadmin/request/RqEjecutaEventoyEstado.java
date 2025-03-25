package com.co.dgc.uadmin.request;

public class RqEjecutaEventoyEstado {
	
	private String idProcesamiento;
	private String userApp;
	private String nombreOperacion;
	private String observaciones;
	
	public RqEjecutaEventoyEstado() {
		super();
	}
	
	public String getIdProcesamiento() {
		return idProcesamiento;
	}
	public void setIdProcesamiento(String idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
	}
	public String getUserApp() {
		return userApp;
	}
	public void setUserApp(String userApp) {
		this.userApp = userApp;
	}
	public String getNombreOperacion() {
		return nombreOperacion;
	}
	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
