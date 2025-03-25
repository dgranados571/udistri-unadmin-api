package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificaciones_app")
public class NotificacionesApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre_evento")
	private String nombre_evento;

	@Column(name = "notifica_usuario")
	private boolean notifica_usuario;
	
	@Column(name = "correos_notifica", length = 520)
	private String correos_notifica;
	
	public NotificacionesApp() {
		super();
	}
	
	public NotificacionesApp(String nombre_evento, boolean notifica_usuario, String correos_notifica) {
		super();
		this.nombre_evento = nombre_evento;
		this.notifica_usuario = notifica_usuario;
		this.correos_notifica = correos_notifica;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre_evento() {
		return nombre_evento;
	}

	public void setNombre_evento(String nombre_evento) {
		this.nombre_evento = nombre_evento;
	}

	public boolean isNotifica_usuario() {
		return notifica_usuario;
	}

	public void setNotifica_usuario(boolean notifica_usuario) {
		this.notifica_usuario = notifica_usuario;
	}

	public String getCorreos_notifica() {
		return correos_notifica;
	}

	public void setCorreos_notifica(String correos_notifica) {
		this.correos_notifica = correos_notifica;
	}
	
}
