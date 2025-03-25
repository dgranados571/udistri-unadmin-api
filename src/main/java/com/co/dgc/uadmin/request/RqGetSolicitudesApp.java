package com.co.dgc.uadmin.request;

public class RqGetSolicitudesApp {
	
	private String usuarioApp;
	private String nombreFiltro;
	private String eventoFiltro;
	private String departamentoFiltro;
	private String municipioFiltro;
	private String diasUltimaActualizacionFiltro;
	private int elementosPorPagina;
	private int paginaActual;
	
	public RqGetSolicitudesApp() {
		super();
	}
	
	public String getUsuarioApp() {
		return usuarioApp;
	}
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	public int getElementosPorPagina() {
		return elementosPorPagina;
	}
	public void setElementosPorPagina(int elementosPorPagina) {
		this.elementosPorPagina = elementosPorPagina;
	}
	public int getPaginaActual() {
		return paginaActual;
	}
	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	public String getNombreFiltro() {
		return nombreFiltro;
	}
	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
	}
	public String getEventoFiltro() {
		return eventoFiltro;
	}
	public void setEventoFiltro(String eventoFiltro) {
		this.eventoFiltro = eventoFiltro;
	}
	public String getDepartamentoFiltro() {
		return departamentoFiltro;
	}
	public void setDepartamentoFiltro(String departamentoFiltro) {
		this.departamentoFiltro = departamentoFiltro;
	}
	public String getMunicipioFiltro() {
		return municipioFiltro;
	}
	public void setMunicipioFiltro(String municipioFiltro) {
		this.municipioFiltro = municipioFiltro;
	}

	public String getDiasUltimaActualizacionFiltro() {
		return diasUltimaActualizacionFiltro;
	}

	public void setDiasUltimaActualizacionFiltro(String diasUltimaActualizacionFiltro) {
		this.diasUltimaActualizacionFiltro = diasUltimaActualizacionFiltro;
	}
	
}
