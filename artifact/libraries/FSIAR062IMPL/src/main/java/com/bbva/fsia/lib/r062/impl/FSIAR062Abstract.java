package com.bbva.fsia.lib.r062.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.elara.utility.api.connector.APIConnectorBuilder;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.fsia.lib.r062.FSIAR062;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class FSIAR062Abstract extends AbstractLibrary implements FSIAR062 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected APIConnector apiConnectorCertificado;

	protected APIConnectorBuilder apiConnectorBuilder;

	protected JdbcUtils jdbcUtils;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param apiConnectorCertificado the this.internalApiConnector to set
	*/
	public void setApiConnectorCertificado(APIConnector apiConnectorCertificado) {
		this.apiConnectorCertificado = apiConnectorCertificado;
	}

	/**
	* @param apiConnectorBuilder the this.apiConnectorBuilder to set
	*/
	public void setApiConnectorBuilder(APIConnectorBuilder apiConnectorBuilder) {
		this.apiConnectorBuilder = apiConnectorBuilder;
	}

	/**
	* @param jdbcUtils the this.jdbcUtils to set
	*/
	public void setJdbcUtils(JdbcUtils jdbcUtils) {
		this.jdbcUtils = jdbcUtils;
	}

}