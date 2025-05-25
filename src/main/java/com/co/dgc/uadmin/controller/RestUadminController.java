package com.co.dgc.uadmin.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.dgc.uadmin.dto.BeneficiariosDto;
import com.co.dgc.uadmin.dto.DocumentosDto;
import com.co.dgc.uadmin.dto.MunicipiosDto;
import com.co.dgc.uadmin.dto.NotificacionesAppDto;
import com.co.dgc.uadmin.dto.SolicitudAppDto;
import com.co.dgc.uadmin.dto.SolicitudesAppDto;
import com.co.dgc.uadmin.dto.UserAppDto;
import com.co.dgc.uadmin.entity.BeneficiariosApp;
import com.co.dgc.uadmin.entity.DepartamentosApp;
import com.co.dgc.uadmin.entity.EventosSolicitudesApp;
import com.co.dgc.uadmin.entity.MunicipiosApp;
import com.co.dgc.uadmin.entity.NotificacionesApp;
import com.co.dgc.uadmin.entity.SolicitudesApp;
import com.co.dgc.uadmin.entity.UserApp;
import com.co.dgc.uadmin.enums.EnumConstantes;
import com.co.dgc.uadmin.request.RqActivacionUsuarioApp;
import com.co.dgc.uadmin.request.RqActualizaContrasenia;
import com.co.dgc.uadmin.request.RqActualizaNotificacionesEventos;
import com.co.dgc.uadmin.request.RqActualizaSolicitud;
import com.co.dgc.uadmin.request.RqControlRegistroBeneficiarios;
import com.co.dgc.uadmin.request.RqEjecutaEventoyEstado;
import com.co.dgc.uadmin.request.RqEliminaBeneficiario;
import com.co.dgc.uadmin.request.RqEliminaRegistroS3;
import com.co.dgc.uadmin.request.RqEliminarMunicipioDepartamento;
import com.co.dgc.uadmin.request.RqEliminarSolicitud;
import com.co.dgc.uadmin.request.RqEliminarUsuario;
import com.co.dgc.uadmin.request.RqGetBeneficiariosSolicitud;
import com.co.dgc.uadmin.request.RqGetMunicipiosPorDepartamento;
import com.co.dgc.uadmin.request.RqGetNotificacionesEventos;
import com.co.dgc.uadmin.request.RqGetSolicitudApp;
import com.co.dgc.uadmin.request.RqGetSolicitudesApp;
import com.co.dgc.uadmin.request.RqGetUsuariosApp;
import com.co.dgc.uadmin.request.RqLoginApp;
import com.co.dgc.uadmin.request.RqObtieneDocumento;
import com.co.dgc.uadmin.request.RqRegistraBeneficiario;
import com.co.dgc.uadmin.request.RqRegistraDepartamento;
import com.co.dgc.uadmin.request.RqRegistraMunicipio;
import com.co.dgc.uadmin.request.RqRegistraSolicitud;
import com.co.dgc.uadmin.request.RqRegistroUsuarioApp;
import com.co.dgc.uadmin.response.GenericResponse;
import com.co.dgc.uadmin.services.IBeneficiariosAppService;
import com.co.dgc.uadmin.services.IDepartamentosAppService;
import com.co.dgc.uadmin.services.IEventosSolicitudesAppService;
import com.co.dgc.uadmin.services.IMunicipiosAppService;
import com.co.dgc.uadmin.services.INotificacionesAppService;
import com.co.dgc.uadmin.services.ISolicitudesAppService;
import com.co.dgc.uadmin.services.IUserAppService;
import com.co.dgc.uadmin.util.AwsUtil;
import com.co.dgc.uadmin.util.EmailUtil;
import com.co.dgc.uadmin.util.PasswordUtil;
import com.co.dgc.uadmin.util.UadminAppUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RequestMapping(path = "/service/uadmin")
public class RestUadminController {

	@Autowired
	IUserAppService iUserAppService;

	@Autowired
	ISolicitudesAppService iSolicitudesAppService;

	@Autowired
	IBeneficiariosAppService iBeneficiariosAppService;

	@Autowired
	IDepartamentosAppService iDepartamentosAppService;

	@Autowired
	IMunicipiosAppService iMunicipiosAppService;

	@Autowired
	IEventosSolicitudesAppService iEventosSolicitudesAppService;
	
	@Autowired
	INotificacionesAppService iNotificacionesAppService;
	
	@PostMapping(path = "/ejecutaEventoyEstado", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse ejecutaEventoYEstadoSolicitud(@RequestBody RqEjecutaEventoyEstado body) {
		boolean estado = true;
		String mensaje = "";
		SimpleDateFormat sdf = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		try {
			SolicitudesApp solicitudApp = iSolicitudesAppService.getSolicitudAppPorIdPocesamiento(body.getIdProcesamiento());			
			String resultadoOperacion = UadminAppUtil.getResultOperation(body.getNombreOperacion());			
			solicitudApp.setEvento_actual(body.getNombreOperacion());
			solicitudApp.setEstado(resultadoOperacion);			
			switch (body.getNombreOperacion()) {
			case EnumConstantes.EVENTO_PREAPROBADO:
				solicitudApp.setCurrent_step(EnumConstantes.STEP_2);
				break;
			case EnumConstantes.EVENTO_ESTUDIO_VIABILIDAD:
				solicitudApp.setCurrent_step(EnumConstantes.STEP_3);
				break;
			case EnumConstantes.EVENTO_FACTIBLE_ACTUALIZACION:
				solicitudApp.setCurrent_step(EnumConstantes.STEP_4);
				break;
			case EnumConstantes.EVENTO_LICENCIAR:
				solicitudApp.setCurrent_step(EnumConstantes.STEP_5);
				break;
			case EnumConstantes.EVENTO_LICENCIA_SUBSIDIO:
				solicitudApp.setCurrent_step(EnumConstantes.STEP_6);
				break;
			case EnumConstantes.EVENTO_VO_BO_SUBSIDIO:
				solicitudApp.setCurrent_step(EnumConstantes.STEP_7);
				break;
			case EnumConstantes.EVENTO_DEVUELTO_GESTION:
				if(solicitudApp.getCurrent_step().equals(EnumConstantes.STEP_6)) {
					solicitudApp.setCurrent_step(EnumConstantes.STEP_4);	
				}
				break;
			default:
				break;
			}
			iSolicitudesAppService.registraSolicitudApp(solicitudApp);			
			EventosSolicitudesApp eventosSolicitudesApp = new EventosSolicitudesApp(
					body.getIdProcesamiento(), 
					sdf.format(new Date()), 
					body.getUserApp(), 
					body.getNombreOperacion(), 
					resultadoOperacion, 
					body.getObservaciones());
			UadminAppUtil.reporteEventos(iEventosSolicitudesAppService, eventosSolicitudesApp);			
			List<NotificacionesApp> notificacionesEvento = iNotificacionesAppService.getNotificacionesPorEvento(body.getNombreOperacion());		
			if (!notificacionesEvento.isEmpty()) {
				String correos = notificacionesEvento.get(0).getCorreos_notifica();
				String[] listaCorreos = correos.split(",\\s*");
				String idDefSolicitud = UadminAppUtil.getIdDefSolicitud(solicitudApp);
				if (listaCorreos.length > 0) {
					String labelDM = UadminAppUtil.obtineLabelDepartamentoMunicipio(solicitudApp.getDepartamento_municipio(), iDepartamentosAppService, iMunicipiosAppService);
					solicitudApp.setDepartamento_municipio(labelDM);
					for (String correo : listaCorreos) {						
						EmailUtil emailUtilThread = new EmailUtil(listaCorreos.length, correo, idDefSolicitud, solicitudApp, body.getNombreOperacion(), resultadoOperacion);
						emailUtilThread.start();
					}
				}
				if (notificacionesEvento.get(0).isNotifica_usuario()) {					
					EmailUtil emailUtilThread = new EmailUtil(0, solicitudApp.getCorreo(), idDefSolicitud, solicitudApp, body.getNombreOperacion(), resultadoOperacion);
					emailUtilThread.start();
				}
			}			
			mensaje = EnumConstantes.MSG_SUCCES_2;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			if (e.getMessage().contains(EnumConstantes.MSG_VALIDA_LONG_DATA_FAIL)) {
				mensaje = EnumConstantes.MSG_REGITRO_LONG_DATA_FAIL;
			} else {
				mensaje = EnumConstantes.MSG_FAIL;
			}
			estado = false;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/registraSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse registraSolicitud(@RequestBody RqRegistraSolicitud body) {
		boolean estado = true;
		String mensaje = "";
		SimpleDateFormat sdf = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		Date ya = new Date();
		String idProcesamiento = sdf.format(ya);
		try {
			List<SolicitudesApp> solicitudesAppValida = iSolicitudesAppService.getSolicitudesAppPorNoDocumento(body.getNumeroIdentificacion());
			if (solicitudesAppValida.isEmpty()) {
				SolicitudesApp solicitudApp = new SolicitudesApp(body, sdf.format(ya), EnumConstantes.EVENTO_CREA_SOLICITUD, idProcesamiento, EnumConstantes.STEP_1);
				iSolicitudesAppService.registraSolicitudApp(solicitudApp);
				if (!body.getBeneficiariosList().isEmpty()) {
					for (int i = 0; i < body.getBeneficiariosList().size(); i++) {						
						iBeneficiariosAppService.registraBeneficiarioApp(new BeneficiariosApp(
								body.getBeneficiariosList().get(i).getNombresBen(),
								body.getBeneficiariosList().get(i).getApellidosBen(),
								body.getBeneficiariosList().get(i).getIdentificacionBen(), 
								body.getBeneficiariosList().get(i).isRegistraDocPdf(), 
								idProcesamiento, i +".txt"));
					}
				}
				EventosSolicitudesApp eventosSolicitudesApp = new EventosSolicitudesApp(idProcesamiento, sdf.format(ya), "", 
						EnumConstantes.EVENTO_CREA_SOLICITUD, 
						EnumConstantes.RESULT_CREA_SOLICITUD, 
						body.getDescripcion());
				UadminAppUtil.reporteEventos(iEventosSolicitudesAppService, eventosSolicitudesApp);
				mensaje = EnumConstantes.MSG_REGITRO_SOLICITUD_SUCCES;
			} else {
				mensaje = EnumConstantes.MSG_REGITRO_SOLICITUD_FAIL_EXISTE;
				estado = false;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			if (e.getMessage().contains(EnumConstantes.MSG_VALIDA_LONG_DATA_FAIL)) {
				mensaje = EnumConstantes.MSG_REGITRO_LONG_DATA_FAIL;
			} else {
				mensaje = EnumConstantes.MSG_REGITRO_SOLICITUD_FAIL;
			}
			estado = false;
		}
		return new GenericResponse(estado, mensaje, idProcesamiento);
	}

	@PostMapping(path = "/getSolicitudesApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getSolicitudesApp(@RequestBody RqGetSolicitudesApp body) {
		boolean estado = true;
		String mensaje = "";		
		SimpleDateFormat sdfComplete = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		SimpleDateFormat sdfDMA = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY);
		List<SolicitudesApp> listaSolicitudesApp = new ArrayList<>();
		List<SolicitudAppDto> listaSolicitudesAppDto = new ArrayList<>();
		int totalElementos = 0;
		try {
			listaSolicitudesApp = iSolicitudesAppService.listaSolicitudesApp();
			
			if (!body.getEventoFiltro().equals("INITIAL")) {
				listaSolicitudesApp = iSolicitudesAppService.getSolicitudesAppPorEventoSolicitud(body.getEventoFiltro());
			}
			if (!body.getNombreFiltro().isEmpty()) {
				if (!body.getEventoFiltro().equals("INITIAL")) {
					listaSolicitudesApp = iSolicitudesAppService.getSolicitudesAppPorEventoSolicitudYNoDocumento(body.getEventoFiltro(), body.getNombreFiltro());
				} else {
					listaSolicitudesApp = iSolicitudesAppService.getSolicitudesAppContieneNoDocumento(body.getNombreFiltro());
				}
			}
			
			if (!body.getFaseFiltro().equals("INITIAL")) {
				listaSolicitudesApp = UadminAppUtil.listaSolicitudesFiltroFase(listaSolicitudesApp, body.getFaseFiltro());
			}
			
			if(!body.getDepartamentoFiltro().equals("INITIAL")) {				
				listaSolicitudesApp = UadminAppUtil.listaSolicitudesFiltroDepartamento(listaSolicitudesApp, body.getDepartamentoFiltro());
			}
			if(!body.getMunicipioFiltro().equals("INITIAL")) {				
				listaSolicitudesApp = UadminAppUtil.listaSolicitudesFiltroMunicipio(listaSolicitudesApp, body.getMunicipioFiltro());
			}
			if(!body.getDiasUltimaActualizacionFiltro().equals("INITIAL")) {
				listaSolicitudesApp = UadminAppUtil.listaSolicitudesFiltroDiasUltimaActualizacion(listaSolicitudesApp, body.getDiasUltimaActualizacionFiltro(), iEventosSolicitudesAppService);
			}
			totalElementos = listaSolicitudesApp.size();
			listaSolicitudesAppDto = UadminAppUtil.listaSolicitudesAppDtoPaginada(listaSolicitudesApp, body.getElementosPorPagina(), body.getPaginaActual());
			
			for (SolicitudAppDto idSolicitud : listaSolicitudesAppDto) {
				
				Date fechaRadicacion = sdfComplete.parse(idSolicitud.getSolicitud().getFecha_registro());
				String DMA = sdfDMA.format(fechaRadicacion);
				idSolicitud.getSolicitud().setFecha_registro(DMA);
				
				String labelDM = UadminAppUtil.obtineLabelDepartamentoMunicipio(idSolicitud.getSolicitud().getDepartamento_municipio(), iDepartamentosAppService, iMunicipiosAppService);
				idSolicitud.getSolicitud().setDepartamento_municipio(labelDM);
				
				long diasUltimaActualizacion = UadminAppUtil.obtieneDiasUltimaActualizacion(idSolicitud.getSolicitud().getId_procesamiento(), iEventosSolicitudesAppService);
				idSolicitud.setDiasUltimaActualizacion(diasUltimaActualizacion + " dias");
				
				idSolicitud.setFaseSolicitud(UadminAppUtil.obtencionFasePorStep(idSolicitud.getSolicitud().getCurrent_step()));
			}
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, new SolicitudesAppDto(listaSolicitudesAppDto, totalElementos));
	}

	@PostMapping(path = "/getSolicitudApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getSolicitudApp(@RequestBody RqGetSolicitudApp body) {	
		boolean estado = true;
		String mensaje = "";
		SimpleDateFormat sdfComplete = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		SimpleDateFormat sdfDMA = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY);
		String idDefSolicitud = "";
		String labelDM = "";
		SolicitudesApp solicitudApp = null;
		List<DocumentosDto> urlDocumentsMod1 = new ArrayList<>();
		List<DocumentosDto> urlDocumentsMod2 = new ArrayList<>();
		List<DocumentosDto> urlDocumentsMod3 = new ArrayList<>();
		List<DocumentosDto> urlDocumentsModAnexos = new ArrayList<>();
		List<DocumentosDto> urlImages = new ArrayList<>();
		List<BeneficiariosDto> beneficiariosList = new ArrayList<>();
		List<EventosSolicitudesApp> eventosSolicitud = new ArrayList<>();
		String gestionSolicitud = "";
		try {
			solicitudApp = iSolicitudesAppService.getSolicitudAppPorIdPocesamiento(body.getIdProcesamiento());			
			idDefSolicitud = UadminAppUtil.getIdDefSolicitud(solicitudApp);
			
			labelDM = UadminAppUtil.obtineLabelDepartamentoMunicipio(solicitudApp.getDepartamento_municipio(), iDepartamentosAppService, iMunicipiosAppService);
			
			Date fechaRadicacion = sdfComplete.parse(solicitudApp.getFecha_registro());
			String DMA = sdfDMA.format(fechaRadicacion);
			solicitudApp.setFecha_registro(DMA);
			
			List<String> documentosBucketS3 =  AwsUtil.listarCarpetasS3();
			
			List<String> documentosMod1 = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, solicitudApp.getId_procesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_MOD_1);
			urlDocumentsMod1 = UadminAppUtil.getListaDocumentosModuloX(documentosMod1);
			
			List<String> documentosMod2 = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, solicitudApp.getId_procesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_MOD_2);
			urlDocumentsMod2 = UadminAppUtil.getListaDocumentosModuloX(documentosMod2);
			
			List<String> documentosMod3 = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, solicitudApp.getId_procesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_MOD_3);
			urlDocumentsMod3 = UadminAppUtil.getListaDocumentosModuloX(documentosMod3);
			
			List<String> documentosModAnexos = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, solicitudApp.getId_procesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_MOD_ANEXOS);
			urlDocumentsModAnexos = UadminAppUtil.getListaDocumentosModuloAnexos(documentosModAnexos);
			
			List<String> urlModImages = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, solicitudApp.getId_procesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_MOD_IMAGES);
			urlImages = UadminAppUtil.getListaDocumentosModuloImages(urlModImages); 
			
			List<String> documentosModBen = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, body.getIdProcesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_BENEFICIARIOS);
			List<BeneficiariosApp> beneficiariosAppList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(body.getIdProcesamiento());
			beneficiariosList = UadminAppUtil.getListaBeneficiarios(beneficiariosAppList, documentosModBen);
			
			eventosSolicitud = iEventosSolicitudesAppService.getESAPorIdProcesamiento(body.getIdProcesamiento());			
			for(EventosSolicitudesApp idEvent: eventosSolicitud) {
				Date dateEventFromat = sdfComplete.parse(idEvent.getFecha_evento());
				String fechaEvento = sdfDMA.format(dateEventFromat);
				idEvent.setFecha_evento(fechaEvento);
			}
			
			UserApp userAPP = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuarioApp());
			gestionSolicitud = UadminAppUtil.obtencionModuloDeGestion(solicitudApp.getCurrent_step(), userAPP.getRole());
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje,
				new SolicitudAppDto(idDefSolicitud, labelDM, solicitudApp, urlDocumentsMod1, urlDocumentsMod2, urlDocumentsMod3,
						urlDocumentsModAnexos, urlImages, beneficiariosList, eventosSolicitud, gestionSolicitud, "0"));
	}
	
	@PostMapping(path = "/actualizaSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse actualizaSolicitud(@RequestBody RqActualizaSolicitud body) {
		boolean estado = true;
		String mensaje = "";
		try {
			SolicitudesApp solicitudesAppEdita = iSolicitudesAppService.getSolicitudAppPorIdPocesamiento(body.getIdProcesamiento());
			if (!solicitudesAppEdita.getNumero_identificacion().equals(body.getNumeroIdentificacion())) {
				List<SolicitudesApp> solicitudesAppValida = iSolicitudesAppService.getSolicitudesAppPorNoDocumento(body.getNumeroIdentificacion());
				if (solicitudesAppValida.isEmpty()) {
					solicitudesAppEdita.setNombres(body.getNombres());
					solicitudesAppEdita.setApellidos(body.getApellidos());
					solicitudesAppEdita.setNumero_identificacion(body.getNumeroIdentificacion());
					solicitudesAppEdita.setCorreo(body.getCorreo());
					solicitudesAppEdita.setTelefono(body.getTelefono());
					solicitudesAppEdita.setMatricula_inmobiliaria(body.getMatriculaInmobiliaria());
					solicitudesAppEdita.setDepartamento_municipio(body.getMunicipio());
					iSolicitudesAppService.registraSolicitudApp(solicitudesAppEdita);
					mensaje = EnumConstantes.MSG_ACTUALIZA_SOLICITUD_SUCCES;
				} else {
					mensaje = EnumConstantes.MSG_REGITRO_SOLICITUD_FAIL_EXISTE;
					estado = false;
				}
			} else {
				solicitudesAppEdita.setNombres(body.getNombres());
				solicitudesAppEdita.setApellidos(body.getApellidos());
				solicitudesAppEdita.setCorreo(body.getCorreo());
				solicitudesAppEdita.setTelefono(body.getTelefono());
				solicitudesAppEdita.setMatricula_inmobiliaria(body.getMatriculaInmobiliaria());
				solicitudesAppEdita.setDepartamento_municipio(body.getMunicipio());
				iSolicitudesAppService.registraSolicitudApp(solicitudesAppEdita);
				mensaje = EnumConstantes.MSG_ACTUALIZA_SOLICITUD_SUCCES;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_ACTUALIZA_SOLICITUD_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, null);
	}
	
	@PostMapping(path = "/eliminaRegistroS3", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse eliminaRegistroS3(@RequestBody RqEliminaRegistroS3 body) {
		boolean estado = true;
		String mensaje = "";
		try {
			String strTxt = body.getUrlTxt();
			String sufixTxt = UadminAppUtil.extraerNombreArchivoSinExtension(strTxt);
			AwsUtil.eliminarDocumentoS3(body.getIdProcesamiento(), body.getModuloS3(), sufixTxt);			
			mensaje = EnumConstantes.MSG_SUCCES_2;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, null);
	}
	
	@PostMapping(path = "/actualizaConfiguracionEnvioNotificaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse actualizaConfiguracionEnvioNotificaciones(@RequestBody RqActualizaNotificacionesEventos body) {
		boolean estado = true;
		String mensaje = "";
		List<NotificacionesAppDto> notificacionesListDto = new ArrayList<>();
		try {
			for (NotificacionesAppDto idNot : body.getListaNotificacion()) {
				List<NotificacionesApp> eventoValidate = iNotificacionesAppService.getNotificacionesPorEvento(idNot.getNombreEvento());
				if (!eventoValidate.isEmpty()) {
					eventoValidate.get(0).setCorreos_notifica(idNot.getCorreosNotifica());
					eventoValidate.get(0).setNotifica_usuario(idNot.isNotificaUsuario());
					iNotificacionesAppService.registraEventoNotificacion(eventoValidate.get(0));
				}
			}
			List<NotificacionesApp> notificacionesList = iNotificacionesAppService.getNotificaciones();
			for(NotificacionesApp nAId:notificacionesList) {
				String labelEvento = UadminAppUtil.getResultOperation(nAId.getNombre_evento());
				notificacionesListDto.add(new NotificacionesAppDto(nAId, labelEvento));
			}
		} catch (Exception e) {
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, notificacionesListDto);
	}
	
	@PostMapping(path = "/getConfirguracionEnvioNotificaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getConfirguracionEnvioNotificaciones(@RequestBody RqGetNotificacionesEventos body) {
		boolean estado = true;
		String mensaje = "";
		List<NotificacionesAppDto> notificacionesListDto = new ArrayList<>();
		try {			
			UadminAppUtil.crearNotificacionEvento(iNotificacionesAppService);		
			List<NotificacionesApp> notificacionesList = iNotificacionesAppService.getNotificaciones();
			for(NotificacionesApp nAId:notificacionesList) {
				String labelEvento = UadminAppUtil.getResultOperation(nAId.getNombre_evento());
				notificacionesListDto.add(new NotificacionesAppDto(nAId, labelEvento));
			}
		} catch (Exception e) {
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, notificacionesListDto);
	}
	
	@PostMapping(path = "/registraBeneficiarioSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse registraBeneficiarioSolicitud(@RequestBody RqRegistraBeneficiario body) {
		boolean estado = true;
		String mensaje = "";
		int indxNewDocument = 0;
		try {
			List<BeneficiariosApp> beneficiariosAppList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(body.getIdProcesamiento());
			if (!beneficiariosAppList.isEmpty()) {		
				String idDocumento = beneficiariosAppList.get(beneficiariosAppList.size() - 1).getId_sufix_txt();				
				String inDsuFixTxt = idDocumento.substring(0, 1);
				try {					
					indxNewDocument = Integer.parseInt(inDsuFixTxt) + 1;					
				} catch (Exception e) {
					System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
				}
			}			
			iBeneficiariosAppService.registraBeneficiarioApp(new BeneficiariosApp(
					body.getNombresBen(),
					body.getApellidosBen(),
					body.getIdentificacionBen(), 
					body.isRegistraDocPdf(), 
					body.getIdProcesamiento(), 
					indxNewDocument + ".txt"));	
			mensaje = EnumConstantes.MSG_SUCCES_2;
		}catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, indxNewDocument);
	}
	
	@PostMapping(path = "/getBeneficiariosSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getBeneficiariosSolicitud(@RequestBody RqGetBeneficiariosSolicitud body) {
		boolean estado = true;
		String mensaje = "";
		List<BeneficiariosDto> beneficiariosList = new ArrayList<>();
		try {		
			List<String> documentosBucketS3 =  AwsUtil.listarCarpetasS3();
			List<String> documentosModBen = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, body.getIdProcesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_BENEFICIARIOS);
			List<BeneficiariosApp> beneficiariosAppList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(body.getIdProcesamiento());
			beneficiariosList = UadminAppUtil.getListaBeneficiarios(beneficiariosAppList, documentosModBen);			
			mensaje = EnumConstantes.MSG_SUCCES_2;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, beneficiariosList);
	}
	
	@PostMapping(path = "/eliminaBeneficiarioSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse eliminaBeneficiarioSolicitud(@RequestBody RqEliminaBeneficiario body) {
		boolean estado = true;
		String mensaje = "";
		List<BeneficiariosDto> beneficiariosList = new ArrayList<>();
		try {			
			List<BeneficiariosApp> benedficiariosAEliminar = iBeneficiariosAppService.getBeneficiariosPorIdProcesamientoDocumento(body.getIdProcesamiento(), body.getBeneficiariosDto().getIdentificacionBen());
			for(BeneficiariosApp bApp:benedficiariosAEliminar) {
				if(bApp.isRegistra_doc_pdf()) {					
					AwsUtil.eliminarDocumentoS3(body.getIdProcesamiento(), EnumConstantes.DIR_FILES_BENEFICIARIOS, bApp.getId_sufix_txt());					
				}
				iBeneficiariosAppService.eliminarBeneficiarioApp(bApp);
			}
			
			List<String> documentosBucketS3 =  AwsUtil.listarCarpetasS3();
			List<String> documentosModBen = AwsUtil.listarCarpetasS3Filtrada(documentosBucketS3, body.getIdProcesamiento(), EnumConstantes.DIR_FILES_AWS, EnumConstantes.DIR_FILES_BENEFICIARIOS);
			
			List<BeneficiariosApp> beneficiariosAppList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(body.getIdProcesamiento());
			beneficiariosList = UadminAppUtil.getListaBeneficiarios(beneficiariosAppList, documentosModBen);			
			mensaje = EnumConstantes.MSG_SUCCES_2;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, beneficiariosList);
	}
	
	@PostMapping(path = "/controlRegistroBeneficiarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse controlRegistroBeneficiarios(@RequestBody RqControlRegistroBeneficiarios body) {
		boolean estado = true;
		String mensaje = "";
		try {
			String strErroTxt = body.getUrlTxt();			
			int posicionUltimoGuionBajo = strErroTxt.lastIndexOf('_');
			String suFixTxt = strErroTxt.substring(posicionUltimoGuionBajo + 1);			
			List<BeneficiariosApp> beneficiariosAppList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(body.getIdProcesamiento());
			for(BeneficiariosApp bAppId: beneficiariosAppList) {
				if(bAppId.getId_sufix_txt().equals(suFixTxt)) {
					bAppId.setRegistra_doc_pdf(false);
					iBeneficiariosAppService.registraBeneficiarioApp(bAppId);
					break;
				}
			}
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/eliminarSolicitud", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse eliminarSolicitud(@RequestBody RqEliminarSolicitud body) {
		boolean estado = true;
		String mensaje = "";
		try {
			AwsUtil.eliminarCarpetasS3(body.getIdProcesamiento(), EnumConstantes.DIR_FILES_AWS);
			List<EventosSolicitudesApp> eventosSolicitud = iEventosSolicitudesAppService.getESAPorIdProcesamiento(body.getIdProcesamiento());
			for (EventosSolicitudesApp eventoSolicitudId : eventosSolicitud) {
				iEventosSolicitudesAppService.eliminarEventoSolicitud(eventoSolicitudId);
			}
			List<BeneficiariosApp> beneficiariosList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(body.getIdProcesamiento());
			for (BeneficiariosApp benId : beneficiariosList) {
				iBeneficiariosAppService.eliminarBeneficiarioApp(benId);
			}
			SolicitudesApp solicitud = iSolicitudesAppService.getSolicitudAppPorIdPocesamiento(body.getIdProcesamiento());
			iSolicitudesAppService.eliminaSolicitudApp(solicitud);
			mensaje = EnumConstantes.MSG_ELIMINA_SOLICITUD_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_ELIMINA_SOLICITUD_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/getUsuariosApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getUsuariosApp(@RequestBody RqGetUsuariosApp body) {
		boolean estado = true;
		String mensaje = "";
		List<UserApp> userAppList = new ArrayList<>();
		try {
			UserApp usuarioSession = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuarioApp());
			if (usuarioSession != null) {
				if (body.getRole().isEmpty()) {
					userAppList = iUserAppService.listaUsuariosApp();
					if (!usuarioSession.getRole().equals(EnumConstantes.ROLE_ROOT)) {
						UserApp usuarioRoot = iUserAppService.obtieneUsuarioAPPporUsuario(EnumConstantes.ROOT);
						userAppList.remove(usuarioRoot);
					}
				} else {
					userAppList = iUserAppService.listaUsuariosAppPorRole(body.getRole());
				}
				for (UserApp idUser : userAppList) {
					if (idUser.isUsuario_activo()) {
						idUser.setContrasenia(EnumConstantes.CONTRASENIA_MASCARA);
					} else {
						idUser.setContrasenia(PasswordUtil.dencodePassword(idUser.getContrasenia()));
					}
				}
				mensaje = EnumConstantes.MSG_SUCCES;
			} else {
				estado = false;
				mensaje = EnumConstantes.MSG_FAIL;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, userAppList);
	}
	
	@PostMapping(path = "/registrarDepartamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse registrarDepartamento(@RequestBody RqRegistraDepartamento body) {
		boolean estado = true;
		String mensaje = "";
		SimpleDateFormat sdf = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		Date ya = new Date();
		String idProcesamiento = sdf.format(ya);
		try {
			DepartamentosApp departamentosAppValida = iDepartamentosAppService.getDepartamentosAppPorIdDepartamento(body.getIdDepartamento());
			if (departamentosAppValida == null) {
				iDepartamentosAppService.registraDepartamentosApp(new DepartamentosApp(body, idProcesamiento));
				mensaje = EnumConstantes.MSG_REGITRO_DEPARTAMENTO_SUCCES;
			} else {
				estado = false;
				mensaje = EnumConstantes.MSG_REGITRO_DEPARTAMENTO_FAIL_EXISTE;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_REGITRO_DEPARTAMENTO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/getDepartamentos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getDepartamentos() {
		boolean estado = true;
		String mensaje = "";
		List<DepartamentosApp> departamentosApp = new ArrayList<>();
		try {
			departamentosApp = iDepartamentosAppService.getDepartamentosList();
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, departamentosApp);
	}

	@PostMapping(path = "/registrarMunicipio", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse registrarMunicipio(@RequestBody RqRegistraMunicipio body) {
		boolean estado = true;
		String mensaje = "";
		SimpleDateFormat sdf = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
		Date ya = new Date();
		String idProcesamiento = sdf.format(ya);
		try {
			MunicipiosApp municipiosAppValida = iMunicipiosAppService.getMunicipiosPorIdDeDepartamentoIdDeMunicipio(body.getDepartamentoMun(), body.getIdMunicipio());
			if (municipiosAppValida == null) {
				iMunicipiosAppService.registraMunicipiosApp(new MunicipiosApp(body, idProcesamiento));
				mensaje = EnumConstantes.MSG_REGITRO_MUNICIPIO_SUCCES;
			} else {
				estado = false;
				mensaje = EnumConstantes.MSG_REGITRO_MUNICIPIO_FAIL_EXISTE;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_REGITRO_MUNICIPIO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/getMunicipios", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getMunicipios() {
		boolean estado = true;
		String mensaje = "";
		List<MunicipiosDto> municipiosDtoList = new ArrayList<>();
		try {
			List<DepartamentosApp> departamentosApp = iDepartamentosAppService.getDepartamentosList();
			for (DepartamentosApp departamentoId : departamentosApp) {
				List<MunicipiosApp> municipiosApp = iMunicipiosAppService.getMunicipiosPorIdDeDepartamento(departamentoId.getId_departamento());
				for (MunicipiosApp municipiosId : municipiosApp) {
					municipiosDtoList.add(new MunicipiosDto(municipiosId, departamentoId));
				}
			}
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, municipiosDtoList);
	}
	
	@PostMapping(path = "/getMunicipiosPorDepartamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse getMunicipiosPorDepartamento(@RequestBody RqGetMunicipiosPorDepartamento body) {
		boolean estado = true;
		String mensaje = "";
		List<MunicipiosApp> municipiosApp = new ArrayList<>();
		try {
			municipiosApp = iMunicipiosAppService.getMunicipiosPorIdDeDepartamento(body.getIdDepartamento());
			mensaje = EnumConstantes.MSG_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, municipiosApp);
	}
	
	@PostMapping(path = "/eliminarMunicipioDepartamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse eliminarMunicipioDepartamento(
			@RequestBody RqEliminarMunicipioDepartamento body) {
		boolean estado = true;
		String mensaje = "";
		try {
			iMunicipiosAppService.eliminaMunicipiosApp(body.getMunicipioDto().getMunicipioObj().getId());
			List<SolicitudesApp> solcitudeEditaIdDepartamentoMunicipio = iSolicitudesAppService
					.getSolicitudesAppPorIdDepartamentoMunicipio(
							body.getMunicipioDto().getDepartamentoMunObj().getId_departamento() + ":"
									+ body.getMunicipioDto().getMunicipioObj().getId_municipio());
			for (SolicitudesApp solId : solcitudeEditaIdDepartamentoMunicipio) {
				solId.setDepartamento_municipio("null" + ":" + "null");
				iSolicitudesAppService.registraSolicitudApp(solId);
			}
			List<MunicipiosApp> municipiosApp = iMunicipiosAppService.getMunicipiosPorIdDeDepartamento(
					body.getMunicipioDto().getDepartamentoMunObj().getId_departamento());
			if (municipiosApp.isEmpty()) {
				iDepartamentosAppService
						.eliminaDepartamentosApp(body.getMunicipioDto().getDepartamentoMunObj().getId());
			}
			mensaje = EnumConstantes.MSG_ELIMINA_MUNICIPIO_SUCCES;
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_ELIMINA_MUNICIPIO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/loginApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse loginApp(@RequestBody RqLoginApp body) {
		boolean estado = true;
		String mensaje = "";
		UserAppDto usuarioLoginDto = null;
		try {
			long totalUsuariosApp = iUserAppService.countUsuariosApp();
			if (totalUsuariosApp == 0) {
				GenericResponse registroRoot = UadminAppUtil.registraUsuarioRoot(iUserAppService);
				estado = registroRoot.isEstado();
				mensaje = registroRoot.getMensaje();
			}
			if (estado) {
				UserApp usuarioLogin = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuario());
				if (usuarioLogin == null) {
					estado = false;
					mensaje = EnumConstantes.MSG_LOGIN_FAIL;
				} else {
					if (PasswordUtil.dencodePassword(usuarioLogin.getContrasenia()).equals(body.getContrasenia())) {
						if (usuarioLogin.isUsuario_activo()) {
							usuarioLogin.setContrasenia(EnumConstantes.CONTRASENIA_MASCARA);
							mensaje = EnumConstantes.MSG_LOGIN_SUCCES;
						} else {
							mensaje = EnumConstantes.MSG_LOGIN_ACTIVATE;
						}
						usuarioLoginDto = new UserAppDto(usuarioLogin);
					} else {
						estado = false;
						mensaje = EnumConstantes.MSG_LOGIN_FAIL;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_FAIL;
		}
		return new GenericResponse(estado, mensaje, usuarioLoginDto);
	}

	@PostMapping(path = "/activacionUsuarioApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse activacionUsuarioApp(@RequestBody RqActivacionUsuarioApp body) {
		boolean estado = true;
		String mensaje = "";
		try {
			UserApp usuarioActualiza = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuario());
			if (usuarioActualiza == null) {
				estado = false;
				mensaje = EnumConstantes.MSG_ACTIVACION_USUARIO_FAIL;
			} else {
				try {
					usuarioActualiza.setUsuario_activo(true);
					usuarioActualiza.setContrasenia(PasswordUtil.encodePassword(body.getContrasenia()));
					iUserAppService.registraUsuarioApp(usuarioActualiza);
					mensaje = EnumConstantes.MSG_ACTIVACION_USUARIO_SUCCES;
				} catch (Exception e) {
					System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
					estado = false;
					mensaje = EnumConstantes.MSG_ACTIVACION_USUARIO_FAIL;
				}
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_ACTIVACION_USUARIO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/registroUsuarioApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse registroUsuarioApp(@RequestBody RqRegistroUsuarioApp body) {
		boolean estado = true;
		String mensaje = "";
		try {
			UserApp usuarioValida = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuario());
			if (usuarioValida == null) {
				SimpleDateFormat sdfRegistro = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY);
				SimpleDateFormat sdf = new SimpleDateFormat(EnumConstantes.DD_MM_YYYY_HH_MM_SS);
				String idProcesamiento = sdf.format(new Date());
				try {
					String randomPass = PasswordUtil.getPassword(8);
					UserApp userAppRegistro = new UserApp(body, PasswordUtil.encodePassword(randomPass), sdfRegistro.format(body.getFechaRegistro()), false, idProcesamiento);
					iUserAppService.registraUsuarioApp(userAppRegistro);
					mensaje = EnumConstantes.MSG_REGITRO_USUARIO_SUCCES;
				} catch (Exception e) {
					System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
					estado = false;
					mensaje = EnumConstantes.MSG_REGITRO_USUARIO_FAIL;
				}
			} else {
				estado = false;
				mensaje = EnumConstantes.MSG_REGITRO_USUARIO_YA_EXISTE + body.getUsuario();
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_REGITRO_USUARIO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/actualizaUsuarioApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse actualizaUsuarioApp(@RequestBody RqRegistroUsuarioApp body) {
		boolean estado = true;
		String mensaje = "";
		try {
			UserApp usuarioValida = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuario());
			if (usuarioValida != null) {
				try {
					usuarioValida.setNombre(body.getNombres());
					usuarioValida.setApellidos(body.getApellidos());
					usuarioValida.setTipo_identificacion(body.getTipoIdentificacion());
					usuarioValida.setIdentificacion(body.getIdentificacion());
					usuarioValida.setCorreo(body.getCorreo());
					usuarioValida.setRole(body.getRole());
					iUserAppService.registraUsuarioApp(usuarioValida);
					mensaje = EnumConstantes.MSG_ACTUALIZA_USUARIO_SUCCES;
				} catch (Exception e) {
					System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
					estado = false;
					mensaje = EnumConstantes.MSG_ACTUALIZA_USUARIO_FAIL;
				}
			} else {
				estado = false;
				mensaje = EnumConstantes.MSG_ACTUALIZA_USUARIO_FAIL + body.getUsuario();
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_ACTUALIZA_USUARIO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/actualizaContraseniaApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse actualizaContraseniaApp(@RequestBody RqActualizaContrasenia body) {
		boolean estado = true;
		String mensaje = "";
		try {
			UserApp userAppEdita = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuarioEdita().getUsuario());
			if (userAppEdita == null) {
				estado = false;
				mensaje = EnumConstantes.MSG_ACTUALIZA_PASS_FAIL;
			} else {
				userAppEdita.setContrasenia(PasswordUtil.encodePassword(PasswordUtil.getPassword(8)));
				userAppEdita.setUsuario_activo(false);
				iUserAppService.registraUsuarioApp(userAppEdita);
				mensaje = EnumConstantes.MSG_ACTUALIZA_PASS_SUCCES;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_ACTUALIZA_PASS_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}
	
	@PostMapping(path = "/eliminaUsuarioApp", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse eliminaUsuarioApp(@RequestBody RqEliminarUsuario body) {
		boolean estado = true;
		String mensaje = "";			
		try {
			UserApp userAppElimina = iUserAppService.obtieneUsuarioAPPporUsuario(body.getUsuarioElimina().getUsuario());
			if (userAppElimina == null) {
				estado = false;
				mensaje = EnumConstantes.MSG_ELIMINA_USUARIO_FAIL;
			} else {			
				iUserAppService.eliminarUsuarioApp(userAppElimina);
				mensaje = EnumConstantes.MSG_ELIMINA_USUARIO_SUCCES;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			estado = false;
			mensaje = EnumConstantes.MSG_ELIMINA_USUARIO_FAIL;
		}
		return new GenericResponse(estado, mensaje, null);
	}

	@PostMapping(path = "/obtenerDocumento", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GenericResponse obtenerDocumento(@RequestBody RqObtieneDocumento body) {
		boolean estado = true;
		String mensaje = "";
		String pdfInBase64 = null;
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(body.getUrlTxt())).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				pdfInBase64 = response.body();
				mensaje = EnumConstantes.MSG_OBTIENE_DOCUMENTOS_SUCCES;
			} else {
				mensaje = EnumConstantes.MSG_OBTIENE_DOCUMENTOS_FAIL;
				estado = false;
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
			mensaje = EnumConstantes.MSG_OBTIENE_DOCUMENTOS_FAIL;
			estado = false;
		}
		return new GenericResponse(estado, mensaje, pdfInBase64);
	}

}
