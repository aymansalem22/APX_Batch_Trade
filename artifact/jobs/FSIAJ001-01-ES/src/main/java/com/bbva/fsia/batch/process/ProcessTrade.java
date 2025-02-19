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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public DeclarationModel172 process(TradeOperation trade) throws Exception {

                // ✅ 1. Create Header with Fixed Values
                Header header = new Header();
                header.setCommunicationType("A0");  // Fixed value
                header.setModel("172");  // Fixed value
                header.setFiscalYear("2023");  // Fixed value
                header.setModelVersionId("1.0");  // Fixed value

                // ✅ 2. Create Declarant (IDDeclarante) and set fixed values
                Declarant declarant = new Declarant();
                declarant.setTaxId("A48265169");  // Fixed value
                declarant.setCompanyName("BANCO BILBAO VIZCAYA ARGENTARIA S.A.");  // Fixed value
                declarant.setRepresentativeTaxId("XXXXXXXX");  // Fixed value


                // ✅ 3. Set Declarant inside Header
                header.setDeclarant(declarant);


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

                if (trade.getGfTrdDate() != null) {
                        virtualCurrency.setCustodyEndDate(trade.getGfTrdDate());
                } else {
                        virtualCurrency.setCustodyEndDate(null);
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
                //print the delcartion model with sout
                System.out.println("Declaration Model: " + declaration);
                LOGGER.info("Declaration Model: {}", declaration);
                return declaration;
        }


}
