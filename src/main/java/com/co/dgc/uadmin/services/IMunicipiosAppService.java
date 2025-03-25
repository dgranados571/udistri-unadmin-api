package com.co.dgc.uadmin.services;

import java.util.List;
import com.co.dgc.uadmin.entity.MunicipiosApp;

public interface IMunicipiosAppService {
	
	public void registraMunicipiosApp(MunicipiosApp municipiosApp);
	public List<MunicipiosApp> getMunicipiosAppList();
	public List<MunicipiosApp> getMunicipiosPorIdDeDepartamento(String id_departamento);
	public MunicipiosApp getMunicipiosPorIdDeDepartamentoIdDeMunicipio(String id_departamento, String id_municipio);
	public void eliminaMunicipiosApp(long id);

}
