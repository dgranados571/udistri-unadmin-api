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
		case EnumConstantes.EVENTO_PREAPROBADO:
			cuerpo = plantillaPreaprobado();
			break;
		case EnumConstantes.EVENTO_ESTUDIO_VIABILIDAD:
			cuerpo = plantillaViabilidadTecnica();
			break;
		case EnumConstantes.EVENTO_LICENCIA_SUBSIDIO:
			cuerpo = plantillaObtencionSubsidio();
			break;
		case EnumConstantes.EVENTO_NO_APROBADO + ";" + EnumConstantes.STEP_2:
			cuerpo = noAprobadoFase2();
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
				+ "			Con mucha alegría, nos permitimos informarle que, tras la revisión de los documentos\r\n"
				+ "			proporcionados, usted ha sido <span class=\"p-negrita\">preaprobado(a)</span> para avanzar a la siguiente fase\r\n"
				+ "			del programa\r\n"
				+ "			<span class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span> Este es un paso significativo hacia la realización de\r\n"
				+ "			su sueño de\r\n"
				+ "			vivienda, y queremos felicitarle por su compromiso y esfuerzo.\r\n"
				+ "		</p>\r\n"
				+ "		<p>\r\n"
				+ "			Para continuar, le solicitamos amablemente que nos proporcione los siguientes documentos, los\r\n"
				+ "			cuales son esenciales para realizar el análisis técnico de viabilidad:\r\n"
				+ "		</p>\r\n"
				+ "		<ol>\r\n"
				+ "			<li>Certificado de disponibilidad de agua potable indicando que es para consumo humano y uso doméstico \r\n"
				+ "				(emitido por las Juntas de Acción Comunal o el Acueducto Veredal).</li>\r\n"
				+ "			<li>Certificado de disponibilidad de energía (emitido por Enel-Codensa).</li>\r\n"
				+ "			<li>Certificado del uso del suelo (emitido por Secretaría de Planeación Municipal).</li>\r\n"
				+ "			<li>Certificado de no riesgo (emitido por Secretaría de Planeación Municipal).</li>\r\n"
				+ "		</ol>\r\n"
				+ "		<p>\r\n"
				+ "			Estamos seguros de que este es un paso más hacia un futuro lleno de nuevas oportunidades para\r\n"
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
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		return template;		
	}
	
	public String plantillaViabilidadTecnica() {
		
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() + " " + this.solicitudApp.getApellidos();
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio();
		
		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
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
				+ "\r\n"
				+ "		.m-li-top {\r\n"
				+ "			margin-top: 5px;\r\n"
				+ "		}\r\n"
				+ "\r\n"
				+ "		.m-li {\r\n"
				+ "			margin-top: 15px;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "</head>\r\n"
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
				+ "			Nos complace informarle que, tras un exhaustivo análisis técnico, hemos determinado que usted es\r\n"
				+ "			<span class=\"p-negrita\">viable técnicamente</span> para avanzar a la siguiente fase del programa <span\r\n"
				+ "				class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span> Este es un\r\n"
				+ "			logro significativo y un testimonio de su esfuerzo y compromiso.\r\n"
				+ "		</p>\r\n"
				+ "		<p>\r\n"
				+ "			Para dar continuidad al proceso, le solicitamos que nos proporcione los siguientes documentos\r\n"
				+ "			indispensables:\r\n"
				+ "		</p>\r\n"
				+ "		<ol>\r\n"
				+ "			<li>Paz y Salvo de impuesto predial que incluya el avalúo catastral (emitido por la Secretaría Municipal de\r\n"
				+ "				Hacienda).</li>\r\n"
				+ "			<li class=\"m-li-top\">Copia de la última escritura del predio.</li>\r\n"
				+ "			<li class=\"m-li-top\">Certificado de nomenclatura (emitido por la Secretaría Municipal de Planeación).</li>\r\n"
				+ "			<li class=\"m-li-top\">Contrato de obra <span class=\"p-negrita\">autenticado</span> (formato proporcionado).\r\n"
				+ "			</li>\r\n"
				+ "			<li class=\"m-li-top\">Formato datos vecinos (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Formato notificación a vecinos (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Formato carta de solicitud de licencia (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Formato poder para solicitud de licencia <span class=\"p-negrita\">autenticado</span>\r\n"
				+ "				(formato\r\n"
				+ "				proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Formulario de postulación a Cafam (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Certificado de responsabilidad documental (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Formato cartilla de especificaciones (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Contrato de voluntariado (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Formulario de declaración juramentada (formato proporcionado).</li>\r\n"
				+ "			<li class=\"m-li-top\">Registro fotográfico del predio, así:</li>\r\n"
				+ "			<ul style=\"list-style-type: disc;\">\r\n"
				+ "				<li class=\"m-li\">5 fotografías del predio desde la entrada y desde los linderos de este mostrando en lo\r\n"
				+ "					posible todo el predio</li>\r\n"
				+ "				<li class=\"m-li-top\">3 fotos de la zona donde se espera construir la vivienda</li>\r\n"
				+ "				<li class=\"m-li-top\">3 fotos de la vía de acceso al lote</li>\r\n"
				+ "				<li class=\"m-li-top\">Fotos de postes de energía, red de acueducto veredal, medidores en el predio o de\r\n"
				+ "					vecinos.</li>\r\n"
				+ "			</ul>\r\n"
				+ "			<li class=\"m-li\">Firmar planos y formato único nacional – FUN (formato proporcionado).</li>\r\n"
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
				+ "	</div>\r\n"
				+ "</body>\r\n"
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
				+ "		<p>Municipio: "+  municipioDepartamento +"</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Asunto: ¡Felicitaciones por la Aprobación de su Subsidio de Vivienda Rural Digna!</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>Querido(a) Señor(a) " + nombresCompletos + ":</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>¡Reciba un afectuoso y cálido saludo!</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Con inmensa alegría y satisfacción, nos complace compartir con usted una maravillosa noticia:\r\n"
				+ "			¡Su solicitud para el subsidio de vivienda rural ha sido <span class=\"p-negrita\"> APROBADA </span>\r\n"
				+ "			por la Caja de Compensación Familiar Cafam.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Este gran logro es fruto de su perseverancia, compromiso y confianza a lo largo de todo el proceso del\r\n"
				+ "			programa <span class=\"p-negrita\"> VIVIENDA RURAL DIGNA </span>. Nos honra enormemente poder acompañarle en\r\n"
				+ "			este camino hacia una vida mejor y más estable.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Próximamente nos estaremos comunicando con usted donde le informaremos los pasos a seguir para el inicio de\r\n"
				+ "			la construcción de su vivienda.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Desde nuestro equipo, le enviamos nuestras más sinceras felicitaciones y un fuerte abrazo. Que este hogar\r\n"
				+ "			sea el refugio de muchas alegrías, unión y amor.\r\n"
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
				+ "</html>";
		return template;
	}
	
	public String noAprobadoFase2() {
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaFormateada = formateador.format(fecha);
		String nombresCompletos = this.solicitudApp.getNombres() + " " + this.solicitudApp.getApellidos();
		String municipioDepartamento = this.solicitudApp.getDepartamento_municipio();

		String template = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
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
				+ "			Queremos agradecerle sinceramente por su participación y el esfuerzo demostrado en el programa <span\r\n"
				+ "				class=\"p-negrita\">VIVIENDA RURAL DIGNA.</span>.\r\n"
				+ "			Sin embargo, tras un detallado análisis técnico, lamentamos informarle que en esta ocasión no ha sido\r\n"
				+ "			posible considerarle viable técnicamente\r\n"
				+ "			para avanzar a la siguiente fase.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Entendemos que esta noticia puede ser desalentadora, pero queremos asegurarle que seguimos comprometidos con\r\n"
				+ "			nuestro propósito\r\n"
				+ "			de trabajar por el bienestar de las comunidades rurales. Su postulación seguirá siendo valorada en futuras\r\n"
				+ "			iniciativas y programas\r\n"
				+ "			que podamos implementar.\r\n"
				+ "		</p>\r\n"
				+ "		<br />\r\n"
				+ "		<p>\r\n"
				+ "			Quedamos a su disposición para atender cualquier inquietud y le animamos a mantenerse en contacto para\r\n"
				+ "			futuras oportunidades.\r\n"
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
