package com.bbva.fsia.lib.r062.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.elara.utility.api.connector.APIConnector;
import com.bbva.elara.utility.api.connector.APIConnectorBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.aop.framework.Advised;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class FSIAR062ImplTest {

    @Spy
    private Context context;

	@InjectMocks
    private FSIAR062Impl fsiaR062;

    @Resource(name = "apiConnector")
    private APIConnector apiConnector;

    @Resource(name = "apiConnectorBuilder")
    private APIConnectorBuilder apiConnectorBuilder;

    @Resource(name = "apiConnectorCertifacte")
    private APIConnector apiConnectorCertificado;

    private APIConnectorBuilder apiConnectorRespuesta;

    @Mock
    private ApplicationConfigurationService applicationConfigurationService;


	@Before
	public void setUp() throws Exception {
		apiConnector = Mockito.mock(APIConnector.class);
        apiConnectorBuilder = Mockito.mock(APIConnectorBuilder.class);
        apiConnectorCertificado = Mockito.mock(APIConnector.class);
        apiConnectorRespuesta = Mockito.mock(APIConnectorBuilder.class);
        applicationConfigurationService = Mockito.mock(ApplicationConfigurationService.class);
        fsiaR062 = new FSIAR062Impl();
        fsiaR062.setApiConnectorBuilder(apiConnectorBuilder);
        fsiaR062.setApiConnectorCertificado(apiConnectorCertificado);
        MockitoAnnotations.initMocks(this);
        ThreadContext.set(context);
        getObjectIntrospection();
	}

    private Object getObjectIntrospection() throws Exception {
       Object result = this.fsiaR062;
       if (this.fsiaR062 instanceof Advised) {
           Advised advised = (Advised) this.fsiaR062;
           result = advised.getTargetSource().getTarget();
       }
       return result;
    }

    @Test
    public void executeRespSoap172_ValidRequest_ReturnsResponse(){
        String xmlConsult_172 = "<xml>valid</xml>";
        String idCertificateDigital = "validCertificate";
        String expectedResponse = "<response>valid</response>";

        when(apiConnectorBuilder.certificate(anyString())).thenReturn(apiConnectorBuilder);
        when(apiConnectorBuilder.external()).thenReturn(apiConnectorBuilder);
        when(apiConnectorBuilder.build()).thenReturn(apiConnectorCertificado);
        when(apiConnectorCertificado.sendSourceAndReceiveToResult(anyString(),any(StreamSource.class), any(StreamResult.class)))
                .thenAnswer(invocation -> {
                    Object[] args = invocation.getArguments();
                    StreamResult xmlReceipt = (StreamResult) args[2];
                    xmlReceipt.getWriter().write(expectedResponse);
                    return true;
                });

        String response = fsiaR062.executeRespSoap172(xmlConsult_172, idCertificateDigital);
        System.out.println("Response: " + response);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(expectedResponse, response);
    }
}
