package com.co.dgc.uadmin.enums;

public class EnumConstantes {
	
	public static final String ROOT = "ROOT";
	
	public static final String MSG_ELIMINA_SOLICITUD_SUCCES = "Solicitud eliminada exitosamente";
	public static final String MSG_ELIMINA_SOLICITUD_FAIL = "No fue posible eliminar la solicitud, contacte al administrador";
	
	public static final String MSG_ELIMINA_MUNICIPIO_SUCCES = "Municipio eliminado exitosamente";
	public static final String MSG_ELIMINA_MUNICIPIO_FAIL = "No fue posible eliminar el municipio, contacte al administrador";

	public static final String MSG_REGITRO_ROOT_SUCCES = "Registro de usuario Root exitoso";
	public static final String MSG_REGITRO_ROOT_FAIL = "No fue posible el registro de Root, contacte al administrador";
	
	public static final String MSG_REGITRO_USUARIO_SUCCES = "Registro de usuario exitoso";
	public static final String MSG_REGITRO_USUARIO_FAIL = "No fue posible el registro de usuario, contacte al administrador";
	public static final String MSG_REGITRO_USUARIO_YA_EXISTE = "Ya existe un usuario ";
	
	public static final String MSG_ACTIVACION_USUARIO_SUCCES = "Usuario activado con exito";
	public static final String MSG_ACTIVACION_USUARIO_FAIL = "No fue posible la activación del usuario, contacte al administrador";
	
	public static final String MSG_ACTUALIZA_USUARIO_SUCCES = "Usuario actualizado exitosamente";
	public static final String MSG_ACTUALIZA_USUARIO_FAIL = "No es posible editar el usuario ";
	
	public static final String MSG_LOGIN_SUCCES = "Usuario logueado";
	public static final String MSG_LOGIN_FAIL = "Usuario y/o contraseña incorrectos";
	public static final String MSG_LOGIN_ACTIVATE = "Usuario pendiente por activar";
	
	public static final String MSG_ACTUALIZA_PASS_SUCCES = "Contraseña actualizada con éxito";
	public static final String MSG_ACTUALIZA_PASS_FAIL = "No fue posible actualizar la contraseña, contacte al administrador";
	
	public static final String MSG_ELIMINA_USUARIO_SUCCES = "Usuario eliminado exitosamente";
	public static final String MSG_ELIMINA_USUARIO_FAIL = "No es posible eliminar el ususario el usuario, contacte al administrador";
	
	public static final String MSG_SUCCES = "Consulta exitosa";
	public static final String MSG_SUCCES_2 = "Información procesada exitosamente";
	public static final String MSG_FAIL = "No fue posible consultar la información, contacte al administrador";
	
	public static final String MSG_VALIDA_LONG_DATA_FAIL = "could not execute statement";
	public static final String MSG_RESUELVE_LONG_DATA_FAIL = "La longitud del campo observación excede el limite permitido";
	
	public static final String MSG_REGITRO_SOLICITUD_SUCCES = "Solicitud registrada exitosamente";
	public static final String MSG_REGITRO_SOLICITUD_FAIL = "No fue posible el registro de la solicitud, contacte al administrador";
	public static final String MSG_REGITRO_SOLICITUD_FAIL_EXISTE = "Ya existe una solicitud bajo este número de documento, por favor verifique la información.";
	public static final String MSG_REGITRO_LONG_DATA_FAIL = "La longitud del campo descripción excede el limite permitido";
	
	public static final String MSG_ACTUALIZA_SOLICITUD_SUCCES = "Información actualizada exitosamente";
	public static final String MSG_ACTUALIZA_SOLICITUD_FAIL = "No fue posible actualizar la información, contacte al administrador";
	
	public static final String MSG_REGITRO_DEPARTAMENTO_SUCCES = "Departamento registrado exitosamente";
	public static final String MSG_REGITRO_DEPARTAMENTO_FAIL = "No fue posible el registrar el departamento, contacte al administrador";
	public static final String MSG_REGITRO_DEPARTAMENTO_FAIL_EXISTE = "Ya existe un departamento bajo este número de id, por favor verifique la información.";
	
	public static final String MSG_REGITRO_MUNICIPIO_SUCCES = "Municipio registrado exitosamente";
	public static final String MSG_REGITRO_MUNICIPIO_FAIL = "No fue posible registrar el municipio, contacte al administrador";
	public static final String MSG_REGITRO_MUNICIPIO_FAIL_EXISTE = "Ya existe un municipio bajo este ID asociado a este departamento, por favor verifique la información.";
	
	public static final String MSG_CARGA_DOCUMENTOS_SUCCES = "Documentos procesados con éxito";
	public static final String MSG_CARGA_DOCUMENTOS_FAIL = "No fue posible procesar la documentación, contacte al administrador";
	
	public static final String MSG_OBTIENE_DOCUMENTOS_SUCCES = "Documento obtenido satisfactoriamente";
	public static final String MSG_OBTIENE_DOCUMENTOS_FAIL = "No fue posible obtener la documentación, contacte al administrador";
	
	public static final String ROLE_ROOT = "USUARIO_ROOT";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_1 = "ROLE_1";
	public static final String ROLE_2 = "ROLE_2";
	public static final String ROLE_3 = "ROLE_3";
	
	public static final String STEP_1 = "STEP_1";
	public static final String STEP_2 = "STEP_2";
	public static final String STEP_3 = "STEP_3";
	public static final String STEP_4 = "STEP_4";
	public static final String STEP_5 = "STEP_5";
	public static final String STEP_6 = "STEP_6";
	public static final String STEP_7 = "STEP_7";
	
	
	public static final String EVENTO_ASIGNA_A_REVISION = "EVENTO_ASIGNA_A_REVISION";
	public static final String RESULT_ASIGNA_A_REVISION = "Asignado a revisor";
	
	// FASE 1
	
	public static final String EVENTO_CREA_SOLICITUD = "CREA_SOLICITUD";
	public static final String RESULT_CREA_SOLICITUD = "Solicitud creada";
	
	// FASE 2
	
	public static final String EVENTO_PREAPROBADO = "EVENTO_PREAPROBADO";
	public static final String RESULT_PREAPROBADO = "Preaprobado";
	
	public static final String EVENTO_NO_PREAPROBADO = "EVENTO_NO_PREAPROBADO";
	public static final String RESULT_NO_PREAPROBADO = "No preaprobado";
	
	public static final String EVENTO_DEVUELTO_GESTION_CREADOR_SOLICITUD = "EVENTO_DEVUELTO_GESTION_CREADOR_SOLICITUD";
	public static final String RESULT_DEVUELTO_GESTION_CREADOR_SOLICITUD = "Devolución a gestor - creador solicitud";
	
	// FASE 3	
	
	public static final String EVENTO_ESTUDIO_VIABILIDAD = "EVENTO_ESTUDIO_VIABILIDAD";
	public static final String RESULT_ESTUDIO_VIABILIDAD = "Viable técnicamente";
	
	public static final String EVENTO_DEVUELTO_GESTION = "EVENTO_DEVUELTO_GESTION";
	public static final String RESULT_DEVUELTO_GESTION = "Devolución a gestor";
	
	public static final String EVENTO_NO_APROBADO = "EVENTO_NO_APROBADO";
	public static final String RESULT_NO_APROBADO = "No aprobado";
	
	// FASE 4
	
	public static final String EVENTO_FACTIBLE_ACTUALIZACION = "EVENTO_FACTIBLE_ACTUALIZACION";
	public static final String RESULT_FACTIBLE_ACTUALIZACION= "Factible para actualización";
	
	// FASE 5
	
	public static final String EVENTO_LICENCIAR = "EVENTO_LICENCIAR";
	public static final String RESULT_LICENCIAR = "Licenciar";
	
	// FASE 6
	
	public static final String EVENTO_LICENCIA_SUBSIDIO = "EVENTO_LICENCIA_SUBSIDIO";
	public static final String RESULT_LICENCIA_SUBSIDIO = "Licencia y Subsidio";
	
	// FASE FINAL
	
	public static final String EVENTO_VO_BO_SUBSIDIO = "EVENTO_VO_BO_SUBSIDIO";
	public static final String RESULT_VO_BO_SUBSIDIO = "Vo-Bo Subsidio";
	
	
	
	public static final String CONTRASENIA_MASCARA = "******";
	public static final String ERROR_SIMBOLO = "Error --> ";
	public static final String DD_MM_YYYY_HH_MM_SS = "ddMMyyyyHHmmss";
	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String HH_MM_SS = "HH:mm:ss";
	
	public static final String DIR_FILES_AWS = "OT_UADMIN";
	public static final String DIR_FILES_MOD_1 = "MODULO_1";
	public static final String DIR_FILES_MOD_2 = "MODULO_2";
	public static final String DIR_FILES_MOD_3 = "MODULO_3";
	public static final String DIR_FILES_MOD_ANEXOS = "MODULO_ANEXOS";
	public static final String DIR_FILES_MOD_IMAGES = "MODULO_IMAGES";
	public static final String DIR_FILES_BENEFICIARIOS = "MODULO_BEN";	
	
	
}
