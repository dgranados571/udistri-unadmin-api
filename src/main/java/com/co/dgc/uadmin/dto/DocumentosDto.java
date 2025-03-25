package com.co.dgc.uadmin.dto;

public class DocumentosDto {
	
	private String nombreArchivo;
	private String urlTxt;
	
	public DocumentosDto() {
		super();
	}
	
	public DocumentosDto(String nombreArchivo, String urlTxt) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.urlTxt = urlTxt;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getUrlTxt() {
		return urlTxt;
	}
	public void setUrlTxt(String urlTxt) {
		this.urlTxt = urlTxt;
	}
	

}
