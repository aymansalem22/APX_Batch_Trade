package com.bbva.fsia.batch.process;

import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;
import java.util.Collections;


public  class ProcessTrade implements ItemProcessor<TradeOperation, DeclarationModel172> {
        public static final Logger LOGGER = LoggerFactory.getLogger(ProcessTrade.class);


        private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy"); // ✅ Ensure correct XML date format

        @Override
        public DeclarationModel172 process(TradeOperation trade) throws Exception {

                // ✅ 1. Create Header
                Header header = new Header();
                header.setCommunicationType("A0");
                header.setModel("172");
                header.setFiscalYear("2024");
                header.setModelVersionId("1.0");

                // ✅ 2. Create Declarant
                Declarant declarant = new Declarant();
                declarant.setTaxId("XXXXXXXXX");
                declarant.setCompanyName("My Company");

                // ✅ 3. Create Contact Person
                ContactPerson contactPerson = new ContactPerson();
                contactPerson.setPhone("+34987654321");
                contactPerson.setFullName("John Doe");

                declarant.setContactPerson(contactPerson);
                header.setDeclarant(declarant);

                // ✅ 4. Create Address
                Address address = new Address();
                address.setCountry("ES");
                address.setCity("Madrid");
                address.setStreet("Gran Via");
                address.setNumber("12");
                address.setPostalCode("28013");

                // ✅ 5. Create Virtual Currency details
                VirtualCurrency virtualCurrency = new VirtualCurrency();
                virtualCurrency.setCurrencyType("V");
                virtualCurrency.setCurrencyName(trade.getGfAssetPairName());
                virtualCurrency.setCurrencySymbol(trade.getTradeAmountAssetName());
                virtualCurrency.setNumberOfUnits(trade.getGfTradeEx1Amount() != null ? trade.getGfTradeEx1Amount() : 0.0);
                virtualCurrency.setCurrencyValue(trade.getGfNetPriceAmount() != null ? trade.getGfNetPriceAmount() : 0.0);
                virtualCurrency.setValueSource("CoinMarketCap");

                // ✅ Ensure custodyEndDate is formatted correctly
                if (trade.getGfTrdDate() != null) {
                        virtualCurrency.setCustodyEndDate(DATE_FORMAT.parse(DATE_FORMAT.format(trade.getGfTrdDate())));
                } else {
                        virtualCurrency.setCustodyEndDate(DATE_FORMAT.parse("01-01-2024")); // ✅ Default to avoid XML errors
                }

                // ✅ 6. Map Balance at Year End (SaldoMonedaVirtual)
                virtualCurrency.setBalanceAtYearEnd(trade.getGfNetAssetAmount() != null ? trade.getGfNetAssetAmount() : 0.0);

                // ✅ 7. Create Declared Entity
                DeclaredEntity declaredEntity = new DeclaredEntity();
                declaredEntity.setDeclaredRecordId(trade.getGfTradeId());
                declaredEntity.setKey("T");
                declaredEntity.setFullName(trade.getClients());
                declaredEntity.setAddress(address);
                declaredEntity.setVirtualCurrencies(Collections.singletonList(virtualCurrency));

                // ✅ 8. Create Declaration Model
                DeclarationModel172 declaration = new DeclarationModel172();
                declaration.setCabecera(header);
                declaration.setDeclaredEntities(Collections.singletonList(declaredEntity));

                return declaration;
        }
}
