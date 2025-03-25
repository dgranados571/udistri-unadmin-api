package com.co.dgc.uadmin.request;

public class RqEliminaRegistroS3 {

	private String idProcesamiento;
	private String moduloS3;
	private String urlTxt;

	public RqEliminaRegistroS3() {
		super();
	}

	public String getIdProcesamiento() {
		return idProcesamiento;
	}

	public void setIdProcesamiento(String idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
	}

	public String getUrlTxt() {
		return urlTxt;
	}

	public void setUrlTxt(String urlTxt) {
		this.urlTxt = urlTxt;
	}

	public String getModuloS3() {
		return moduloS3;
	}

	public void setModuloS3(String moduloS3) {
		this.moduloS3 = moduloS3;
	}

}
