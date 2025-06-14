package com.co.dgc.uadmin.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.co.dgc.uadmin.dto.BeneficiariosDto;
import com.co.dgc.uadmin.dto.DocumentosDto;
import com.co.dgc.uadmin.dto.MunicipiosDto;
import com.co.dgc.uadmin.dto.SolicitudAppDto;
import com.co.dgc.uadmin.entity.BeneficiariosApp;
import com.co.dgc.uadmin.entity.DepartamentosApp;
import com.co.dgc.uadmin.entity.EventosSolicitudesApp;
import com.co.dgc.uadmin.entity.MunicipiosApp;
import com.co.dgc.uadmin.entity.NotificacionesApp;
import com.co.dgc.uadmin.entity.SolicitudesApp;
import com.co.dgc.uadmin.entity.UserApp;
import com.co.dgc.uadmin.enums.EnumConstantes;
import com.co.dgc.uadmin.request.RqGetSolicitudesApp;
import com.co.dgc.uadmin.response.GenericResponse;
import com.co.dgc.uadmin.services.IDepartamentosAppService;
import com.co.dgc.uadmin.services.IEventosSolicitudesAppService;
import com.co.dgc.uadmin.services.IMunicipiosAppService;
import com.co.dgc.uadmin.services.INotificacionesAppService;
import com.co.dgc.uadmin.services.ISolicitudesAppService;
import com.co.dgc.uadmin.services.IUserAppService;
import com.google.gson.Gson;

public class UadminAppUtil {
	
	public static List<SolicitudesApp> listaSolicitudesConFiltrosAplicados(RqGetSolicitudesApp body,
			ISolicitudesAppService iSolicitudesAppService, IEventosSolicitudesAppService iEventosSolicitudesAppService) {
		
		List<SolicitudesApp> listaSolicitudesApp = new ArrayList<>();
		try {
			listaSolicitudesApp = iSolicitudesAppService.listaSolicitudesApp();
			if (!body.getEventoFiltro().equals("INITIAL")) {
				listaSolicitudesApp = iSolicitudesAppService
						.getSolicitudesAppPorEventoSolicitud(body.getEventoFiltro());
			}
			if (!body.getNombreFiltro().isEmpty()) {
				if (!body.getEventoFiltro().equals("INITIAL")) {
					listaSolicitudesApp = iSolicitudesAppService.getSolicitudesAppPorEventoSolicitudYNoDocumento(
							body.getEventoFiltro(), body.getNombreFiltro());
				} else {
					listaSolicitudesApp = iSolicitudesAppService
							.getSolicitudesAppContieneNoDocumento(body.getNombreFiltro());
				}
			}
			if (!body.getFaseFiltro().equals("INITIAL")) {
				listaSolicitudesApp = listaSolicitudesFiltroFase(listaSolicitudesApp,
						body.getFaseFiltro());
			}

			if (!body.getDepartamentoFiltro().equals("INITIAL")) {
				listaSolicitudesApp = listaSolicitudesFiltroDepartamento(listaSolicitudesApp,
						body.getDepartamentoFiltro());
			}
			if (!body.getMunicipioFiltro().equals("INITIAL")) {
				listaSolicitudesApp = listaSolicitudesFiltroMunicipio(listaSolicitudesApp,
						body.getMunicipioFiltro());
			}
			if (!body.getDiasUltimaActualizacionFiltro().equals("INITIAL")) {
				listaSolicitudesApp = listaSolicitudesFiltroDiasUltimaActualizacion(listaSolicitudesApp,
						body.getDiasUltimaActualizacionFiltro(), iEventosSolicitudesAppService);
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
		return listaSolicitudesApp;
	}
	
	
	public static List<MunicipiosDto> listaMunicipiosAppPaginada(List<MunicipiosDto> municipiosDtoList, int elementosPorPagina, int paginaActual) {
		List<MunicipiosDto> municipiosDtoListPost = new ArrayList<>();
		int rangoInicial = (paginaActual * elementosPorPagina) - elementosPorPagina;
		for (int i = 0; i < municipiosDtoList.size(); i++) {
			if ((i + 1) > rangoInicial) {
				municipiosDtoListPost.add(municipiosDtoList.get(i));
			}
			if (municipiosDtoListPost.size() == elementosPorPagina) {
				break;
			}
		}
		return municipiosDtoListPost;
	}
	
	public static String obtencionModuloDeGestion(String step, String role) {
		
		String gestionSolicitud = "";
		if (role.equals(EnumConstantes.ROLE_1) || role.equals(EnumConstantes.ROLE_3)) {
			switch (step) {
			case EnumConstantes.STEP_4:
				gestionSolicitud = "MODULO_4";
				break;
			case EnumConstantes.STEP_5:
				gestionSolicitud = "MODULO_5";
				break;
			case EnumConstantes.STEP_7:
				gestionSolicitud = "";
				break;
			default:
				gestionSolicitud = "MODULO_0";
				break;
			}
		} else if (role.equals(EnumConstantes.ROLE_2)) {
			switch (step) {
			case EnumConstantes.STEP_1:
				gestionSolicitud = "MODULO_1";
				break;
			case EnumConstantes.STEP_2:
				gestionSolicitud = "MODULO_2";
				break;
			case EnumConstantes.STEP_3:
				gestionSolicitud = "MODULO_3";
				break;
			case EnumConstantes.STEP_6:
				gestionSolicitud = "MODULO_6";
				break;
			default:
				break;
			}
		}
		return gestionSolicitud;
	}
	
	public static String obtencionFasePorStep(String step) {
		String faseStr = "N/A";
		switch (step) {
		case EnumConstantes.STEP_1:
			faseStr = "Fase 1";
			break;
		case EnumConstantes.STEP_2:
			faseStr = "Fase 2";
			break;
		case EnumConstantes.STEP_3:
			faseStr = "Fase 3";
			break;
		case EnumConstantes.STEP_4:
			faseStr = "Fase 4";
			break;
		case EnumConstantes.STEP_5:
			faseStr = "Fase 5";
			break;
		case EnumConstantes.STEP_6:
			faseStr = "Fase 6";
			break;
		case EnumConstantes.STEP_7:
			faseStr = "Finalizada";
			break;
		default:
			break;
		}
		return faseStr;
	}
		
	public static List<SolicitudAppDto> listaSolicitudesAppDtoPaginada(List<SolicitudesApp> listaSolicitudesApp,
			int elementosPorPagina, int paginaActual) {
		List<SolicitudAppDto> listaSolicitudesAppDto = new ArrayList<>();
		int rangoInicial = (paginaActual * elementosPorPagina) - elementosPorPagina;
		for (int i = 0; i < listaSolicitudesApp.size(); i++) {
			if ((i + 1) > rangoInicial) {
				SolicitudAppDto solicitudAppDto = new SolicitudAppDto("", "", listaSolicitudesApp.get(i),
						new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
						new ArrayList<>(), null, "", "");
				listaSolicitudesAppDto.add(solicitudAppDto);
			}
			if (listaSolicitudesAppDto.size() == elementosPorPagina) {
				break;
			}
		}
		return listaSolicitudesAppDto;
	}
	
	public static List<SolicitudesApp> listaSolicitudesFiltroFase(List<SolicitudesApp> listaSolicitudesApp, String faseStep) {
		List<SolicitudesApp> solicitudesApp = new ArrayList<>();
		try {
			for (int i = 0; i < listaSolicitudesApp.size(); i++) {
				String currentStep = listaSolicitudesApp.get(i).getCurrent_step();
				if (currentStep.equals(faseStep)) {
					solicitudesApp.add(listaSolicitudesApp.get(i));
				}
			}	
		} catch (Exception e) {
			solicitudesApp = listaSolicitudesApp;
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
		return solicitudesApp;
	}
	
	public static List<SolicitudesApp> listaSolicitudesFiltroDepartamento(List<SolicitudesApp> listaSolicitudesApp, String departamentoFiltro) {
		List<SolicitudesApp> solicitudesApp = new ArrayList<>();
		try {
			for (int i = 0; i < listaSolicitudesApp.size(); i++) {
				String departamento_municipio = listaSolicitudesApp.get(i).getDepartamento_municipio();
				String[] partesDM = departamento_municipio.split(":");
				if (partesDM.length > 0) {
					if (partesDM[0].equals(departamentoFiltro)) {
						solicitudesApp.add(listaSolicitudesApp.get(i));
					}
				}
			}	
		} catch (Exception e) {
			solicitudesApp = listaSolicitudesApp;
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
		return solicitudesApp;
	}
	
	public static List<SolicitudesApp> listaSolicitudesFiltroMunicipio(List<SolicitudesApp> listaSolicitudesApp, String municipioFiltro) {
		List<SolicitudesApp> solicitudesApp = new ArrayList<>();
		try {
			for (int i = 0; i < listaSolicitudesApp.size(); i++) {
				String departamento_municipio = listaSolicitudesApp.get(i).getDepartamento_municipio();
				String[] partesDM = departamento_municipio.split(":");
				if (partesDM.length > 0) {
					if (partesDM[1].equals(municipioFiltro)) {
						solicitudesApp.add(listaSolicitudesApp.get(i));
					}
				}
			}	
		} catch (Exception e) {
			solicitudesApp = listaSolicitudesApp;
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}		
		return solicitudesApp;
	}

	public static List<SolicitudesApp> listaSolicitudesFiltroDiasUltimaActualizacion(List<SolicitudesApp> listaSolicitudesApp, String diasUltimaActualizacionFiltro, IEventosSolicitudesAppService iEventosSolicitudesAppService ) {
		List<SolicitudesApp> solicitudesApp = new ArrayList<>();
		try {
			switch (diasUltimaActualizacionFiltro) {
			case "OPTION_1":
				for (int i = 0; i < listaSolicitudesApp.size(); i++) {
					long diasUltimaActualizacion = obtieneDiasUltimaActualizacion(listaSolicitudesApp.get(i).getId_procesamiento(), iEventosSolicitudesAppService);
					if(diasUltimaActualizacion <= 5) {
						solicitudesApp.add(listaSolicitudesApp.get(i));
					}	
				}
				break;
			case "OPTION_2":
				for (int i = 0; i < listaSolicitudesApp.size(); i++) {
					long diasUltimaActualizacion = obtieneDiasUltimaActualizacion(listaSolicitudesApp.get(i).getId_procesamiento(), iEventosSolicitudesAppService);
					if(diasUltimaActualizacion > 5 && diasUltimaActualizacion <= 15) {
						solicitudesApp.add(listaSolicitudesApp.get(i));
					}
				}
				break;
			case "OPTION_3":
				for (int i = 0; i < listaSolicitudesApp.size(); i++) {
					long diasUltimaActualizacion = obtieneDiasUltimaActualizacion(listaSolicitudesApp.get(i).getId_procesamiento(), iEventosSolicitudesAppService);
					if(diasUltimaActualizacion > 15) {
						solicitudesApp.add(listaSolicitudesApp.get(i));
					}
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			solicitudesApp = listaSolicitudesApp;
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
		return solicitudesApp;
	}
	
	public static long obtieneDiasUltimaActualizacion(String idProsesamiento, IEventosSolicitudesAppService iEventosSolicitudesAppService) {
		SimpleDateFormat sdfComplete = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		long diasUltimaActualizacion = 0L;
		try {
			List<EventosSolicitudesApp> eventosSolicitud = iEventosSolicitudesAppService.getESAPorIdProcesamiento(idProsesamiento);			
			if(!eventosSolicitud.isEmpty()) {
				Date ya = new Date();
				Date dateEventFormat = sdfComplete.parse(eventosSolicitud.get(eventosSolicitud.size() - 1).getFecha_evento());
				long diffInMillies = Math.abs(ya.getTime() - dateEventFormat.getTime());
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				diasUltimaActualizacion = diffInDays;				
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}		
		return diasUltimaActualizacion;
	}
	
	public static String obtineLabelDepartamentoMunicipio(String departamento_municipio, IDepartamentosAppService iDepartamentosAppService, IMunicipiosAppService iMunicipiosAppService) {
		String[] partesDM = departamento_municipio.split(":");
		String labelDM = departamento_municipio.replace(':', '-');
		if(partesDM.length > 0) {
			try {
				DepartamentosApp departamentosApp = iDepartamentosAppService.getDepartamentosAppPorIdDepartamento(partesDM[0]);
				MunicipiosApp municipiosApp = iMunicipiosAppService.getMunicipiosPorIdDeDepartamentoIdDeMunicipio(partesDM[0], partesDM[1]);
				labelDM = municipiosApp.getMunicipio() + " - " + departamentosApp.getDepartamento();
			} catch (Exception e) {
				System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			}
		}
		return labelDM;
	}
	
	public static String getIdDefSolicitud(SolicitudesApp solicitudApp) {
		String departamentoMunicipio = solicitudApp.getDepartamento_municipio().replace(':', '.');
		String primerNombre = obtenerPrimerElemento(solicitudApp.getNombres());
		String primerApellido = obtenerPrimerElemento(solicitudApp.getApellidos());
		String nombreCompleto = primerApellido + " " + primerNombre;
		return departamentoMunicipio + "." + nombreCompleto.trim() + "." + solicitudApp.getNumero_identificacion().trim();
	}

	private static String obtenerPrimerElemento(String cadena) {
		String[] partes = cadena.split(" ");
		return partes.length > 0 ? partes[0] : "";
	}
	
	public static String getResultOperation(String nameOperationEvent) {
		String resultadoOperacion = "";
		switch (nameOperationEvent) {
		
		case EnumConstantes.EVENTO_ASIGNA_A_REVISION:
			resultadoOperacion = EnumConstantes.RESULT_ASIGNA_A_REVISION;
			break;
			
		case EnumConstantes.EVENTO_CREA_SOLICITUD:
			resultadoOperacion = EnumConstantes.RESULT_CREA_SOLICITUD;
			break;
		case EnumConstantes.EVENTO_PREAPROBADO:
			resultadoOperacion = EnumConstantes.RESULT_PREAPROBADO;
			break;
		case EnumConstantes.EVENTO_NO_PREAPROBADO:
			resultadoOperacion = EnumConstantes.RESULT_NO_PREAPROBADO;
			break;
		case EnumConstantes.EVENTO_DEVUELTO_GESTION_CREADOR_SOLICITUD:
			resultadoOperacion = EnumConstantes.RESULT_DEVUELTO_GESTION_CREADOR_SOLICITUD;
			break;
			
			
		case EnumConstantes.EVENTO_ESTUDIO_VIABILIDAD:
			resultadoOperacion = EnumConstantes.RESULT_ESTUDIO_VIABILIDAD;
			break;
		case EnumConstantes.EVENTO_DEVUELTO_GESTION:
			resultadoOperacion = EnumConstantes.RESULT_DEVUELTO_GESTION;
			break;
		case EnumConstantes.EVENTO_NO_APROBADO:
			resultadoOperacion = EnumConstantes.RESULT_NO_APROBADO;
			break;	
			

		case EnumConstantes.EVENTO_FACTIBLE_ACTUALIZACION:
			resultadoOperacion = EnumConstantes.RESULT_FACTIBLE_ACTUALIZACION;
			break;
			
		case EnumConstantes.EVENTO_LICENCIAR:
			resultadoOperacion = EnumConstantes.RESULT_LICENCIAR;
			break;
		
		case EnumConstantes.EVENTO_LICENCIA_SUBSIDIO:
			resultadoOperacion = EnumConstantes.RESULT_LICENCIA_SUBSIDIO;
			break;
			
		case EnumConstantes.EVENTO_VO_BO_SUBSIDIO:
			resultadoOperacion = EnumConstantes.RESULT_VO_BO_SUBSIDIO;
			break;
			
		default:
			break;
		}
		return resultadoOperacion;
	}

	public static List<DocumentosDto> getListaDocumentosModuloX(List<String> documentos) {
		List<DocumentosDto> documentosDtoList = new ArrayList<>();
		for (String str : documentos) {
			DocumentosDto documentosDto = new DocumentosDto();
			documentosDto.setUrlTxt(str);
			String sufixNaleFile= extraerNombreArchivoSinExtension(str); 
			String suFixTxt = sufixNaleFile.substring(sufixNaleFile.lastIndexOf('_') + 1) ;
			switch (suFixTxt + ".txt") {
			case "1.txt":
				documentosDto.setNombreArchivo("Documento de identidad");
				break;
			case "2.txt":
				documentosDto.setNombreArchivo("Certificado de libertad y tradición");
				break;
			case "3.txt":
				documentosDto.setNombreArchivo("Impuesto predial");
				break;
			case "4.txt":
				documentosDto.setNombreArchivo("Certificado de disponibilidad agua");
				break;
			case "5.txt":
				documentosDto.setNombreArchivo("Certificado de disponibilidad energía");
				break;
			case "6.txt":
				documentosDto.setNombreArchivo("Certificado uso del suelo");
				break;
			case "7.txt":
				documentosDto.setNombreArchivo("Certificado de no riesgo");
				break;
			case "8.txt":
				documentosDto.setNombreArchivo("Lista de verificación documental");
				break;
			case "9.txt":
				documentosDto.setNombreArchivo("Copia de la última escritura del predio");
				break;
			case "10.txt":
				documentosDto.setNombreArchivo("Paz y salvo del impuesto predial");
				break;
			case "11.txt":
				documentosDto.setNombreArchivo("Documentos de los vecinos");
				break;
			case "12.txt":
				documentosDto.setNombreArchivo("Notificación a vecinos");
				break;
			case "13.txt":
				documentosDto.setNombreArchivo("Certificado de solicitud de licencia");
				break;
			case "14.txt":
				documentosDto.setNombreArchivo("Poder para solicitud de licencia");
				break;
			case "15.txt":
				documentosDto.setNombreArchivo("Firmar planos y formato notarial");
				break;
			case "16.txt":
				documentosDto.setNombreArchivo("Radicado de la licencia");
				break;
			case "17.txt":
				documentosDto.setNombreArchivo("Certificación de avalúo catastral");
				break;
			case "18.txt":
				documentosDto.setNombreArchivo("Certificación de nomenclatura");
				break;
			case "19.txt":
				documentosDto.setNombreArchivo("Formulario de postulación");
				break;
			case "20.txt":
				documentosDto.setNombreArchivo("Certificado de responsabilidad documental");
				break;
			case "21.txt":
				documentosDto.setNombreArchivo("Formulario de cartilla de especificaciones");
				break;
			case "22.txt":
				documentosDto.setNombreArchivo("Contrato de obra");
				break;
			case "23.txt":
				documentosDto.setNombreArchivo("Contrato de voluntariado");
				break;
			case "24.txt":
				documentosDto.setNombreArchivo("Formulario de declaración jurada");
				break;				
			default:
				documentosDto.setNombreArchivo("");
				break;
			}
			documentosDtoList.add(documentosDto);
		}
		return documentosDtoList;
	}
	
	public static List<DocumentosDto> getListaDocumentosModuloAnexos(List<String> documentos) {
		List<DocumentosDto> documentosDtoList = new ArrayList<>();
		for (String str : documentos) {
			DocumentosDto documentosDto = new DocumentosDto();
			documentosDto.setUrlTxt(str);
			documentosDto.setNombreArchivo(extraerNombreArchivoSinExtension(str));
			documentosDtoList.add(documentosDto);
		}
		return documentosDtoList;
	}
	
	public static List<DocumentosDto> getListaDocumentosModuloImages(List<String> documentos) {
		List<DocumentosDto> documentosDtoList = new ArrayList<>();
		for (String str : documentos) {
			DocumentosDto documentosDto = new DocumentosDto();
			documentosDto.setUrlTxt(str);
			documentosDto.setNombreArchivo("Image_" + extraerNombreArchivoSinExtension(str));
			documentosDtoList.add(documentosDto);
		}
		return documentosDtoList;
	}
	
	public static String extraerNombreArchivoSinExtension(String url) {  
		String[] partes = url.split("/");  
		String nombreArchivo = partes.length > 0 ? partes[partes.length - 1] : "";  
		int indiceUltimoPunto = nombreArchivo.lastIndexOf("."); 
		if (indiceUltimoPunto > 0) { 
			nombreArchivo = nombreArchivo.substring(0, indiceUltimoPunto); 
		} 
		return nombreArchivo; 
	}	
	
	public static List<BeneficiariosDto> getListaBeneficiarios(List<BeneficiariosApp> beneficiariosList, List<String> documentosModBen) {
		List<BeneficiariosDto> beneficiariosDtoList = new ArrayList<BeneficiariosDto>();		
		for (int i = 0; i < beneficiariosList.size(); i++) {				
			BeneficiariosDto beneficiariosDto = new BeneficiariosDto(beneficiariosList.get(i));			
			if(beneficiariosList.get(i).isRegistra_doc_pdf()) {				
				for (String str: documentosModBen) {
					int posicionUltimoGuionBajo = str.lastIndexOf('_');					
					String suFixTxt = str.substring(posicionUltimoGuionBajo + 1);					
					if(suFixTxt.equals(beneficiariosList.get(i).getId_sufix_txt())) {						
						beneficiariosDto.setDocumentosDto(new DocumentosDto("Documento", str));
						break;
					}
				}				
			}			
			beneficiariosDtoList.add(beneficiariosDto);	
		}
		return beneficiariosDtoList;
	}

	public static GenericResponse reporteEventos(IEventosSolicitudesAppService iEventosSolicitudesAppService, EventosSolicitudesApp eventosSolicitudesApp) {
		boolean estado = true;
		String mensaje = "";
		try {
			iEventosSolicitudesAppService.registraEventoSolicitud(eventosSolicitudesApp);
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, new GenericResponse());
	}

	public static GenericResponse registraUsuarioRoot(IUserAppService iUserAppService) {
		boolean estado = true;
		String mensaje = "";
		try {
			SimpleDateFormat sdfRegistro = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY);
			SimpleDateFormat sdf = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
			String idProcesamiento = sdf.format(new Date());
			UserApp userAppRegistro = new UserApp(EnumConstantes.ROOT, PasswordUtil.encodePassword(EnumConstantes.ROOT),
					EnumConstantes.ROOT, EnumConstantes.ROOT, "CC", "123456789", "root@root.rr",
					EnumConstantes.ROLE_ROOT, sdfRegistro.format(new Date()), false, idProcesamiento,
					EnumConstantes.ROOT);
			iUserAppService.registraUsuarioApp(userAppRegistro);
			mensaje = EnumConstantes.MSG_REGITRO_ROOT_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_REGITRO_ROOT_FAIL;
		}
		return new GenericResponse(estado, mensaje, new GenericResponse());
	}
	
	public static void actualizaNotificacionEvento(INotificacionesAppService iNotificacionesAppService) {		
		String[] eventosAnotificar = {
				EnumConstantes.EVENTO_PREAPROBADO, EnumConstantes.EVENTO_ESTUDIO_VIABILIDAD, EnumConstantes.EVENTO_LICENCIA_SUBSIDIO, 
				EnumConstantes.EVENTO_NO_APROBADO + ";" + EnumConstantes.STEP_2
		};
		List<NotificacionesApp> notificacionesList = iNotificacionesAppService.getNotificaciones();
		for(NotificacionesApp nAId:notificacionesList) {
			boolean eventoValido = Arrays.asList(eventosAnotificar).contains(nAId.getNombre_evento());
			if(!eventoValido) {
				iNotificacionesAppService.eliminarEventoNotificacion(nAId);
			}
		}
		for(String nombreEvento:eventosAnotificar) {
			List<NotificacionesApp> notificacioneEvento = iNotificacionesAppService.getNotificacionesPorEvento(nombreEvento);
			if(notificacioneEvento.isEmpty()) {
				iNotificacionesAppService.registraEventoNotificacion(new NotificacionesApp(nombreEvento, false, ""));
			}	
		}		
	}
	
	public static <T> String gsonActionsToString(T object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	public static <T> T gsonActionsToObj(String body, Class<T> clazz) {
		Gson gson = new Gson();
		T bodyRq = gson.fromJson(body, clazz);
		return bodyRq;
	}


}
