package com.co.dgc.uadmin.request;

import java.util.ArrayList;
import java.util.List;

import com.co.dgc.uadmin.dto.BeneficiariosDto;

public class RqRegistraSolicitud {

	private String nombres;
	private String apellidos;
	private String numeroIdentificacion;
	private String correo;
	private String telefono;
	private String matriculaInmobiliaria;
	private String municipio;
	private String descripcion;
	private List<BeneficiariosDto> beneficiariosList = new ArrayList<BeneficiariosDto>();
	
	public RqRegistraSolicitud() {
		super();
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

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
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

	public List<BeneficiariosDto> getBeneficiariosList() {
		return beneficiariosList;
	}

	public void setBeneficiariosList(List<BeneficiariosDto> beneficiariosList) {
		this.beneficiariosList = beneficiariosList;
	}

	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}

	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	

	
}
