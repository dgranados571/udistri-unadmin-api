package com.co.dgc.uadmin.services;

import java.util.List;

import com.co.dgc.uadmin.entity.DepartamentosApp;

public interface IDepartamentosAppService {
	
	public void registraDepartamentosApp(DepartamentosApp departamentosApp);
	public DepartamentosApp getDepartamentosAppPorIdDepartamento(String id_departamento);
	public List<DepartamentosApp> getDepartamentosList();
	public void eliminaDepartamentosApp(long id);
}

