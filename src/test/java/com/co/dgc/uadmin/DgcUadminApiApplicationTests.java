package com.co.dgc.uadmin;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.dgc.uadmin.entity.BeneficiariosApp;
import com.co.dgc.uadmin.entity.NotificacionesApp;
import com.co.dgc.uadmin.entity.SolicitudesApp;
import com.co.dgc.uadmin.enums.EnumConstantes;
import com.co.dgc.uadmin.request.RqRegistraSolicitud;
import com.co.dgc.uadmin.services.IBeneficiariosAppService;
import com.co.dgc.uadmin.services.IDepartamentosAppService;
import com.co.dgc.uadmin.services.IEventosSolicitudesAppService;
import com.co.dgc.uadmin.services.IMunicipiosAppService;
import com.co.dgc.uadmin.services.INotificacionesAppService;
import com.co.dgc.uadmin.services.ISolicitudesAppService;
import com.co.dgc.uadmin.services.IUserAppService;
import com.co.dgc.uadmin.util.AwsUtil;
import com.co.dgc.uadmin.util.EmailUtil;
import com.co.dgc.uadmin.util.UadminAppUtil;
import com.google.gson.Gson;

@SpringBootTest
class DgcUadminApiApplicationTests {

	@Autowired
	IUserAppService iUserAppService;

	@Autowired
	ISolicitudesAppService iSolicitudesAppService;

	@Autowired
	IBeneficiariosAppService iBeneficiariosAppService;

	@Autowired
	IEventosSolicitudesAppService iEventosSolicitudesAppService;
	
	@Autowired
	INotificacionesAppService iNotificacionesAppService;
	
	@Autowired
	IDepartamentosAppService iDepartamentosAppService;

	@Autowired
	IMunicipiosAppService iMunicipiosAppService;

	@Test
	void contextLoads() {
			
	}
	
	public void envioCorreo(String eventoNotificacion) {
		String resultadoOperacion = UadminAppUtil.getResultOperation(eventoNotificacion);
		List<NotificacionesApp> notificacionesEvento = iNotificacionesAppService.getNotificacionesPorEvento(eventoNotificacion);			
		if(!notificacionesEvento.isEmpty()) {	
			 String correos = notificacionesEvento.get(0).getCorreos_notifica();
			 String[] listaCorreos = correos.split(",\\s*");				 
			 if(listaCorreos.length > 0) {
				 for(String correo:listaCorreos) {					 
					 String idDefSolicitud = "Identificacion de prueba";					 
					 EmailUtil emailUtilThread = new EmailUtil(listaCorreos.length, correo, idDefSolicitud, new SolicitudesApp(), eventoNotificacion, resultadoOperacion);
					 emailUtilThread.start();					 
				 }	 
			 }
		}
	}
	
	public void crearNotificacionEvento() {
		inicializaNotificacionEvento(EnumConstantes.EVENTO_PREAPROBADO);
		inicializaNotificacionEvento(EnumConstantes.EVENTO_NO_PREAPROBADO);
		inicializaNotificacionEvento(EnumConstantes.EVENTO_DEVUELTO_GESTION);
		inicializaNotificacionEvento(EnumConstantes.EVENTO_ASIGNA_A_REVISION);
	}
	
	public void inicializaNotificacionEvento(String nombreEvento) {		
		List<NotificacionesApp> notificacioneEvento = iNotificacionesAppService.getNotificacionesPorEvento(nombreEvento);		
		if(notificacioneEvento.isEmpty()) {
			iNotificacionesAppService.registraEventoNotificacion(new NotificacionesApp(nombreEvento, false, ""));
		}
	}

	public void eliminaDocumetnBeneficioario(String idProcesamiento, String idDocumento) {
		int posicionUltimoGuionBajo = idDocumento.lastIndexOf('_');
		String suFixTxt = idDocumento.substring(posicionUltimoGuionBajo + 1);
		System.out.println("SuFixTxt -- " + suFixTxt);
	}

	public void actualizaDocumetnBeneficioario(String idProcesamiento) {
		String strErroTxt = "";
		String suFixTxt = strErroTxt.substring(strErroTxt.indexOf(".txt") - 1);
		char primerCaracter = suFixTxt.charAt(0);
		int indexTxt = Character.getNumericValue(primerCaracter);
		List<BeneficiariosApp> beneficiariosAppList = iBeneficiariosAppService
				.getBeneficiariosPorIdProcesamiento(idProcesamiento);
		BeneficiariosApp bencontrolDocument = beneficiariosAppList.get(indexTxt);
		bencontrolDocument.setRegistra_doc_pdf(true);
		iBeneficiariosAppService.registraBeneficiarioApp(bencontrolDocument);

	}

	public void eliminarBeneficiarios(String idProcesamiento) {
		List<BeneficiariosApp> beneficiariosList = iBeneficiariosAppService.getBeneficiariosPorIdProcesamiento(idProcesamiento);
		for (BeneficiariosApp benId : beneficiariosList) {
			iBeneficiariosAppService.eliminarBeneficiarioApp(benId);
		}
	}

	public void gsonActions() {
		String body = "{\"nombres\":\"Angelica Maria Pena U\"}";
		Gson gson = new Gson();
		RqRegistraSolicitud bodyRq = gson.fromJson(body, RqRegistraSolicitud.class);
		System.out.println("RQ OBJECT --> " + bodyRq.getNombres());
	}

	public void awsUtilActions() {
		AwsUtil.eliminarCarpetasS3("12142023185845", EnumConstantes.DIR_FILES_AWS);
	}

	public void eliminarMasivamenteCarpetasS3() {
		AwsUtil.eliminarMasivamenteCarpetasS3(EnumConstantes.DIR_FILES_AWS);
	}


}
