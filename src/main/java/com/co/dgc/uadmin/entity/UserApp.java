package com.co.dgc.uadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.co.dgc.uadmin.request.RqRegistroUsuarioApp;

@Entity
@Table(name = "usuarios_app")
public class UserApp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "tipo_identificacion")
	private String tipo_identificacion;

	@Column(name = "identificacion")
	private String identificacion;

	@Column(name = "correo")
	private String correo;

	@Column(name = "role")
	private String role;

	@Column(name = "fecha_registro")
	private String fecha_registro;

	@Column(name = "usuario_activo")
	private boolean usuario_activo;

	@Column(name = "id_procesamiento")
	private String id_procesamiento;

	@Column(name = "usuario_app")
	private String usuarioApp;

	public UserApp() {
	}

	public UserApp(RqRegistroUsuarioApp body, String contrasenia, String fechaRegistro, boolean usuarioActivo, String idProcesamiento) {
		this.usuario = body.getUsuario();
		this.contrasenia = contrasenia;
		this.nombre = body.getNombres();
		this.apellidos = body.getApellidos();
		this.tipo_identificacion = body.getTipoIdentificacion();
		this.identificacion = body.getIdentificacion();
		this.correo = body.getCorreo();
		this.role = body.getRole();
		this.fecha_registro = fechaRegistro;
		this.usuario_activo = usuarioActivo;
		this.id_procesamiento = idProcesamiento;
		this.usuarioApp = body.getUsuarioApp();
	}

	public UserApp(String usuario, String contrasenia, String nombre, String apellidos, String tipoIdentificacion,
			String identificacion, String correo, String role, String fecha_registro, boolean usuario_activo,
			String id_procesamiento, String usuarioApp) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tipo_identificacion = tipoIdentificacion;
		this.identificacion = identificacion;
		this.correo = correo;
		this.role = role;
		this.fecha_registro = fecha_registro;
		this.usuario_activo = usuario_activo;
		this.id_procesamiento = id_procesamiento;
		this.usuarioApp = usuarioApp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipo_identificacion() {
		return tipo_identificacion;
	}

	public void setTipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public boolean isUsuario_activo() {
		return usuario_activo;
	}

	public void setUsuario_activo(boolean usuario_activo) {
		this.usuario_activo = usuario_activo;
	}

	public String getId_procesamiento() {
		return id_procesamiento;
	}

	public void setId_procesamiento(String id_procesamiento) {
		this.id_procesamiento = id_procesamiento;
	}

	public String getUsuarioApp() {
		return usuarioApp;
	}

	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}

}
