package com.co.dgc.uadmin.util;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.co.dgc.uadmin.enums.EnumConstantes;

public class AwsUtil {

	static AWSCredentials credenciales = new BasicAWSCredentials("", "");
	static AmazonS3 s3client = new AmazonS3Client(credenciales);
	static String APPDGCBUCKET = "appuadminbucket";

	public static void eliminarCarpetasS3(String procesmientoId, String pathLocal) {
		try {
			List<S3ObjectSummary> fileList = s3client.listObjects(APPDGCBUCKET).getObjectSummaries();
			for (S3ObjectSummary idSumary : fileList) {
				if (idSumary.getKey().contains(pathLocal)) {
					if (idSumary.getKey().contains(procesmientoId)) {
						s3client.deleteObject(APPDGCBUCKET, idSumary.getKey());
					} 
				}
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
	}
	
	public static void eliminarDocumentoS3(String procesamientoId, String pathLocal, String indexDocument) {
		try {
			List<S3ObjectSummary> fileList = s3client.listObjects(APPDGCBUCKET).getObjectSummaries();
			for (S3ObjectSummary idSumary : fileList) {
				if (idSumary.getKey().contains(procesamientoId)) {					
					if (idSumary.getKey().contains(pathLocal)) {
						if(idSumary.getKey().contains(indexDocument)) {
							s3client.deleteObject(APPDGCBUCKET, idSumary.getKey());
							break;
						}
					}	
				}
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
	}
	
	public static void eliminarMasivamenteCarpetasS3(String pathLocal) {
		try {
			List<S3ObjectSummary> fileList = s3client.listObjects(APPDGCBUCKET).getObjectSummaries();
			for (S3ObjectSummary idSumary : fileList) {
				if (idSumary.getKey().contains(pathLocal)) {
					s3client.deleteObject(APPDGCBUCKET, idSumary.getKey());
				}
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
	}

	public static List<String> listarCarpetasS3(String procesmientoId, String pathLocal, String pathModulo) {
		List<String> urlImages = new ArrayList<String>();
		try {
			List<S3ObjectSummary> fileList = s3client.listObjects(APPDGCBUCKET).getObjectSummaries();
			for (S3ObjectSummary idSumary : fileList) {
				if (idSumary.getKey().contains(pathLocal)) {
					if (idSumary.getKey().contains(procesmientoId) && idSumary.getKey().contains(pathModulo)) {
						urlImages.add("https://" + APPDGCBUCKET + ".s3.amazonaws.com/" + idSumary.getKey());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(EnumConstantes.ERROR_SIMBOLO + e);
		}
		return urlImages;
	}

}
