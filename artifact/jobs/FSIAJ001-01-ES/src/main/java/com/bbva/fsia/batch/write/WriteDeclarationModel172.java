package com.bbva.fsia.batch.write;


import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.dto.artica.xml.DeclaredEntity;
import com.bbva.fsia.dto.artica.xml.VirtualCurrency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;



public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);

    private WritableResource resource; // ✅ Use WritableResource
    private DeclarationModel172 declarationModel = new DeclarationModel172(); // ✅ Store all declared entities
    private static final SimpleDateFormat XML_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy"); // ✅ Correct Date Format

    public void setResource(Resource resource) {
        this.resource = (WritableResource) resource; // ✅ Ensure it's writable
    }


    @Override
    public void write(List<? extends DeclarationModel172> list) throws Exception {
        if (list == null || list.isEmpty()) {
            LOGGER.warn("No data to write.");
            return;
        }

        LOGGER.info("⏳ Collecting declared entities...");

        if (declarationModel.getCabecera() == null && !list.isEmpty()) {
            declarationModel.setCabecera(list.get(0).getCabecera());
        }

        if (declarationModel.getDeclaredEntities() == null) {
            declarationModel.setDeclaredEntities(new ArrayList<>());
        }

        for (DeclarationModel172 item : list) {
            declarationModel.getDeclaredEntities().addAll(item.getDeclaredEntities());
        }

        File outputFile = resource.getFile();
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists() && parentDir.mkdirs()) {
            LOGGER.info("✅ Created output directory: {}", parentDir.getAbsolutePath());
        }

        LOGGER.info("📂 Writing XML to: {}", outputFile.getAbsolutePath());

        try (OutputStream os = resource.getOutputStream(); StringWriter stringWriter = new StringWriter()) {
            stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            stringWriter.write("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n");
            stringWriter.write("                  xmlns:dec=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/Declaracion.xsd\"\n");
            stringWriter.write("                  xmlns:dec1=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/DeclaracionInformativa.xsd\">\n");
            stringWriter.write("<soapenv:Header/>\n");
            stringWriter.write("<soapenv:Body>\n");
            stringWriter.write("<dec:Declaracion>\n");
            stringWriter.write("    <dec1:Cabecera>\n");
            stringWriter.write("        <dec1:TipoComunicacion>A0</dec1:TipoComunicacion>\n");
            stringWriter.write("        <dec1:Modelo>172</dec1:Modelo>\n");
            stringWriter.write("        <dec1:Ejercicio>2023</dec1:Ejercicio>\n");
            stringWriter.write("        <dec1:IDVersionModelo>1.0</dec1:IDVersionModelo>\n");
            stringWriter.write("        <dec1:IDDeclarante>\n");
            stringWriter.write("            <dec1:NIF>A48265169</dec1:NIF>\n");
            stringWriter.write("            <dec1:NombreRazon>BANCO BILBAO VIZCAYA ARGENTARIA S.A.</dec1:NombreRazon>\n");
            stringWriter.write("            <dec1:NIFRepresentante>XXXXXXXX</dec1:NIFRepresentante>\n");
            stringWriter.write("        </dec1:IDDeclarante>\n");
            stringWriter.write("    </dec1:Cabecera>\n");

            for (DeclaredEntity entity : declarationModel.getDeclaredEntities()) {
                stringWriter.write("    <dec1:Declarado>\n");
                stringWriter.write("        <dec1:IDRegistroDeclarado>" + entity.getDeclaredRecordId() + "</dec1:IDRegistroDeclarado>\n");
                stringWriter.write("        <dec1:Clave>" + entity.getKey() + "</dec1:Clave>\n");
                stringWriter.write("        <dec1:NombreRazon>" + entity.getFullName() + "</dec1:NombreRazon>\n");
                stringWriter.write("        <dec1:Domicilio>\n");
                stringWriter.write("            <dec1:CodigoPais>" + entity.getAddress().getCountry() + "</dec1:CodigoPais>\n");
                stringWriter.write("        </dec1:Domicilio>\n");
                stringWriter.write("        <dec1:IDMonedas>\n");
                stringWriter.write("            <dec1:TipoMoneda>V</dec1:TipoMoneda>\n");
                stringWriter.write("            <dec1:MonedaVirtual>\n");
                stringWriter.write("                <dec1:DenominacionMonedaVirtual>" + entity.getVirtualCurrencies().get(0).getCurrencyName() + "</dec1:DenominacionMonedaVirtual>\n");
                stringWriter.write("                <dec1:SiglasMonedaVirtual>" + entity.getVirtualCurrencies().get(0).getCurrencySymbol() + "</dec1:SiglasMonedaVirtual>\n");
                stringWriter.write("                <dec1:NumMonedas>" + entity.getVirtualCurrencies().get(0).getNumberOfUnits() + "</dec1:NumMonedas>\n");
                stringWriter.write("                <dec1:FechaFinCustodia>" + entity.getVirtualCurrencies().get(0).getCustodyEndDate() + "</dec1:FechaFinCustodia>\n");
                stringWriter.write("            </dec1:MonedaVirtual>\n");
                stringWriter.write("        </dec1:IDMonedas>\n");
                stringWriter.write("    </dec1:Declarado>\n");
            }
            stringWriter.write("</dec:Declaracion>\n");
            stringWriter.write("</soapenv:Body>\n");
            stringWriter.write("</soapenv:Envelope>\n");

            os.write(stringWriter.toString().getBytes());
            LOGGER.info("✅ XML writing completed successfully!");
        } catch (Exception e) {
            LOGGER.error("❌ Error while writing XML: {}", e.getMessage(), e);
        }
    }
}


