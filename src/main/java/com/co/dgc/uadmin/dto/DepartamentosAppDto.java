package com.co.dgc.uadmin.dto;

import java.util.ArrayList;
import java.util.List;

import com.co.dgc.uadmin.entity.DepartamentosApp;

public class DepartamentosAppDto {
	
	private List<DepartamentosApp> departamentosApp = new ArrayList<>();
	private int totalElementos;
	
	public DepartamentosAppDto() {
		super();
	}
	
	public DepartamentosAppDto(List<DepartamentosApp> departamentosApp, int totalElementos) {
		super();
		this.departamentosApp = departamentosApp;
		this.totalElementos = totalElementos;
	}
	
	public List<DepartamentosApp> getDepartamentosApp() {
		return departamentosApp;
	}
	public void setDepartamentosApp(List<DepartamentosApp> departamentosApp) {
		this.departamentosApp = departamentosApp;
	}
	public int getTotalElementos() {
		return totalElementos;
	}
	public void setTotalElementos(int totalElementos) {
		this.totalElementos = totalElementos;
	}
	
	
	

}
