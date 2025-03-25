package com.co.dgc.uadmin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.dgc.uadmin.entity.MunicipiosApp;
import com.co.dgc.uadmin.repo.IMunicipiosAppRepo;

@Service
public class MunicipiosAppService implements IMunicipiosAppService {
	
	@Autowired
	IMunicipiosAppRepo iMunicipiosAppRepo;

	@Override
	public List<MunicipiosApp> getMunicipiosAppList() {
		return iMunicipiosAppRepo.findAll();
	}

	@Override
	public void registraMunicipiosApp(MunicipiosApp municipiosApp) {
		iMunicipiosAppRepo.save(municipiosApp);		
	}

	@Override
	public List<MunicipiosApp> getMunicipiosPorIdDeDepartamento(String id_departamento) {
		return iMunicipiosAppRepo.getMunicipiosPorIdDeDepartamento(id_departamento);
	}

	@Override
	public MunicipiosApp getMunicipiosPorIdDeDepartamentoIdDeMunicipio(String id_departamento, String id_municipio) {
		return iMunicipiosAppRepo.getMunicipiosPorIdDeDepartamentoIdDeMunicipio(id_departamento, id_municipio);
	}

	@Override
	public void eliminaMunicipiosApp(long id) {
		iMunicipiosAppRepo.deleteById(id);
	}

}
