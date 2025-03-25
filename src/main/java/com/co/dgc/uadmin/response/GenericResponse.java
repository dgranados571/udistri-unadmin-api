package com.co.dgc.uadmin.response;

import java.io.Serializable;

public class GenericResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean estado;
	private String mensaje;
	private Object objeto;
	
	public GenericResponse() {
		super();
	}
	
	public GenericResponse(boolean estado, String mensaje, Object objeto) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.objeto = objeto;
	}
	
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Object getObjeto() {
		return objeto;
	}
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	
	

}
