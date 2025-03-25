package com.co.dgc.uadmin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.dgc.uadmin.entity.BeneficiariosApp;
import com.co.dgc.uadmin.repo.IBeneficiariosAppRepo;

@Service
public class BeneficiariosAppService implements IBeneficiariosAppService {
	
	@Autowired
	IBeneficiariosAppRepo iBeneficiariosAppRepo;

	@Override
	public void registraBeneficiarioApp(BeneficiariosApp beneficiariosApp) {
		iBeneficiariosAppRepo.save(beneficiariosApp);
	}

	@Override
	public List<BeneficiariosApp> getBeneficiariosPorIdProcesamiento(String id_procesamiento) {
		return iBeneficiariosAppRepo.getBeneficiariosPorIdProcesamiento(id_procesamiento);
	}

	@Override
	public void eliminarBeneficiarioApp(BeneficiariosApp beneficiariosApp) {
		iBeneficiariosAppRepo.delete(beneficiariosApp);		
	}

	@Override
	public List<BeneficiariosApp> getBeneficiariosPorIdProcesamientoDocumento(String id_procesamiento, String identificacionBen) {
		return iBeneficiariosAppRepo.getBeneficiariosPorIdProcesamientoDocumento(id_procesamiento, identificacionBen);
	}

}
