package com.co.dgc.uadmin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.co.dgc.uadmin.entity.DepartamentosApp;
import com.co.dgc.uadmin.repo.IDepartamentosAppRepo;

@Service
public class DepartamentosAppService implements IDepartamentosAppService {
	
	@Autowired
	IDepartamentosAppRepo iDepartamentosAppRepo;

	@Override
	public void registraDepartamentosApp(DepartamentosApp departamentosApp) {
		iDepartamentosAppRepo.save(departamentosApp);
	}

	@Override
	public DepartamentosApp getDepartamentosAppPorIdDepartamento(String id_departamento) {
		return iDepartamentosAppRepo.getDepartamentosAppPorIdDepartamento(id_departamento);
	}

	@Override
	public List<DepartamentosApp> getDepartamentosList() {
		return iDepartamentosAppRepo.findAll(Sort.by(Sort.Order.asc("departamento")));
	}

	@Override
	public void eliminaDepartamentosApp(long id) {
		iDepartamentosAppRepo.deleteById(id);
	}

}
