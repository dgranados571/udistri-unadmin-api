package com.co.dgc.uadmin.dto;

import java.util.ArrayList;
import java.util.List;

import com.co.dgc.uadmin.entity.EventosSolicitudesApp;
import com.co.dgc.uadmin.entity.SolicitudesApp;

public class SolicitudAppDto {

	private String idDefSolicitud;
	private String departamentoMunicipioLabel;
	private SolicitudesApp solicitud = new SolicitudesApp();
	private List<DocumentosDto> urlDocumentsMod1 = new ArrayList<>();
	private List<DocumentosDto> urlDocumentsMod2 = new ArrayList<>();
	private List<DocumentosDto> urlDocumentsMod3 = new ArrayList<>();
	private List<DocumentosDto> urlDocumentsModAnexos = new ArrayList<>();
	private List<DocumentosDto> urlImages = new ArrayList<>();
	private List<BeneficiariosDto> beneficiariosList = new ArrayList<>();
	private List<EventosSolicitudesApp> eventosSolicitud = new ArrayList<>();
	private String gestionSolicitud;
	private String diasUltimaActualizacion;

	public SolicitudAppDto() {
		super();
	}

	public SolicitudAppDto(String idDefSolicitud, String departamentoMunicipioLabel, SolicitudesApp solicitud, 
			List<DocumentosDto> urlDocumentsMod1, List<DocumentosDto> urlDocumentsMod2,
			List<DocumentosDto> urlDocumentsMod3, List<DocumentosDto> urlDocumentsModAnexos, List<DocumentosDto> urlImages,
			List<BeneficiariosDto> beneficiariosList, List<EventosSolicitudesApp> eventosSolicitud,
			String gestionSolicitud, String diasUltimaActualizacion) {
		super();
		this.idDefSolicitud = idDefSolicitud;
		this.departamentoMunicipioLabel = departamentoMunicipioLabel;
		this.solicitud = solicitud;
		this.urlDocumentsMod1 = urlDocumentsMod1;
		this.urlDocumentsMod2 = urlDocumentsMod2;
		this.urlDocumentsMod3 = urlDocumentsMod3;
		this.urlDocumentsModAnexos = urlDocumentsModAnexos;
		this.urlImages = urlImages;
		this.beneficiariosList = beneficiariosList;
		this.eventosSolicitud = eventosSolicitud;
		this.gestionSolicitud = gestionSolicitud;
		this.diasUltimaActualizacion = diasUltimaActualizacion; 
	}

	public String getIdDefSolicitud() {
		return idDefSolicitud;
	}

	public void setIdDefSolicitud(String idDefSolicitud) {
		this.idDefSolicitud = idDefSolicitud;
	}

	public SolicitudesApp getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudesApp solicitud) {
		this.solicitud = solicitud;
	}

	public List<DocumentosDto> getUrlDocumentsMod1() {
		return urlDocumentsMod1;
	}

	public void setUrlDocumentsMod1(List<DocumentosDto> urlDocumentsMod1) {
		this.urlDocumentsMod1 = urlDocumentsMod1;
	}

	public List<BeneficiariosDto> getBeneficiariosList() {
		return beneficiariosList;
	}

	public void setBeneficiariosList(List<BeneficiariosDto> beneficiariosList) {
		this.beneficiariosList = beneficiariosList;
	}

	public List<EventosSolicitudesApp> getEventosSolicitud() {
		return eventosSolicitud;
	}

	public void setEventosSolicitud(List<EventosSolicitudesApp> eventosSolicitud) {
		this.eventosSolicitud = eventosSolicitud;
	}

	public String getGestionSolicitud() {
		return gestionSolicitud;
	}

	public void setGestionSolicitud(String gestionSolicitud) {
		this.gestionSolicitud = gestionSolicitud;
	}

	public List<DocumentosDto> getUrlDocumentsMod2() {
		return urlDocumentsMod2;
	}

	public void setUrlDocumentsMod2(List<DocumentosDto> urlDocumentsMod2) {
		this.urlDocumentsMod2 = urlDocumentsMod2;
	}

	public List<DocumentosDto> getUrlDocumentsMod3() {
		return urlDocumentsMod3;
	}

	public void setUrlDocumentsMod3(List<DocumentosDto> urlDocumentsMod3) {
		this.urlDocumentsMod3 = urlDocumentsMod3;
	}

	public List<DocumentosDto> getUrlDocumentsModAnexos() {
		return urlDocumentsModAnexos;
	}

	public void setUrlDocumentsModAnexos(List<DocumentosDto> urlDocumentsModAnexos) {
		this.urlDocumentsModAnexos = urlDocumentsModAnexos;
	}

	public List<DocumentosDto> getUrlImages() {
		return urlImages;
	}

	public void setUrlImages(List<DocumentosDto> urlImages) {
		this.urlImages = urlImages;
	}

	public String getDepartamentoMunicipioLabel() {
		return departamentoMunicipioLabel;
	}

	public void setDepartamentoMunicipioLabel(String departamentoMunicipioLabel) {
		this.departamentoMunicipioLabel = departamentoMunicipioLabel;
	}

	public String getDiasUltimaActualizacion() {
		return diasUltimaActualizacion;
	}

	public void setDiasUltimaActualizacion(String diasUltimaActualizacion) {
		this.diasUltimaActualizacion = diasUltimaActualizacion;
	}

}
