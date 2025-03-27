package com.bbva.fsia.lib.r062.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;


public class FSIAR062Impl extends FSIAR062Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(FSIAR062Impl.class);

	@Override
	public String executeRespSoap172(String xmlConsult_172, String idCertificateDigital) {
		LOGGER.info("xmlConsult_172 : {}",xmlConsult_172);
		System.out.println("LIB executeRespSoap172..xmlConsult_172 : "+xmlConsult_172);
		LOGGER.info("idCertificateDigital : {}",idCertificateDigital);
		System.out.println("idCertificateDigital : "+idCertificateDigital);
		StreamSource request = new StreamSource(new StringReader(xmlConsult_172));

		LOGGER.info("request : {}",request);

		apiConnectorCertificado = this.apiConnectorBuilder.certificate(idCertificateDigital).external().build();

		// Attempt to send the soap request multiple times to avoid connection issues

		int attempts = 15;
		boolean connection = false;
		String apiURLId = "send172SOAP";
		StreamResult xmlReceipt = null;

		LOGGER.info( "attempts : {}",attempts);
		LOGGER.info("connection : {}",connection);
		LOGGER.info("apiURLId : {}",apiURLId);

		while(!connection && attempts > 0){
			attempts--;
			LOGGER.info("attempts : {}",attempts);
			xmlReceipt = new StreamResult(new StringWriter());
			connection = apiConnectorCertificado.sendSourceAndReceiveToResult(apiURLId, request, xmlReceipt);
		}
		LOGGER.info("connection : {}",connection);
		if(connection){
			LOGGER.info("xmlReceipt.getWriter().toString() : {}",xmlReceipt.getWriter().toString());
			return xmlReceipt.getWriter().toString();
		} else{
			return "";
		}
	}

	@Override
	public int executeUpdateSoapInformation(Map<String, Object> parameters) {
//		LOGGER.debug("\n executeUpdateSOAPInformation - INI");
//		int resultado = this.jdbcUtils.update("job1.actualizarDatosInfoEnvioSOAP", parameters);
//		LOGGER.debug("\n executeUpdateSOAPInformation - FIN");
//		return resultado;
		//part of database  will implement in the future
		return 0;
	}

}
