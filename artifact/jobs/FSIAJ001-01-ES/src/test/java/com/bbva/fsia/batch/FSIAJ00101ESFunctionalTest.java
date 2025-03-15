package com.bbva.fsia.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;

import com.bbva.apx.exception.io.IOException;
import com.bbva.fsia.batch.process.ProcessTrade;
import com.bbva.fsia.batch.write.WriteDeclarationModel172;
import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.lib.r062.FSIAR062;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for batch process FSIAJ001-01-ES
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/batch/beans/FSIAJ001-01-ES-beans.xml","classpath:/META-INF/spring/batch/jobs/jobs-FSIAJ001-01-ES-context.xml","classpath:/META-INF/spring/jobs-FSIAJ001-01-ES-runner-context.xml"})
public class FSIAJ00101ESFunctionalTest{

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private FSIAR062 fsiaR062; // Mock the library

@Before
	public void setUp() throws java.io.IOException {
	// Step 1: Load the XML response from the file
	String mockResponseXml = loadFileContent("src/test/resources/respuesta_172.xml");
//	String mockResponseXml = loadFileContent("src/test/resources/respuesta_rechazda.xml");

	// Step 2: Mock the executeRespSoap172 method to return the XML response
	when(fsiaR062.executeRespSoap172(anyString(), anyString())).thenReturn(mockResponseXml);
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

}
