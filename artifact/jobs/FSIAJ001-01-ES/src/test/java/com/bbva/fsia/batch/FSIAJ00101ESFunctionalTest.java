package com.bbva.fsia.batch;

import java.util.Collections;
import java.util.HashMap;

import com.bbva.fsia.batch.process.ProcessTrade;
import com.bbva.fsia.batch.write.WriteDeclarationModel172;
import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for batch process FSIAJ001-01-ES
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/batch/beans/FSIAJ001-01-ES-beans.xml","classpath:/META-INF/spring/batch/jobs/jobs-FSIAJ001-01-ES-context.xml","classpath:/META-INF/spring/jobs-FSIAJ001-01-ES-runner-context.xml"})
public class FSIAJ00101ESFunctionalTest{

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;


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

	@Test
	public void testWrite() throws Exception {
		WriteDeclarationModel172 writer = new WriteDeclarationModel172();
		writer.setResource(new FileSystemResource("output/test_output.xml"));

		TradeOperation trade = new TradeOperation();
		trade.setGfTradeId(123456);
		trade.setGfAssetPairName("BTC/EUR");
		trade.setTradeAmountAssetName("BTC");
		trade.setGfTradeEx1Amount(2);
		trade.setGfNetPriceAmount(34950.5);
		trade.setGfTrdDate(new java.sql.Date(System.currentTimeMillis()));
		trade.setGfNetAssetAmount(87450.38);
		trade.setClients("John Doe, Jane Smith");

		ProcessTrade processor = new ProcessTrade();
		DeclarationModel172 declaration = processor.process(trade);

		writer.write(Collections.singletonList(declaration));
	}
}
