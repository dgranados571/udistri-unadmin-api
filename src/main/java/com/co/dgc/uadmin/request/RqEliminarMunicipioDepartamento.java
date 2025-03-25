package com.co.dgc.uadmin.request;

import com.co.dgc.uadmin.dto.MunicipiosDto;

public class RqEliminarMunicipioDepartamento {
	
	private MunicipiosDto municipioDto = new MunicipiosDto();
	
	public RqEliminarMunicipioDepartamento() {
		super();
	}

	public MunicipiosDto getMunicipioDto() {
		return municipioDto;
	}

	public void setMunicipioDto(MunicipiosDto municipioDto) {
		this.municipioDto = municipioDto;
	}
	

}
