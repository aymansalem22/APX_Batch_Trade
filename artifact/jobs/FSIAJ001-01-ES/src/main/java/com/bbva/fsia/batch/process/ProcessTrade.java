package com.bbva.fsia.batch.process;

import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.text.SimpleDateFormat;
import java.util.Collections;



public  class ProcessTrade implements ItemProcessor<TradeOperation, DeclaredEntity> {
        public static final Logger LOGGER = LoggerFactory.getLogger(ProcessTrade.class);
        private static Header header;


        @Override
        public DeclaredEntity process(TradeOperation trade) throws Exception {

                if (header == null) {
                        header = new Header();
                        header.setCommunicationType("A0");
                        header.setModel("172");
                        header.setFiscalYear("2023");
                        header.setModelVersionId("1.0");

                        Declarant declarant = new Declarant();
                        declarant.setTaxId("A48265169");
                        declarant.setCompanyName("BANCO BILBAO VIZCAYA ARGENTARIA S.A.");
                        declarant.setRepresentativeTaxId("XXXXXXXX");
                        header.setDeclarant(declarant);
                }

                // Create Address (Domicilio)
                Address address = new Address();
                address.setCountry("ES");  // Spain
                address.setCity("Madrid");
                address.setStreet("Gran Via");
                address.setNumber("12");
                address.setPostalCode("28013");

                // Create Virtual Currency (MonedaVirtual)
                IDMonedas IDMonedas = new IDMonedas();
                IDMonedas.setCurrencyType("V");
                VirtualCurrency virtualCurrency = new VirtualCurrency();
                TypeCurrency typeCurrency = new TypeCurrency();
                typeCurrency.setCurrencySymbol(trade.getTradeAmountAssetName());
                typeCurrency.setCurrencyName(trade.getGfAssetPairName());
                virtualCurrency.setTypeCurrency(typeCurrency);
                virtualCurrency.setNumberOfUnits(trade.getGfTradeEx1Amount() != null ? trade.getGfTradeEx1Amount() : 0);
                virtualCurrency.setCurrencyValue(trade.getGfNetPriceAmount() != null ? trade.getGfNetPriceAmount() : 0.0);
                virtualCurrency.setValueSource("CoinMarketCap");
                if (trade.getGfTrdDate() != null) {
                        virtualCurrency.setCustodyEndDate(trade.getGfTrdDate());
                }
                virtualCurrency.setBalanceAtYearEnd(trade.getGfNetAssetAmount() != null ? trade.getGfNetAssetAmount() : 0.0);
                IDMonedas.setVirtualCurrency(virtualCurrency);



                // Create Declared Entity (Declarado)
                DeclaredEntity declaredEntity = new DeclaredEntity();
                declaredEntity.setDeclaredRecordId(trade.getGfTradeId()); // Mapping from TradeOperation
                DeclaredPerson declaredPerson = new DeclaredPerson();
                declaredPerson.setFullName(trade.getClients());
                declaredPerson.setKey("T");
                declaredPerson.setAddress(address);
                declaredEntity.setPerson(declaredPerson);
                declaredEntity.setVirtualCurrencies(IDMonedas);

                // Create the DeclarationModel172 and set it up
                DeclarationModel172 declaration = new DeclarationModel172();
                declaration.setHeader(header);
                declaration.setDeclaredEntities(Collections.singletonList(declaredEntity));


                LOGGER.info("Generated DeclarationModel: {}", declaration);

                return declaredEntity;
        }

        public static Header getHeader() {
                return header;
        }
}