package com.co.dgc.uadmin.services;

import java.util.List;

import com.co.dgc.uadmin.entity.BeneficiariosApp;

public interface IBeneficiariosAppService {
	
	public void registraBeneficiarioApp(BeneficiariosApp beneficiariosApp);
	public void eliminarBeneficiarioApp(BeneficiariosApp beneficiariosApp);
	public List<BeneficiariosApp> getBeneficiariosPorIdProcesamiento(String id_procesamiento);
	public List<BeneficiariosApp> getBeneficiariosPorIdProcesamientoDocumento(String id_procesamiento, String identificacionBen);
}
