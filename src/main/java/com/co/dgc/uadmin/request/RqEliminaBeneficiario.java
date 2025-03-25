package com.co.dgc.uadmin.request;

import com.co.dgc.uadmin.dto.BeneficiariosDto;

public class RqEliminaBeneficiario {
	
	private String idProcesamiento;
	private BeneficiariosDto beneficiariosDto;
	
	public RqEliminaBeneficiario() {
		super();
	}
	
	public String getIdProcesamiento() {
		return idProcesamiento;
	}
	public void setIdProcesamiento(String idProcesamiento) {
		this.idProcesamiento = idProcesamiento;
	}
	public BeneficiariosDto getBeneficiariosDto() {
		return beneficiariosDto;
	}
	public void setBeneficiariosDto(BeneficiariosDto beneficiariosDto) {
		this.beneficiariosDto = beneficiariosDto;
	}
	
	
	

}
