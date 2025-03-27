package com.bbva.fsia.batch;

import com.bbva.apx.exception.io.IOException;
import com.bbva.dtod.dto.artica.xml.*;
import com.bbva.fsia.batch.write.SerializationService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test for batch process FSIAJ001-01-ES
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/batch/beans/FSIAJ001-01-ES-beans.xml","classpath:/META-INF/spring/batch/jobs/jobs-FSIAJ001-01-ES-context.xml","classpath:/META-INF/spring/jobs-FSIAJ001-01-ES-runner-context.xml"})
public class FSIAJ00101ESFunctionalTest{

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

//	@Autowired
//	private FSIAR062 fsiaR062; // Mock the library

//	@Autowired
//	private ApplicationConfigurationService applicationConfigurationService;

@Before
	public void setUp() throws java.io.IOException {
	// Step 1: Load the XML response from the file
//	String mockResponseXml = loadFileContent("src/test/resources/respuesta_172.xml");
//	String mockResponseXml = loadFileContent("src/test/resources/respuesta_rechazda.xml");

	// Step 2: Mock the executeRespSoap172 method to return the XML response
	//when(fsiaR062.executeRespSoap172(anyString(), anyString())).thenReturn(mockResponseXml);
    //mock this to avoid null pointer exception :            certificadoElegido = String.valueOf(this.applicationConfigurationService.getProperty("fsif.soap.certificadoASSET"));
	//when(String.valueOf(this.applicationConfigurationService.getProperty("fsif.soap.certificadoASSET"))).thenReturn("7293385H");

}

	@Test
	public void testLaunchJob() throws Exception {
		//TODO implements job launch test
		//Without parameters (use this implementation if job not need parameters)
		final JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		
		//With parameters (use this implementation if job needs parameters comment first implementation) 
		/*********************** Parameters Definition ***********************/
		//First parameter
//		final JobParameter jobParameter = new JobParameter("ParamValue");
		//Add parameters to job
//		final HashMap<String, JobParameter> parameters = new HashMap<String, JobParameter>();
//		parameters.put("paramName", jobParameter);
//		final JobParameters jobParameters = new JobParameters(parameters);
//		final JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
		//TODO implements job launch test Assert's
		Assert.assertTrue(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED));
	}

	public static String loadFileContent(String filePath) throws IOException, java.io.IOException {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		return content.toString();
	}

	@Test
	public void testDeserialize() {
		String xml = "<RespuestaDeclaracion xmlns:ddiiR=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/RespuestaDeclaracion.xsd\">" +
				"    <CSV>FYYATSZMJDRJXRRW</CSV>" +
				"    <DatosPresentacion>" +
				"        <NIFPresentador>XXXXXXXX</NIFPresentador>" +
				"        <TimestampPresentacion>13-07-2023 11:22:33</TimestampPresentacion>" +
				"    </DatosPresentacion>" +
				"</RespuestaDeclaracion>";
		SerializationService<RespuestaDeclaracionType> service = new SerializationService<>();
		RespuestaDeclaracionType response = service.deserialize(xml);
		assertNotNull(response);
		assertEquals("FYYATSZMJDRJXRRW", response.getCsv());
	}

}
