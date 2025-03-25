package com.co.dgc.uadmin.dto;

import com.co.dgc.uadmin.entity.DepartamentosApp;
import com.co.dgc.uadmin.entity.MunicipiosApp;

public class MunicipiosDto {
	
	private MunicipiosApp municipioObj = new MunicipiosApp();
	private DepartamentosApp departamentoMunObj = new DepartamentosApp();
	
	public MunicipiosDto() {
		super();
	}
	
	public MunicipiosDto(MunicipiosApp municipioObj, DepartamentosApp departamentoMunObj) {
		super();
		this.municipioObj = municipioObj;
		this.departamentoMunObj = departamentoMunObj;
	}

	public MunicipiosApp getMunicipioObj() {
		return municipioObj;
	}
	public void setMunicipioObj(MunicipiosApp municipioObj) {
		this.municipioObj = municipioObj;
	}
	public DepartamentosApp getDepartamentoMunObj() {
		return departamentoMunObj;
	}
	public void setDepartamentoMunObj(DepartamentosApp departamentoMunObj) {
		this.departamentoMunObj = departamentoMunObj;
	}
	
	
	
	

}
