package com.bbva.fsia.batch.soap;


import com.bbva.fsia.dto.artica.xmlresponsev2.*;
import com.bbva.fsia.lib.r062.FSIAR062;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class SOAPTransmission implements Tasklet {


    private static final Logger LOGGER = LoggerFactory.getLogger(SOAPTransmission.class);

    private String cadenaLog = "";
    private FSIAR062 fsiaR062;
    private List<Map<String, Object>> datosResouestaCombinadoTotal; // Stores processed response data
    private String nombreArchivo;
    private String nifDeclarante;


    public void setFsiaR062(FSIAR062 fsiaR062) {
        this.fsiaR062 = fsiaR062;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        try {
            // Retrieve the XML from the execution context
            ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
            String xmlConsult_172 = jobContext.getString("finalBody");

            // Log the generated XML
            LOGGER.info("Generated XML Request: {}", xmlConsult_172);

            // Send the SOAP request and get the response
            String respuestaAeatModelo172 = this.fsiaR062.executeRespSoap172(xmlConsult_172, nifDeclarante);
            LOGGER.info("SOAP Response: {}", respuestaAeatModelo172);

            // Process the SOAP response
            datosResouestaCombinadoTotal = procesarRespuestaAeatModelo172(nombreArchivo, respuestaAeatModelo172, 12345, xmlConsult_172);

            // Log the processed data
            LOGGER.info("Processed Data: {}", datosResouestaCombinadoTotal);

        } catch (Exception e) {
            LOGGER.error("Error during SOAP transmission: {}", e.getMessage(), e);
            throw new RuntimeException("SOAP transmission failed. Details: " + e.getMessage(), e);
        }

        return RepeatStatus.FINISHED;
    }

    private List<Map<String, Object>> procesarRespuestaAeatModelo172(String nombreArchivo,
                                                                     String respuestaAeatModelo172,
                                                                     int idEnvio,
                                                                     String xmlConsult_172) throws Exception {
        try {
            // Log the raw SOAP response
            LOGGER.info("Raw SOAP Response: {}", respuestaAeatModelo172);
            System.out.println("Raw SOAP Response: " + respuestaAeatModelo172);

            // Step 1: Extract the <RespuestaDeclaracion> element
            String responseBodyXml = XmlUtils.extractSoapBody(respuestaAeatModelo172);
            LOGGER.info("Extracted Response Body: {}", responseBodyXml);
            System.out.println("Extracted Response Body: " + responseBodyXml);

            // Step 2: Remove namespaces
            String cleanedXml = XStreamUtils.removeNamespaces(responseBodyXml);
            LOGGER.info("Cleaned XML: {}", cleanedXml);
            System.out.println("Cleaned XML: " + cleanedXml);

            // Step 3: Deserialize the cleaned XML into a RespuestaDeclaracionType object
            SerializationService2<RespuestaDeclaracionType> serializationService = new SerializationService2<>();
            RespuestaDeclaracionType respuestaDeclaracion = serializationService.deserialize(cleanedXml);


                // Validate that the cleaned XML is not empty
            if (cleanedXml == null || cleanedXml.isEmpty()) {
                throw new IllegalArgumentException("Cleaned XML is empty.");
            }

            // Log the parsed object
            LOGGER.info("Parsed Object: {}", respuestaDeclaracion);
            System.out.println("Parsed Object: " + respuestaDeclaracion);


            // Access the timestamp as a Date
            if (respuestaDeclaracion.getDatosPresentacion() != null) {
                Date timestamp = respuestaDeclaracion.getDatosPresentacion().getTimestampPresentacion();
                LOGGER.info("Parsed Timestamp: {}", timestamp);
            }


            // Process the parsed object (e.g., extract fields)
            // Handle missing CSV
            String csv = respuestaDeclaracion.getCsv();
            if ( csv == null) {
                LOGGER.warn("The <CSV> element is missing in the SOAP response. Submission was likely rejected.");
                System.out.println("CSV: " + csv);
            } else {
             csv = respuestaDeclaracion.getCsv();
            }
            DatosPresentacionType datosPresentacion = respuestaDeclaracion.getDatosPresentacion();
            CabeceraDI cabecera = respuestaDeclaracion.getCabecera();
            String estadoEnvio = respuestaDeclaracion.getEstadoEnvio();
            //TimestampPresentacion

            List<RespuestaOperacionesType> respuestaLinea = respuestaDeclaracion.getRespuestaLinea();

            // Debugging output
           // LOGGER.info("CSV: {}", csv);
           // System.out.println("CSV: " + csv);
            LOGGER.info("DatosPresentacion: {}", datosPresentacion);
            System.out.println("DatosPresentacion: " + datosPresentacion);
            LOGGER.info("Cabecera: {}", cabecera);
            System.out.println("Cabecera: " + cabecera);
            LOGGER.info("EstadoEnvio: {}", estadoEnvio);
            System.out.println("EstadoEnvio: " + estadoEnvio);
            LOGGER.info("RespuestaLinea: {}", respuestaLinea);
            System.out.println("RespuestaLinea: " + respuestaLinea);

            // Process the response based on EstadoEnvio
            List<Map<String, Object>> processedData = new ArrayList<>();
            if ("Aceptacion Completa".equals(estadoEnvio)) {
                // All records are accepted
                for (RespuestaOperacionesType linea : respuestaLinea) {
                    Map<String, Object> record = new HashMap<>();
                    record.put("IDRegistroDeclarado", linea.getIdRegistroDeclarado());
                    record.put("EstadoRegistro", linea.getEstadoRegistro());
                    processedData.add(record);
                }
            } else if ("Rechazada".equals(estadoEnvio) || "AceptadaParcialmente".equals(estadoEnvio)) {
                // Some or all records are rejected
                for (RespuestaOperacionesType linea : respuestaLinea) {
                    Map<String, Object> record = new HashMap<>();
                    record.put("IDRegistroDeclarado", linea.getIdRegistroDeclarado());
                    record.put("EstadoRegistro", linea.getEstadoRegistro());

                    // Add error details if available
                    if (linea.getErrores() != null && !linea.getErrores().isEmpty()) {
                        List<Map<String, String>> errores = new ArrayList<>();
                        for (ErrorType error : linea.getErrores()) {
                            Map<String, String> errorDetails = new HashMap<>();
                            errorDetails.put("CodigoError", error.getCodigoError());
                            errorDetails.put("DescripcionError", error.getDescripcionError());
                            errorDetails.put("ElementoError", error.getElementoError());
                            errores.add(errorDetails);
                        }
                        record.put("Errores", errores);
                    }

                    processedData.add(record);
                }
            } else {
                // Handle unexpected EstadoEnvio values
                LOGGER.error("Unexpected EstadoEnvio value: {}", estadoEnvio);
                throw new IllegalStateException("Unexpected EstadoEnvio value: " + estadoEnvio);
            }

            return processedData;
        } catch (Exception e) {
            LOGGER.error("Error processing AEAT response: {}", e.getMessage(), e);
            throw e;
        }
    }








}
