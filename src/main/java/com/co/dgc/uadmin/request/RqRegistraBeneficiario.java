package com.co.dgc.uadmin.request;

public class RqRegistraBeneficiario {
	
	private String nombresBen;
	private String apellidosBen;
	private String identificacionBen;
	private boolean registraDocPdf;
	private String idProcesamiento;
	
	public RqRegistraBeneficiario() {
		super();
	}
	
	public String getNombresBen() {
		return nombresBen;
	}
	public void setNombresBen(String nombresBen) {
		this.nombresBen = nombresBen;
	}
	public String getIdentificacionBen() {
		return identificacionBen;
	}
	public void setIdentificacionBen(String identificacionBen) {
		this.identificacionBen = identificacionBen;
	}
	public boolean isRegistraDocPdf() {
		return registraDocPdf;
	}
	public void setRegistraDocPdf(boolean registraDocPdf) {
		this.registraDocPdf = registraDocPdf;
	}
	public String getIdProcesamiento() {
		return idProcesamiento;
	}
	public void setIdProcesamiento(String idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
	}

	public String getApellidosBen() {
		return apellidosBen;
	}

	public void setApellidosBen(String apellidosBen) {
		this.apellidosBen = apellidosBen;
	}
	
	
	
	
	

}
