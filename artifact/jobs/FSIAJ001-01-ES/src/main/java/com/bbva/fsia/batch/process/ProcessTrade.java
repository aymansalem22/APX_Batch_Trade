package com.bbva.fsia.batch.process;

import com.bbva.fsia.batch.read.ReadTrade;
import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.xml.StaxEventItemWriter;

import java.util.Collections;


public  class ProcessTrade implements ItemProcessor<TradeOperation, DeclarationModel172> {
        public static final Logger LOGGER = LoggerFactory.getLogger(ProcessTrade.class);


    @Override
    public DeclarationModel172 process(TradeOperation trade) throws Exception {

            // Create Header
            Header header = new Header();
            header.setCommunicationType("A0"); // Fixed value
            header.setModel("172"); // Fixed value
            header.setFiscalYear("2024"); // Fixed value
            header.setModelVersionId("1.0"); // Fixed value

            // Create Declarant
            Declarant declarant = new Declarant();
            declarant.setTaxId("XXXXXXXXX");
            declarant.setCompanyName("My Company");

            // Create Contact Person
            ContactPerson contactPerson = new ContactPerson();
            contactPerson.setPhone("+34987654321");
            contactPerson.setFullName("John Doe");

            // ✅ **Fix: Set Contact Person inside Declarant**
            declarant.setContactPerson(contactPerson);

            // ✅ **Fix: Set Declarant inside Header**
            header.setDeclarant(declarant);

            // Create Address (Fixed values for now)
            Address address = new Address();
            address.setCountry("ES");
            address.setCity("Madrid");
            address.setStreet("Gran Via");
            address.setNumber("12");
            address.setPostalCode("28013");

            // Create Virtual Currency details
            VirtualCurrency virtualCurrency = new VirtualCurrency();
            virtualCurrency.setCurrencyType("V"); // Fixed value for Virtual Currency
            virtualCurrency.setCurrencyName(trade.getGfAssetPairName());
            virtualCurrency.setCurrencySymbol(trade.getTradeAmountAssetName());
            virtualCurrency.setNumberOfUnits(trade.getGfTradeEx1Amount());
            virtualCurrency.setCurrencyValue(trade.getGfNetPriceAmount());
            virtualCurrency.setValueSource("CoinMarketCap"); // Fixed value
            virtualCurrency.setCustodyEndDate(trade.getGfTrdDate().toString());

            // Create Declared Entity
            DeclaredEntity declaredEntity = new DeclaredEntity();
            declaredEntity.setDeclaredRecordId(trade.getGfTradeId());
            declaredEntity.setKey("T"); // Fixed value (Titular)
            declaredEntity.setFullName(trade.getClients());
            declaredEntity.setAddress(address);
            declaredEntity.setVirtualCurrency(virtualCurrency);

            // Create the final Declaration Model
            DeclarationModel172 declaration = new DeclarationModel172();
            declaration.setHeader(header);
            declaration.setDeclaredEntities(Collections.singletonList(declaredEntity));

            LOGGER.info("Created DeclarationModel172: {}", declaration); // ✅ Debug log
            System.out.println("Created DeclarationModel172 "+ declaration);
            return declaration;

    }
}
