package com.bbva.fsia.batch.read;

import com.bbva.fsia.dto.artica.trade.TradeOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.function.DoubleConsumer;



public class ReadTrade implements ItemReader<TradeOperation>, StepExecutionListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReadTrade.class);
    private Resource resource;
    private boolean isFileRead;
    private BufferedReader br;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("ReadTrade - beforeStep : " + stepExecution.getSummary());
    }

    private void openFile() throws Exception {
        LOGGER.info("File path: {}", resource.getFilename());
        br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        // Skip header
        String headerLine = br.readLine();
        isFileRead = true;
    }

    @Override
    public TradeOperation read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!isFileRead) {
            openFile();
        }

        String line = br.readLine();
        if (line != null) {
            List<String> values = Arrays.asList(line.split(";"));
            TradeOperation trade = new TradeOperation();

            // Parse numeric fields using setDoubleValue
            setDoubleValue(values, 0, value -> trade.setGfTradeId((int) value)); // GF_TRADE_ID
            setDoubleValue(values, 1, trade::setGfOrderId); // GF_ORDER_ID

            // Parse string fields
            trade.setContractId(getValue(values, 2)); // CONTRACT_ID
            trade.setClients(getValue(values, 3)); // CLIENTS
            trade.setGfTradeType(getValue(values, 4)); // GF_TRADE_TYPE

            // Parse date fields
            parseDate(values, 5).ifPresent(trade::setGfTrdDate); // GF_TRD_DATE

            // Repeat for other fields...
            trade.setGfAssetPairName(getValue(values, 6)); // GF_ASSET_PAIR_NAME
            trade.setTradeAmountAssetName(getValue(values, 7)); // TRADE_AMOUNT_ASSET_NAME
            setDoubleValue(values, 8, trade::setGfTradeEx1Amount); // GF_TRADE_EX1_AMOUNT
            setDoubleValue(values, 9, trade::setGfGrossPriceAmount); // GF_GROSS_PRICE_AMOUNT
            trade.setGrossPriceAssetName(getValue(values, 10)); // GROSS_PRICE_ASSET_NAME
            setDoubleValue(values, 11, trade::setGfGrossEx1Amount); // GF_GROSS_EX1_AMOUNT
            trade.setGrossAmountAssetName(getValue(values, 12)); // GROSS_AMOUNT_ASSET_NAME
            setDoubleValue(values, 13, trade::setGfFeeAmount); // GF_FEE_AMOUNT
            trade.setGfCommissionAmountAssetName(getValue(values, 14)); // GF_COMMISSION_AMOUNT_ASSET_NAME
            setDoubleValue(values, 15, trade::setGfNetPriceAmount); // GF_NET_PRICE_AMOUNT
            trade.setNetPriceAssetName(getValue(values, 16)); // NET_PRICE_ASSET_NAME
            setDoubleValue(values, 17, trade::setGfNetAssetAmount); // GF_NET_ASSET_AMOUNT
            setDoubleValue(values, 18, trade::setNetAmountAssetName); // NET_AMOUNT_ASSET_NAME

            parseDate(values, 19).ifPresent(trade::setGfExecutionDate); // GF_EXECUTION_DATE
            parseDate(values, 20).ifPresent(trade::setGfCustomerSettlementDate); // GF_CUSTOMER_SETTLEMENT_DATE
            trade.setGfCustomerSettlementStatusId(getValue(values, 21)); // GF_CUSTOMER_SETTLEMENT_STATUS_ID
            parseDate(values, 22).ifPresent(trade::setGfMarketSettlementDate); // GF_MARKET_SETTLEMENT_DATE
            trade.setGfMarketSettlementStatusId(getValue(values, 23)); // GF_MARKET_SETTLEMENT_STATUS_ID
            parseDate(values, 24).ifPresent(trade::setGfGlAccountDate); // GF_GL_ACCOUNT_DATE

            setDoubleValue(values, 25, trade::setGfTaxAmount); // GF_TAX_AMOUNT
            setDoubleValue(values, 26, trade::setGfTaxCurrency2Id); // GF_TAX_CURRENCY2_ID
            setDoubleValue(values, 27, trade::setGfBrokerageFee1Amount); // GF_BROKERAGE_FEE1_AMOUNT
            setDoubleValue(values, 28, trade::setGfBrokerageCurrencyId); // GF_BROKERAGE_CURRENCY_ID

            return trade;
        } else {
            br.close();
            isFileRead = false;
            return null;
        }
    }

    private Optional<Date> parseDate(List<String> values, int index) {
        if (values.size() > index) {
            String dateValue = values.get(index).trim();
            if (!dateValue.isEmpty()) {
                try {
                    // Validate and parse the date based on its format
                    if (isValidDate(dateValue, "dd-MM-yyyy")) {
                        LocalDate localDate = LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        return Optional.of(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    } else if (isValidDate(dateValue, "yyyy-MM-dd")) {
                        LocalDate localDate = LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        return Optional.of(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    } else {
                        LOGGER.error("Invalid date format: {}", dateValue);
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed to parse date: {}", dateValue, e);
                }
            } else {
                LOGGER.debug("Date value is empty at index: {}", index);
            }
        } else {
            LOGGER.debug("Index out of bounds: {}", index);
        }
        return Optional.empty();
    }

    private boolean isValidDate(String dateValue, String pattern) {
        switch (pattern) {
            case "dd-MM-yyyy":
                return dateValue.matches("\\d{2}-\\d{2}-\\d{4}");
            case "yyyy-MM-dd":
                return dateValue.matches("\\d{4}-\\d{2}-\\d{2}");
            default:
                return false;
        }
    }

    private boolean isValidDouble(String value) {
        if (value == null || value.isEmpty()) {
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
        String value = getValue(values, index);
        if (isValidDouble(value)) {
            setter.accept(Double.parseDouble(value));
        } else {
            LOGGER.debug("Invalid or missing double value at index: {}", index);
        }
    }

    private String getValue(List<String> values, int index) {
        return index < values.size() ? values.get(index).trim() : null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}