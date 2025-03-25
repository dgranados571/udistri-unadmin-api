package com.co.dgc.uadmin.services;

import java.util.List;
import com.co.dgc.uadmin.entity.UserApp;

public interface IUserAppService {
	
	public List<UserApp> listaUsuariosApp();
	public List<UserApp> listaUsuariosAppPorRole(String role);
	public void registraUsuarioApp(UserApp usuarioApp);
	public void eliminarUsuarioApp(UserApp usuarioApp);
	public UserApp obtieneUsuarioAPPporUsuario(String usuario);
	public long countUsuariosApp();

}
