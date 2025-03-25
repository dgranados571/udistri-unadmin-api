package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.co.dgc.uadmin.request.RqRegistraMunicipio;

@Entity
@Table(name = "municipios_app")
public class MunicipiosApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "municipio")
	private String municipio;
	
	@Column(name = "id_municipio")
	private String id_municipio;
	
	@Column(name = "id_departamento")
	private String id_departamento;
	
	@Column(name = "id_procesamiento")
	private String id_procesamiento;
	
	
	public MunicipiosApp() {
		super();
	}
	
	public MunicipiosApp(RqRegistraMunicipio body, String id_procesamiento) {
		super();
		this.municipio = body.getMunicipio();
		this.id_municipio = body.getIdMunicipio();
		this.id_departamento = body.getDepartamentoMun();
		this.id_procesamiento = id_procesamiento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(String id_municipio) {
		this.id_municipio = id_municipio;
	}

	public String getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(String id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getId_procesamiento() {
		return id_procesamiento;
	}

	public void setId_procesamiento(String id_procesamiento) {
		this.id_procesamiento = id_procesamiento;
	}
	
	

}
