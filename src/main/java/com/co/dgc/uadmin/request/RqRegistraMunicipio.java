package com.co.dgc.uadmin.request;

public class RqRegistraMunicipio {
	
	private String municipio;
	private String idMunicipio;
	private String departamentoMun;
	
	public RqRegistraMunicipio() {
		super();
	}
	
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getDepartamentoMun() {
		return departamentoMun;
	}
	public void setDepartamentoMun(String departamentoMun) {
		this.departamentoMun = departamentoMun;
	}
	

}
