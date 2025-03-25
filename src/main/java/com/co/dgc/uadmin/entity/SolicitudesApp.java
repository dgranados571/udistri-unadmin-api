package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.co.dgc.uadmin.request.RqRegistraSolicitud;
import com.co.dgc.uadmin.util.UadminAppUtil;

@Entity
@Table(name = "solicitudes_app")
public class SolicitudesApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "numero_identificacion")
	private String numero_identificacion;

	@Column(name = "correo")
	private String correo;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "matricula_inmobiliaria")
	private String matricula_inmobiliaria;
	
	@Column(name = "departamento_municipio")
	private String departamento_municipio;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_registro")
	private String fecha_registro;

	@Column(name = "evento_actual")
	private String evento_actual;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "current_step")
	private String current_step;

	@Column(name = "id_procesamiento")
	private String id_procesamiento;

	public SolicitudesApp() {
		super();
	}
	
	public SolicitudesApp(RqRegistraSolicitud body, String fecha_registro, String evento_actual, String id_procesamiento, String current_step) {
		super();
		this.nombres = body.getNombres();
		this.apellidos = body.getApellidos();
		this.numero_identificacion = body.getNumeroIdentificacion();
		this.correo = body.getCorreo();
		this.telefono = body.getTelefono();
		this.matricula_inmobiliaria = body.getMatriculaInmobiliaria();
		this.departamento_municipio = body.getMunicipio();
		this.descripcion = body.getDescripcion();
		this.fecha_registro = fecha_registro;
		this.evento_actual = evento_actual;
		this.estado = UadminAppUtil.getResultOperation(evento_actual);
		this.current_step = current_step;
		this.id_procesamiento = id_procesamiento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNumero_identificacion() {
		return numero_identificacion;
	}

	public void setNumero_identificacion(String numero_identificacion) {
		this.numero_identificacion = numero_identificacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getId_procesamiento() {
		return id_procesamiento;
	}

	public void setId_procesamiento(String id_procesamiento) {
		this.id_procesamiento = id_procesamiento;
	}

	public String getMatricula_inmobiliaria() {
		return matricula_inmobiliaria;
	}

	public void setMatricula_inmobiliaria(String matricula_inmobiliaria) {
		this.matricula_inmobiliaria = matricula_inmobiliaria;
	}

	public String getDepartamento_municipio() {
		return departamento_municipio;
	}

	public void setDepartamento_municipio(String departamento_municipio) {
		this.departamento_municipio = departamento_municipio;
	}

	public String getEvento_actual() {
		return evento_actual;
	}

	public void setEvento_actual(String evento_actual) {
		this.evento_actual = evento_actual;
	}
	
	public String getCurrent_step() {
		return current_step;
	}

	public void setCurrent_step(String current_step) {
		this.current_step = current_step;
	}
	
	
}
