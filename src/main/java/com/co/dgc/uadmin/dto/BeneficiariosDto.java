package com.co.dgc.uadmin.dto;

import com.co.dgc.uadmin.entity.BeneficiariosApp;

public class BeneficiariosDto {
	
	private String nombresBen;
	private String apellidosBen;
	private String identificacionBen;
	private boolean registraDocPdf;
	private DocumentosDto documentosDto;
	
	public BeneficiariosDto() {
		super();
	}
	
	public BeneficiariosDto(BeneficiariosApp beneficiariosApp) {
		super();
		this.nombresBen = beneficiariosApp.getNombres_ben();
		this.apellidosBen =  beneficiariosApp.getApellidos_ben();
		this.identificacionBen = beneficiariosApp.getIdentificacion_ben();
		this.registraDocPdf = beneficiariosApp.isRegistra_doc_pdf();
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

	public DocumentosDto getDocumentosDto() {
		return documentosDto;
	}

	public void setDocumentosDto(DocumentosDto documentosDto) {
		this.documentosDto = documentosDto;
	}

	public String getApellidosBen() {
		return apellidosBen;
	}

	public void setApellidosBen(String apellidosBen) {
		this.apellidosBen = apellidosBen;
	}
	
}
