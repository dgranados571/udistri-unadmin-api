package com.co.dgc.uadmin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.co.dgc.uadmin.entity.SolicitudesApp;
import com.co.dgc.uadmin.enums.EnumConstantes;

public class EmailUtil extends Thread {
	
	private int threadNumber;
	private String destinatario;
	private String numeroRadicado;
	private String nameOperationEvent;
	private String resultOperacion;
	private SolicitudesApp solicitudApp;
	

	public EmailUtil(int threadNumber, String destinatario, String numeroRadicado, SolicitudesApp solicitudApp, String nameOperationEvent, String resultOperacion) {
		super();
		this.threadNumber = threadNumber;
		this.destinatario = destinatario;
		this.numeroRadicado = numeroRadicado;
		this.solicitudApp = solicitudApp;
		this.nameOperationEvent = nameOperationEvent;
		this.resultOperacion = resultOperacion;
	}

	public void run() {
		if (correoValido()) {
			notificaOperacion();
		} else {
			System.out.println("Finaliza Ejecución: Correo NO valido");
		}
	}
	
	public boolean correoValido() {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.destinatario);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void notificaOperacion() {
		String asunto = "VIVIENDA RURAL DIGNA -- " + this.numeroRadicado;
		String cuerpo = "";
			
		switch (this.nameOperationEvent) {
		case EnumConstantes.EVENTO_CREA_SOLICITUD:
			cuerpo = plantillaGenerica();
			break;
		case EnumConstantes.EVENTO_PREAPROBADO:
			cuerpo = plantillaPreaprobado();
			break;
		case EnumConstantes.EVENTO_NO_PREAPROBADO:
			cuerpo = plantillaNoPreaprobado();
			break;
		case EnumConstantes.EVENTO_DEVUELTO_GESTION:
			cuerpo = plantillaGenerica();
			break;
		case EnumConstantes.EVENTO_ASIGNA_A_REVISION:
			cuerpo = plantillaGenerica();
			break;
		case EnumConstantes.EVENTO_ESTUDIO_VIABILIDAD:
			cuerpo = plantillaGenerica();
			break;
		default:
			cuerpo = plantillaGenerica();
			break;
		}		
		enviarConGMail(destinatario, asunto, cuerpo);
	}

	public String plantillaPreaprobado() {
		
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES")); 
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() +" " + this.solicitudApp.getApellidos(); 
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio(); 
		
		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<title></title>\r\n"
				+ "	<style>\r\n"
				+ "\r\n"
				+ "		p {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			text-align: justify;\r\n"
				+ "			margin: 5px;\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		li {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.p-negrita {\r\n"
				+ "			font-weight: bold;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-padre {\r\n"
				+ "			width: 100%;\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: center;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image {\r\n"
				+ "			width: 16rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-firma {\r\n"
				+ "			width: 8rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.image-logo {\r\n"
				+ "			width: 100%;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-firmas-container {\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: space-between;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-template\" style=\"padding: 2rem;\">\r\n"
				+ "		<div class=\"div-image-padre\">\r\n"
				+ "			<div class=\"div-image\">\r\n"
				+ "				<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_vivienda_rural.PNG\"\r\n"
				+ "					alt=\"Logo Vivienda Rural\" class=\"image-logo\">\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Bogotá, " + fechaFormateada + "</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Señor(a): " + nombresCompletos + "</p>\r\n"
				+ "		<p>Municipio: " + municipioDepartamento + "</p>\r\n"
				+ "		<p>VIVIENDA RURAL DIGNA</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Respetado(a) Señor(a):</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Con mucha alegría, nos permitimos informarle que, tras la revisión de los documentos\r\n"
				+ "			proporcionados, usted ha sido <span class=\"p-negrita\">Preaprobado(a)</span> para avanzar a la siguiente fase\r\n"
				+ "			del programa\r\n"
				+ "			<span class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span> Este es un paso significativo hacia la realización de\r\n"
				+ "			su sueño de\r\n"
				+ "			vivienda, y queremos felicitarle por su compromiso y esfuerzo.\r\n"
				+ "		</p>\r\n"
				+ "		<p>Para continuar, le solicitamos amablemente que nos proporcione los siguientes documentos, los\r\n"
				+ "			cuales son esenciales para realizar el análisis técnico de viabilidad:\r\n"
				+ "		</p>\r\n"
				+ "		<ol>\r\n"
				+ "			<li>Certificado de disponibilidad de agua (emitido por las Juntas de Acción Comunal o el\r\n"
				+ "				Acueducto Veredal).</li>\r\n"
				+ "			<li>Certificado de disponibilidad de energía (emitido por Enel-Codensa).</li>\r\n"
				+ "			<li>Certificado del uso del suelo (solicítelo en la alcaldía de su municipio).</li>\r\n"
				+ "			<li>Certificado de no riesgo (emitido por Planeación Municipal).</li>\r\n"
				+ "			<li>Formulario de verificación documental (entregado por nuestra empresa).</li>\r\n"
				+ "		</ol>\r\n"
				+ "		<p>Estamos seguros de que este es un paso más hacia un futuro lleno de nuevas oportunidades para\r\n"
				+ "			usted y su familia. Quedamos atentos a recibir estos documentos y a resolver cualquier duda que\r\n"
				+ "			pueda tener.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Cordialmente,</p>\r\n"
				+ "		<br />\r\n"
				+ "		<div class=\"div-firmas-container\">\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/firma_rafa_lozano.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "				<p>_______________________________</p>\r\n"
				+ "				<p class=\"p-negrita\">RAFAEL EDUARDO LOZANO</p>\r\n"
				+ "				<p class=\"p-negrita\">Proyecto: VIVIENDA RURAL DIGNA</p>\r\n"
				+ "				<p class=\"p-negrita\">FRANCO OSORIO INVESTMENT S.A.S. </p>\r\n"
				+ "			</div>\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_franco_osorio.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return template;		
	}
	
	public String plantillaNoPreaprobado() {

		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() + " " + this.solicitudApp.getApellidos();
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio();

		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<title></title>\r\n"
				+ "	<style>\r\n"
				+ "		.container-template {\r\n"
				+ "			padding: 2rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		p {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			text-align: justify;\r\n"
				+ "			margin: 5px;\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		li {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.p-negrita {\r\n"
				+ "			font-weight: bold;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-padre {\r\n"
				+ "			width: 100%;\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: center;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image {\r\n"
				+ "			width: 16rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-firma {\r\n"
				+ "			width: 8rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.image-logo {\r\n"
				+ "			width: 100%;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-firmas-container {\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: space-between;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-template\">\r\n"
				+ "		<div class=\"div-image-padre\">\r\n"
				+ "			<div class=\"div-image\">\r\n"
				+ "				<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_vivienda_rural.PNG\"\r\n"
				+ "					alt=\"Logo Vivienda Rural\" class=\"image-logo\">\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Bogotá, " + fechaFormateada + "</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Señor(a): " + nombresCompletos + "</p>\r\n"
				+ "		<p>Municipio: " + municipioDepartamento + "</p>\r\n"
				+ "		<p>VIVIENDA RURAL DIGNA</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Respetado(a) Señor(a):</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Con profundo respeto, queremos agradecerle por su participación en el programa\r\n"
				+ "			<span class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span>\r\n"
				+ "			Tras un análisis detallado de los documentos proporcionados, lamentamos informarle que\r\n"
				+ "			en esta ocasión no ha sido posible otorgarle la pre aprobación para avanzar a la siguiente fase del\r\n"
				+ "			proceso.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Queremos expresar nuestra más sincera empatía y reconocer el esfuerzo que usted ha demostrado\r\n"
				+ "			en su aplicación. Aunque este resultado pueda ser desalentador, queremos que sepa que seguimos\r\n"
				+ "			comprometidos con nuestra misión de apoyar a las comunidades rurales, y su postulación seguirá\r\n"
				+ "			siendo considerada en futuros programas o subsidios.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Le animamos a mantenerse en contacto con nosotros para conocer nuevas oportunidades. Su\r\n"
				+ "			compromiso y dedicación son una inspiración para seguir trabajando por el bienestar de nuestras\r\n"
				+ "			comunidades.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Cordialmente,</p>\r\n"
				+ "		<br />\r\n"
				+ "		<div class=\"div-firmas-container\">\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/firma_rafa_lozano.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "				<p>_______________________________</p>\r\n"
				+ "				<p class=\"p-negrita\">RAFAEL EDUARDO LOZANO</p>\r\n"
				+ "				<p class=\"p-negrita\">Proyecto: VIVIENDA RURAL DIGNA</p>\r\n"
				+ "				<p class=\"p-negrita\">FRANCO OSORIO INVESTMENT S.A.S. </p>\r\n"
				+ "			</div>\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_franco_osorio.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return template;
	}
	
	public String plantillaViabilidadEstudiosTecnicos() {
		
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() + " " + this.solicitudApp.getApellidos();
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio();
		
		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<title></title>\r\n"
				+ "	<style>\r\n"
				+ "		.container-template {\r\n"
				+ "			padding: 2rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		p {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			text-align: justify;\r\n"
				+ "			margin: 5px;\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		li {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.p-negrita {\r\n"
				+ "			font-weight: bold;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-padre {\r\n"
				+ "			width: 100%;\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: center;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image {\r\n"
				+ "			width: 16rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-firma {\r\n"
				+ "			width: 8rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.image-logo {\r\n"
				+ "			width: 100%;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-firmas-container {\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: space-between;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-template\">\r\n"
				+ "		<div class=\"div-image-padre\">\r\n"
				+ "			<div class=\"div-image\">\r\n"
				+ "				<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_vivienda_rural.PNG\"\r\n"
				+ "					alt=\"Logo Vivienda Rural\" class=\"image-logo\">\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Bogotá, " + fechaFormateada + "</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Señor(a): " + nombresCompletos + "</p>\r\n"
				+ "		<p>Municipio: " + municipioDepartamento + "</p>\r\n"
				+ "		<p>VIVIENDA RURAL DIGNA</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Respetado(a) Señor(a):</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Nos complace informarle que, tras un exhaustivo análisis técnico, usted ha sido declarado(a)\r\n"
				+ "			<span class=\"p-negrita\">Viable Técnicamente</span> para avanzar a la siguiente fase del programa <span\r\n"
				+ "				class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span> Este es un\r\n"
				+ "			logro significativo y un testimonio de su esfuerzo y compromiso.\r\n"
				+ "		</p>\r\n"
				+ "		<p>\r\n"
				+ "			Para dar continuidad al proceso, le solicitamos que nos proporcione los siguientes documentos\r\n"
				+ "			indispensables:\r\n"
				+ "		</p>\r\n"
				+ "		<ol>\r\n"
				+ "			<li>Registro fotográfico del lote.</li>\r\n"
				+ "			<li>Copia de la última escritura del predio.</li>\r\n"
				+ "			<li>Paz y salvo del impuesto predial.</li>\r\n"
				+ "			<li>Documentos de los vecinos (formato proporcionado).</li>\r\n"
				+ "			<li>Notificación a vecinos (formato proporcionado).</li>\r\n"
				+ "			<li>Certificado de solicitud de licencia.</li>\r\n"
				+ "			<li>Poder para solicitud de licencia.</li>\r\n"
				+ "			<li>Firmar planos y formato notarial.</li>\r\n"
				+ "			<li>Radicado de la licencia. </li>\r\n"
				+ "			<li>Certificación de avalúo catastral. </li>\r\n"
				+ "			<li>Certificación de nomenclatura.</li>\r\n"
				+ "			<li>Formulario de postulación.</li>\r\n"
				+ "			<li>Certificado de responsabilidad documental (formato proporcionado).</li>\r\n"
				+ "			<li>Formulario de cartilla de especificaciones (formato proporcionado).</li>\r\n"
				+ "			<li>Contrato de obra.</li>\r\n"
				+ "			<li>Contrato de voluntariado. </li>\r\n"
				+ "			<li>Formulario de declaración jurada.</li>\r\n"
				+ "		</ol>\r\n"
				+ "		<p>\r\n"
				+ "			Estamos muy emocionados de que continúe en esta importante iniciativa y reiteramos nuestro\r\n"
				+ "			compromiso de acompañarle en cada etapa del proceso.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Cordialmente,</p>\r\n"
				+ "		<br />\r\n"
				+ "		<div class=\"div-firmas-container\">\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/firma_rafa_lozano.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "				<p>_______________________________</p>\r\n"
				+ "				<p class=\"p-negrita\">RAFAEL EDUARDO LOZANO</p>\r\n"
				+ "				<p class=\"p-negrita\">Proyecto: VIVIENDA RURAL DIGNA</p>\r\n"
				+ "				<p class=\"p-negrita\">FRANCO OSORIO INVESTMENT S.A.S. </p>\r\n"
				+ "			</div>\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_franco_osorio.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return template;
	}
	
	public String plantillaObtencionSubsidio() {

		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() + " " + this.solicitudApp.getApellidos();
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio();

		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<title></title>\r\n"
				+ "	<style>\r\n"
				+ "		.container-template {\r\n"
				+ "			padding: 2rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		p {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			text-align: justify;\r\n"
				+ "			margin: 5px;\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		li {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.p-negrita {\r\n"
				+ "			font-weight: bold;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-padre {\r\n"
				+ "			width: 100%;\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: center;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image {\r\n"
				+ "			width: 16rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-firma {\r\n"
				+ "			width: 8rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.image-logo {\r\n"
				+ "			width: 100%;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-firmas-container {\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: space-between;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-template\">\r\n"
				+ "		<div class=\"div-image-padre\">\r\n"
				+ "			<div class=\"div-image\">\r\n"
				+ "				<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_vivienda_rural.PNG\"\r\n"
				+ "					alt=\"Logo Vivienda Rural\" class=\"image-logo\">\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Bogotá, " + fechaFormateada + "</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Señor(a): " + nombresCompletos + "</p>\r\n"
				+ "		<p>Municipio: " + municipioDepartamento + "</p>\r\n"
				+ "		<p>VIVIENDA RURAL DIGNA</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Respetado(a) Señor(a):</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Con inmensa alegría y profunda gratitud, nos permitimos notificarle que usted ha sido\r\n"
				+ "			seleccionado(a) como <span class=\"p-negrita\">Beneficiario(a) del Subsidio</span> del programa <span\r\n"
				+ "				class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span> Este\r\n"
				+ "			logro representa no solo su esfuerzo y dedicación, sino también nuestro compromiso de construir un\r\n"
				+ "			futuro mejor para las comunidades rurales de nuestro país.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Queremos felicitarle por este paso tan significativo. Usted y su familia pronto contarán con un hogar\r\n"
				+ "			digno, un espacio que será testigo de sus sueños, sus logros y los momentos más valiosos de su\r\n"
				+ "			vida. Con la expedición de la licencia de construcción, iniciaremos las obras para que muy pronto\r\n"
				+ "			pueda disfrutar de su nueva vivienda.\r\n"
				+ "		</p>\r\n"
				+ "		<p>\r\n"
				+ "			Desde el inicio, este programa se diseñó con la visión de transformar vidas, y es gracias a personas\r\n"
				+ "			como usted que esa misión cobra sentido. Nos llena de orgullo ser parte de su historia y acompañarle\r\n"
				+ "			en este viaje hacia un futuro lleno de esperanza y nuevas oportunidades.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Gracias por permitirnos ser parte de este hermoso proceso. Su confianza y compromiso inspiran\r\n"
				+ "			todo lo que hacemos.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Con admiración y respeto, </p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Cordialmente,</p>\r\n"
				+ "		<br />\r\n"
				+ "		<div class=\"div-firmas-container\">\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/firma_rafa_lozano.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "				<p>_______________________________</p>\r\n"
				+ "				<p class=\"p-negrita\">RAFAEL EDUARDO LOZANO</p>\r\n"
				+ "				<p class=\"p-negrita\">Proyecto: VIVIENDA RURAL DIGNA</p>\r\n"
				+ "				<p class=\"p-negrita\">FRANCO OSORIO INVESTMENT S.A.S. </p>\r\n"
				+ "			</div>\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_franco_osorio.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return template;
	}
	
	public String plantillaNoViable() {
		
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() + " " + this.solicitudApp.getApellidos();
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio();
		
		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<title></title>\r\n"
				+ "	<style>\r\n"
				+ "		.container-template {\r\n"
				+ "			padding: 2rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		p {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			text-align: justify;\r\n"
				+ "			margin: 5px;\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		li {\r\n"
				+ "			font-family: 'Calibri';\r\n"
				+ "			font-size: 18px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.p-negrita {\r\n"
				+ "			font-weight: bold;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-padre {\r\n"
				+ "			width: 100%;\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: center;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image {\r\n"
				+ "			width: 16rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-image-firma {\r\n"
				+ "			width: 8rem;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.image-logo {\r\n"
				+ "			width: 100%;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.div-firmas-container {\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: space-between;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-template\">\r\n"
				+ "		<div class=\"div-image-padre\">\r\n"
				+ "			<div class=\"div-image\">\r\n"
				+ "				<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_vivienda_rural.PNG\"\r\n"
				+ "					alt=\"Logo Vivienda Rural\" class=\"image-logo\">\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Bogotá, " + fechaFormateada + "</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Señor(a): " + nombresCompletos + "</p>\r\n"
				+ "		<p>Municipio: " + municipioDepartamento + "</p>\r\n"
				+ "		<p>VIVIENDA RURAL DIGNA</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Respetado(a) Señor(a):</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Queremos agradecerle sinceramente por su participación y el esfuerzo demostrado en el programa\r\n"
				+ "			<span class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span> Sin embargo, tras un detallado análisis técnico,\r\n"
				+ "			lamentamos informarle\r\n"
				+ "			que en esta ocasión no ha sido posible considerarle viable técnicamente para avanzar a la siguiente\r\n"
				+ "			fase.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Entendemos que esta noticia puede ser desalentadora, pero queremos asegurarle que seguimos\r\n"
				+ "			comprometidos con nuestro propósito de trabajar por el bienestar de las comunidades rurales. Su\r\n"
				+ "			postulación seguirá siendo valorada en futuras iniciativas y programas que podamos implementar.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Quedamos a su disposición para atender cualquier inquietud y le animamos a mantenerse en\r\n"
				+ "			contacto para futuras oportunidades.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Cordialmente,</p>\r\n"
				+ "		<br />\r\n"
				+ "		<div class=\"div-firmas-container\">\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/firma_rafa_lozano.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "				<p>_______________________________</p>\r\n"
				+ "				<p class=\"p-negrita\">RAFAEL EDUARDO LOZANO</p>\r\n"
				+ "				<p class=\"p-negrita\">Proyecto: VIVIENDA RURAL DIGNA</p>\r\n"
				+ "				<p class=\"p-negrita\">FRANCO OSORIO INVESTMENT S.A.S. </p>\r\n"
				+ "			</div>\r\n"
				+ "			<div>\r\n"
				+ "				<div class=\"div-image-padre\">\r\n"
				+ "					<div class=\"div-image-firma\">\r\n"
				+ "						<img src=\"https://appuadminbucket.s3.us-east-1.amazonaws.com/SOURCES_APP/IMAGENES_EMAIL/logo_franco_osorio.PNG\"\r\n"
				+ "							alt=\"Firma\" class=\"image-logo\">\r\n"
				+ "					</div>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return template;
	}

	public String plantillaGenerica() {
		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<title></title>\r\n"
				+ "	<style>\r\n"
				+ "		body {\r\n"
				+ "			font-family: 'Ancizar sans', 'Tahoma', 'Geneva', 'sans-serif';\r\n"
				+ "		}\r\n"
				+ "		.no-radica {\r\n"
				+ "			font-size: 15px;\r\n"
				+ "			font-weight: bold;\r\n"
				+ "		}\r\n"
				+ "		.container-template {\r\n"
				+ "			padding: 2rem;\r\n"
				+ "		}\r\n"
				+ "		p {\r\n"
				+ "			text-align: justify;\r\n"
				+ "		}\r\n"
				+ "		.div-info-padre {\r\n"
				+ "			display: flex;\r\n"
				+ "			justify-content: flex-start;\r\n"
				+ "			flex-direction: column;\r\n"
				+ "		}\r\n"
				+ "		.p-label {\r\n"
				+ "			font-weight: bold;\r\n"
				+ "			margin-left: 2rem;\r\n"
				+ "		}\r\n"
				+ "		.p-label-text {\r\n"
				+ "			margin-left: 2rem;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"container-template\">\r\n"
				+ "		<h3>Se ha registrado una nueva operación</h3>\r\n"
				+ "		<p>Informamos la siguiente operación asociada a la solicitud: <span class=\"no-radica\">" + this.numeroRadicado + " </span>\r\n"
				+ "		</p>\r\n"
				+ "		<div class=\"div-info-padre\">\r\n"
				+ "			<p class=\"p-label-text \">La solicitud a sido resuelta bajo la siguiente casuística:</p>\r\n"
				+ "			<p class=\"p-label\">"+ this.resultOperacion +"</p>\r\n"
				+ "		</div>\r\n"
				+ "		<p>En caso de requerir mayor información, por favor escribe al correo \r\n"
				+ "			<span class=\"no-radica\">gestiongobierno.francoosorio@gmail.com </span>\r\n"
				+ "		</p>\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		return template;
	}

	public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		String remitente = "notificaciones.francoosorio@gmail.com";
		String claveemail = "jdva fzma vscd dfqs";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", claveemail);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587"); 
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			message.setContent(cuerpo, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, claveemail);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			System.out.println("Error --> " + me);
			me.printStackTrace();
		}
	}

	public int getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(int threadNumber) {
		this.threadNumber = threadNumber;
	}
	
	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getNumeroRadicado() {
		return numeroRadicado;
	}

	public void setNumeroRadicado(String numeroRadicado) {
		this.numeroRadicado = numeroRadicado;
	}
	
	public String getNameOperationEvent() {
		return nameOperationEvent;
	}

	public void setNameOperationEvent(String nameOperationEvent) {
		this.nameOperationEvent = nameOperationEvent;
	}

	public String getResultOperacion() {
		return resultOperacion;
	}

	public void setResultOperacion(String resultOperacion) {
		this.resultOperacion = resultOperacion;
	}

	public SolicitudesApp getSolicitudApp() {
		return solicitudApp;
	}

	public void setSolicitudApp(SolicitudesApp solicitudApp) {
		this.solicitudApp = solicitudApp;
	}

}
