package com.bbva.fsia.batch.process;

import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public  class ProcessTrade implements ItemProcessor<TradeOperation, DeclarationModel172> {
        public static final Logger LOGGER = LoggerFactory.getLogger(ProcessTrade.class);

        private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");



        @Override
        public DeclarationModel172 process(TradeOperation trade) throws Exception {

                // Create Header
                Header header = new Header();
                header.setCommunicationType("A0");
                header.setModel("172");
                header.setFiscalYear("2023");
                header.setModelVersionId("1.0");

                // Create Declarant (Declarante)
                Declarant declarant = new Declarant();
                declarant.setTaxId("A48265169");
                declarant.setCompanyName("BANCO BILBAO VIZCAYA ARGENTARIA S.A.");
                declarant.setRepresentativeTaxId("XXXXXXXX");

                // Create Contact Person
                ContactPerson contactPerson = new ContactPerson();
                contactPerson.setPhone("+34987654321");
                contactPerson.setFullName("John Doe");

                declarant.setContactPerson(contactPerson);
                header.setDeclarant(declarant);

                // Create Address (Domicilio)
                Address address = new Address();
                address.setCountry("ES");  // Spain
                address.setCity("Madrid");
                address.setStreet("Gran Via");
                address.setNumber("12");
                address.setPostalCode("28013");

                // Create Virtual Currency (MonedaVirtual)
                VirtualCurrency virtualCurrency = new VirtualCurrency();
                virtualCurrency.setCurrencyType("V");
                virtualCurrency.setCurrencyName(trade.getGfAssetPairName()); // Mapping from TradeOperation
                virtualCurrency.setCurrencySymbol(trade.getTradeAmountAssetName()); // Mapping from TradeOperation
                virtualCurrency.setNumberOfUnits(trade.getGfTradeEx1Amount() != null ? trade.getGfTradeEx1Amount() : 0);
                virtualCurrency.setCurrencyValue(trade.getGfNetPriceAmount() != null ? trade.getGfNetPriceAmount() : 0.0);
                virtualCurrency.setValueSource("CoinMarketCap");

                // Date handling for Custody End Date
                if (trade.getGfTrdDate() != null) {
                        virtualCurrency.setCustodyEndDate(trade.getGfTrdDate());
                }

                // Balance at Year End
                virtualCurrency.setBalanceAtYearEnd(trade.getGfNetAssetAmount() != null ? trade.getGfNetAssetAmount() : 0.0);

                // Create Declared Entity (Declarado)
                DeclaredEntity declaredEntity = new DeclaredEntity();
                declaredEntity.setDeclaredRecordId(trade.getGfTradeId()); // Mapping from TradeOperation
                declaredEntity.setKey("T");
                declaredEntity.setFullName(trade.getClients()); // Mapping to fullName
                declaredEntity.setAddress(address);
                declaredEntity.setVirtualCurrencies(Collections.singletonList(virtualCurrency));

                // Create the DeclarationModel172 and set it up
                DeclarationModel172 declaration = new DeclarationModel172();
                declaration.setCabecera(header);

                // Add the Declared Entity and set it in the declaration
                declaration.setDeclaredEntities(Collections.singletonList(declaredEntity));

                // Debug Output
                LOGGER.info("Generated DeclarationModel: {}", declaration);

                return declaration;
        }
}