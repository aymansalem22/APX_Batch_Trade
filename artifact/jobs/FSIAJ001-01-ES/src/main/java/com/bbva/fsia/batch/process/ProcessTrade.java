package com.bbva.fsia.batch.process;

import com.bbva.fsia.dto.artica.trade.TradeOperation;
import com.bbva.fsia.dto.artica.xml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;



public  class ProcessTrade implements ItemProcessor<TradeOperation, DeclaredEntity>, StepExecutionListener {
        public static final Logger LOGGER = LoggerFactory.getLogger(ProcessTrade.class);
        private Header header;
        private static final String[] ADDRESS = {"ES", "Madrid", "Gran Via", "12", "28013"};


        @Override
        public DeclaredEntity process(TradeOperation trade) {
                header.setCommunicationType(trade.getGfTradeType());
                DeclaredEntity entity = createBaseEntity(trade);
                populateVirtualCurrency(entity, trade);
                return entity;
        }

        private DeclaredEntity createBaseEntity(TradeOperation trade) {
                DeclaredEntity entity = new DeclaredEntity();
                entity.setDeclaredRecordId(trade.getGfTradeId());
                entity.setPerson(createPerson(trade));
                return entity;
        }

        private DeclaredPerson createPerson(TradeOperation trade) {
                DeclaredPerson person = new DeclaredPerson();
                person.setFullName(trade.getClients());
                person.setKey("T");
                person.setAddress(createAddress());
                return person;
        }

        private Address createAddress() {
                Address address = new Address();
                address.setCountry(ADDRESS[0]);
                address.setCity(ADDRESS[1]);
                address.setStreet(ADDRESS[2]);
                address.setNumber(ADDRESS[3]);
                address.setPostalCode(ADDRESS[4]);
                return address;
        }

        private void populateVirtualCurrency(DeclaredEntity entity, TradeOperation trade) {
                IDMonedas monedas = new IDMonedas();
                monedas.setCurrencyType("V");

                VirtualCurrency vc = new VirtualCurrency();
                vc.setTypeCurrency(createTypeCurrency(trade));
                vc.setNumberOfUnits(getDoubleValue(trade.getGfTradeEx1Amount()));
                vc.setCurrencyValue(getDoubleValue(trade.getGfNetPriceAmount()));
                vc.setValueSource("CoinMarketCap");



                vc.setCustodyEndDate(trade.getGfTrdDate());




                vc.setBalanceAtYearEnd(getDoubleValue(trade.getGfNetAssetAmount()));

                monedas.setVirtualCurrency(vc);
                entity.setVirtualCurrencies(monedas);
        }

        private TypeCurrency createTypeCurrency(TradeOperation trade) {
                TypeCurrency type = new TypeCurrency();
                type.setCurrencySymbol(trade.getTradeAmountAssetName());
                type.setCurrencyName(trade.getGfAssetPairName());
                return type;
        }

        private int getIntValue(Integer value) {
                return value != null ? value : 0;
        }

        private double getDoubleValue(Double value) {
                return value != null ? value : 0.0;
        }

        @Override
        public void beforeStep(StepExecution stepExecution) {
                header = createHeader(null); // Pass null initially
                stepExecution.getExecutionContext().put("header", header);
        }

        @Override
        public ExitStatus afterStep(StepExecution stepExecution) {
                return null;
        }

        private Header createHeader(TradeOperation trade) {
                Header header = new Header();
                header.setCommunicationType(trade != null ? trade.getGfTradeType() : "A0"); // Default to "A0" if no trade yet
                header.setModel("172");
                header.setPeriod(createPeriod());
                header.setModelVersionId("1.0");
                header.setDeclarant(createDeclarant());
                return header;
        }





        private Declarant createDeclarant() {
                Declarant declarant = new Declarant();
                declarant.setTaxId("A48265169");
                declarant.setCompanyName("BANCO BILBAO VIZCAYA ARGENTARIA S.A.");
                declarant.setRepresentativeTaxId("A48265169");
                return declarant;
        }

        private Period createPeriod() {
                Period period = new Period();
                period.setFiscalYear("2024");
                period.setPeriod("4T");
                return period;
        }
}
