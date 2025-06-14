package com.co.dgc.uadmin.request;

public class RqGetDepartamentos {
	
	private int elementosPorPagina;
	private int paginaActual;
	
	public RqGetDepartamentos() {
		super();
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
	
	

}
