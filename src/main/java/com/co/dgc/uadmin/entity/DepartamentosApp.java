package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.co.dgc.uadmin.request.RqRegistraDepartamento;

@Entity
@Table(name = "departamentos_app")
public class DepartamentosApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "departamento")
	private String departamento;
	
	@Column(name = "id_departamento")
	private String id_departamento;
	
	@Column(name = "id_procesamiento")
	private String id_procesamiento;
	
	public DepartamentosApp() {
		super();
	}
	
	public DepartamentosApp(RqRegistraDepartamento body, String id_procesamiento) {
		super();
		this.departamento = body.getDepartamento();
		this.id_departamento = body.getIdDepartamento();
		this.id_procesamiento = id_procesamiento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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
