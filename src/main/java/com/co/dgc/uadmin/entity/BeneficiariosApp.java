package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beneficiarios_app")
public class BeneficiariosApp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombres_ben")
	private String nombres_ben;
	
	@Column(name = "apellidos_ben")
	private String apellidos_ben;

	@Column(name = "identificacion_ben")
	private String identificacion_ben;

	@Column(name = "registra_doc_pdf")
	private boolean registra_doc_pdf;

	@Column(name = "id_procesamiento")
	private String id_procesamiento;
	
	@Column(name = "id_sufix_txt")
	private String id_sufix_txt;
	

	public BeneficiariosApp() {
		super();
	}

	public BeneficiariosApp(String nombres_ben, String apellidos_ben, String identificacion_ben, boolean registra_doc_pdf,
			String id_procesamiento, String id_sufix_txt) {
		super();
		this.nombres_ben = nombres_ben;
		this.apellidos_ben = apellidos_ben;
		this.identificacion_ben = identificacion_ben;
		this.registra_doc_pdf = registra_doc_pdf;
		this.id_procesamiento = id_procesamiento;
		this.id_sufix_txt = id_sufix_txt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres_ben() {
		return nombres_ben;
	}

	public void setNombres_ben(String nombres_ben) {
		this.nombres_ben = nombres_ben;
	}
	
	public String getApellidos_ben() {
		return apellidos_ben;
	}

	public void setApellidos_ben(String apellidos_ben) {
		this.apellidos_ben = apellidos_ben;
	}

	public String getIdentificacion_ben() {
		return identificacion_ben;
	}

	public void setIdentificacion_ben(String identificacion_ben) {
		this.identificacion_ben = identificacion_ben;
	}

	public boolean isRegistra_doc_pdf() {
		return registra_doc_pdf;
	}

	public void setRegistra_doc_pdf(boolean registra_doc_pdf) {
		this.registra_doc_pdf = registra_doc_pdf;
	}

	public String getId_procesamiento() {
		return id_procesamiento;
	}

	public void setId_procesamiento(String id_procesamiento) {
		this.id_procesamiento = id_procesamiento;
	}

	public String getId_sufix_txt() {
		return id_sufix_txt;
	}

	public void setId_sufix_txt(String id_sufix_txt) {
		this.id_sufix_txt = id_sufix_txt;
	}
	
	

}
