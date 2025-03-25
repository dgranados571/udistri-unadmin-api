package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventos_solicitudes")
public class EventosSolicitudesApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "id_procesamiento")
	private String id_procesamiento;
	
	@Column(name = "fecha_evento")
	private String fecha_evento;
	
	@Column(name = "userApp")
	private String userApp;
	
	@Column(name = "nombre_operacion")
	private String nombre_operacion;
	
	@Column(name = "resultado_operacion")
	private String resultado_operacion;
	
	@Column(name = "observaciones")
	private String observaciones;

	public EventosSolicitudesApp() {
		super();
	}

	public EventosSolicitudesApp(String id_procesamiento, String fecha_evento, String userApp, 
			String nombre_operacion, String resultado_operacion, String observaciones) {
		super();
		this.id_procesamiento = id_procesamiento;
		this.fecha_evento = fecha_evento;
		this.userApp = userApp;
		this.nombre_operacion = nombre_operacion;
		this.resultado_operacion = resultado_operacion;
		this.observaciones = observaciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getId_procesamiento() {
		return id_procesamiento;
	}

	public void setId_procesamiento(String id_procesamiento) {
		this.id_procesamiento = id_procesamiento;
	}

	public String getFecha_evento() {
		return fecha_evento;
	}

	public void setFecha_evento(String fecha_evento) {
		this.fecha_evento = fecha_evento;
	}

	public String getUserApp() {
		return userApp;
	}

	public void setUserApp(String userApp) {
		this.userApp = userApp;
	}

	public String getNombre_operacion() {
		return nombre_operacion;
	}

	public void setNombre_operacion(String nombre_operacion) {
		this.nombre_operacion = nombre_operacion;
	}

	public String getResultado_operacion() {
		return resultado_operacion;
	}

	public void setResultado_operacion(String resultado_operacion) {
		this.resultado_operacion = resultado_operacion;
	}
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
