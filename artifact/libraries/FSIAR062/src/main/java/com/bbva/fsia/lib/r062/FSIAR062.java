package com.bbva.fsia.lib.r062;

import java.util.Map;

/**
 * The  interface FSIAR062 class...
 */
public interface FSIAR062 {

	/**
	 * The execute method...
	 */
	String executeRespSoap172(String xmlConsult_172,String idCertificateDigital);
	int executeUpdateSoapInformation(Map<String, Object> parameters);
}
