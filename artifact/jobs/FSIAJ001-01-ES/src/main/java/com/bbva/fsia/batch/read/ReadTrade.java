package com.bbva.fsia.batch.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bbva.fsia.dto.artica.trade.TradeOperation;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleConsumer;

public class ReadTrade implements ItemReader<TradeOperation>, StepExecutionListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReadTrade.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Resource resource;
    private boolean isFileRead;
    private BufferedReader br;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("ReadTrade - beforeStep : "+stepExecution.getSummary());
    }

    private void openFile() throws Exception {
        LOGGER.info("File path: {}", resource.getFilename());
        br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        //skip header
        String headerLine = br.readLine();
        isFileRead = true;
    }

    @Override
    public TradeOperation read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!isFileRead){
            openFile();
        }

        String line = br.readLine();
        if (line != null) {
            List<String> values = parseLineTrade(line);
            TradeOperation trade = new TradeOperation();
            setDoubleValue(values, 0, value -> trade.setGfTradeId((int) value));
            setDoubleValue(values, 1, trade::setGfOrderId);
            trade.setContractId(getValue(values, 2));
            trade.setClients(getValue(values, 3));
            trade.setGfTradeType(getValue(values, 4));
            parseDate(values, 5).ifPresent(date -> trade.setGfTrdDate(Date.valueOf(date)));
            trade.setGfAssetPairName(getValue(values, 6));
            trade.setTradeAmountAssetName(getValue(values, 7));
            setDoubleValue(values, 8, value -> trade.setGfTradeEx1Amount((int) value));
            setDoubleValue(values, 9, trade::setGfGrossPriceAmount);
            trade.setGrossPriceAssetName(getValue(values, 10));
            setDoubleValue(values, 11, trade::setGfGrossEx1Amount);
            trade.setGrossAmountAssetName(getValue(values, 12));
            setDoubleValue(values, 13, trade::setGfFeeAmount);
            trade.setGfCommissionAmountAssetName(getValue(values, 14));
            setDoubleValue(values, 15, trade::setGfNetPriceAmount);
            trade.setNetPriceAssetName(getValue(values, 16));
            setDoubleValue(values, 17, trade::setGfNetAssetAmount);
            setDoubleValue(values, 18, trade::setNetAmountAssetName);
            parseDate(values, 19).ifPresent(date -> trade.setGfExecutionDate(Date.valueOf(date)));
            parseDate(values, 20).ifPresent(date -> trade.setGfCustomerSettlementDate(Date.valueOf(date)));
            trade.setGfCustomerSettlementStatusId(getValue(values, 21));
            parseDate(values, 22).ifPresent(date -> trade.setGfMarketSettlementDate(Date.valueOf(date)));
            trade.setGfMarketSettlementStatusId(getValue(values, 23));
            parseDate(values, 24).ifPresent(date -> trade.setGfGlAccountDate(Date.valueOf(date)));
            setDoubleValue(values, 25, trade::setGfTaxAmount);
            setDoubleValue(values, 26, trade::setGfTaxCurrency2Id);
            setDoubleValue(values, 27, trade::setGfBrokerageFee1Amount);
            setDoubleValue(values, 28, trade::setGfBrokerageCurrencyId);
            return trade;
        }else {
            br.close();
            isFileRead = false;
            return null;
        }

    }

    private List<String> parseLineTrade(String line) {
        String[] tokens = line.split(";");
        List<String> values = Arrays.asList(tokens);
        return values;
    }

    private boolean isValidDouble(String value) {
        if( value == null || value.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    private void setDoubleValue(List<String> values, int index, DoubleConsumer setter) {
        String value = getValue(values,index);
        if(isValidDouble(value)) {
            setter.accept(Double.parseDouble(value));
        }
    }

    private String getValue(List<String> values, int index) {
        return index < values.size() ? values.get(index).trim() : null;
    }


    private Optional<LocalDate> parseDate(List<String> values, int index) {
        if(values.size() > index) {
            String dateValue = values.get(index).trim();
            if (!dateValue.isEmpty()) {
                return Optional.of(LocalDate.parse(dateValue, formatter));
            } else {
                LOGGER.debug("Date value is empty");
            }
        }else {
            LOGGER.debug("Index out of bounds");
        }
        return Optional.empty();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
