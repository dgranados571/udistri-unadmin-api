package com.co.dgc.uadmin.dto;

import java.util.ArrayList;
import java.util.List;

public class SolicitudesAppDto {
	 
	private List<SolicitudAppDto> listaSolicitudesAppDto = new ArrayList<>();
	private int totalElementos;
	
	public SolicitudesAppDto() {
		super();
	}
	
	public SolicitudesAppDto(List<SolicitudAppDto> listaSolicitudesAppDto, int totalElementos) {
		super();
		this.listaSolicitudesAppDto = listaSolicitudesAppDto;
		this.totalElementos = totalElementos;
	}

	public List<SolicitudAppDto> getListaSolicitudesAppDto() {
		return listaSolicitudesAppDto;
	}

	public void setListaSolicitudesAppDto(List<SolicitudAppDto> listaSolicitudesAppDto) {
		this.listaSolicitudesAppDto = listaSolicitudesAppDto;
	}

	public int getTotalElementos() {
		return totalElementos;
	}

	public void setTotalElementos(int totalElementos) {
		this.totalElementos = totalElementos;
	}
	

	
	

}
