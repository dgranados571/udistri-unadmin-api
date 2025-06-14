package com.co.dgc.uadmin.dto;

import java.util.ArrayList;
import java.util.List;

public class MunicipiosAppDto {

	private List<MunicipiosDto> municipiosDtoList = new ArrayList<>();
	private int totalElementos;
	
	public MunicipiosAppDto() {
		super();
	}

	public MunicipiosAppDto(List<MunicipiosDto> municipiosDtoList, int totalElementos) {
		super();
		this.municipiosDtoList = municipiosDtoList;
		this.totalElementos = totalElementos;
	}
	
	public List<MunicipiosDto> getMunicipiosDtoList() {
		return municipiosDtoList;
	}
	public void setMunicipiosDtoList(List<MunicipiosDto> municipiosDtoList) {
		this.municipiosDtoList = municipiosDtoList;
	}
	public int getTotalElementos() {
		return totalElementos;
	}
	public void setTotalElementos(int totalElementos) {
		this.totalElementos = totalElementos;
	}
	
	
	
	
}
