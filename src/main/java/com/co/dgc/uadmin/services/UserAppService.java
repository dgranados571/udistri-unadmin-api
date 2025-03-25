package com.co.dgc.uadmin.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.dgc.uadmin.entity.UserApp;
import com.co.dgc.uadmin.repo.IUserAppRepo;

@Service
public class UserAppService implements IUserAppService {
	
	@Autowired
	IUserAppRepo iUserAppRepo;

	@Override
	public List<UserApp> listaUsuariosApp() {
		return iUserAppRepo.findAll();
	}

	@Override
	public void registraUsuarioApp(UserApp usuarioApp) {
		iUserAppRepo.save(usuarioApp);
	}

	@Override
	public UserApp obtieneUsuarioAPPporUsuario(String usuario) {
		return iUserAppRepo.obtieneUsuarioAPPporUsuario(usuario);
	}

	@Override
	public long countUsuariosApp() {
		return iUserAppRepo.count();
	}

	@Override
	public List<UserApp> listaUsuariosAppPorRole(String role) {
		return iUserAppRepo.obtieneUsuarioAPPporRole(role);
	}

	@Override
	public void eliminarUsuarioApp(UserApp usuarioApp) {
		iUserAppRepo.delete(usuarioApp);		
	}

}
