package com.bbva.fsia.dto.artica.trade;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * The TradeOperation class...
 */
public class TradeOperation implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	private Integer gfTradeId;
	private Double gfOrderId;
	private String contractId;
	private String clients;
	private String gfTradeType;
	private Date gfTrdDate;
	private String gfAssetPairName;
	private String tradeAmountAssetName;
	private Double gfTradeEx1Amount;
	private Double gfGrossPriceAmount;
	private String grossPriceAssetName;
	private Double gfGrossEx1Amount;
	private String grossAmountAssetName;
	private Double gfFeeAmount;
	private String gfCommissionAmountAssetName;
	private Double gfNetPriceAmount;
	private String netPriceAssetName;
	private Double gfNetAssetAmount;
	private Double netAmountAssetName;
	private Date gfExecutionDate;
	private Date gfCustomerSettlementDate;
	private String gfCustomerSettlementStatusId;
	private Date gfMarketSettlementDate;
	private String gfMarketSettlementStatusId;
	private Date gfGlAccountDate;
	private Double gfTaxAmount;
	private Double gfTaxCurrency2Id;
	private Double gfBrokerageFee1Amount;
	private Double gfBrokerageCurrencyId;


	public Integer getGfTradeId() {
		return gfTradeId;
	}

	public void setGfTradeId(Integer gfTradeId) {
		this.gfTradeId = gfTradeId;
	}

	public Double getGfOrderId() {
		return gfOrderId;
	}

	public void setGfOrderId(Double gfOrderId) {
		this.gfOrderId = gfOrderId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

	public String getGfTradeType() {
		return gfTradeType;
	}

	public void setGfTradeType(String gfTradeType) {
		this.gfTradeType = gfTradeType;
	}

	public Date getGfTrdDate() {
		return gfTrdDate;
	}

	public void setGfTrdDate(Date gfTrdDate) {
		this.gfTrdDate = gfTrdDate;
	}

	public String getGfAssetPairName() {
		return gfAssetPairName;
	}

	public void setGfAssetPairName(String gfAssetPairName) {
		this.gfAssetPairName = gfAssetPairName;
	}

	public String getTradeAmountAssetName() {
		return tradeAmountAssetName;
	}

	public void setTradeAmountAssetName(String tradeAmountAssetName) {
		this.tradeAmountAssetName = tradeAmountAssetName;
	}

	public Double getGfTradeEx1Amount() {
		return gfTradeEx1Amount;
	}

	public void setGfTradeEx1Amount(Double gfTradeEx1Amount) {
		this.gfTradeEx1Amount = gfTradeEx1Amount;
	}

	public Double getGfGrossPriceAmount() {
		return gfGrossPriceAmount;
	}

	public void setGfGrossPriceAmount(Double gfGrossPriceAmount) {
		this.gfGrossPriceAmount = gfGrossPriceAmount;
	}

	public String getGrossPriceAssetName() {
		return grossPriceAssetName;
	}

	public void setGrossPriceAssetName(String grossPriceAssetName) {
		this.grossPriceAssetName = grossPriceAssetName;
	}

	public Double getGfGrossEx1Amount() {
		return gfGrossEx1Amount;
	}

	public void setGfGrossEx1Amount(Double gfGrossEx1Amount) {
		this.gfGrossEx1Amount = gfGrossEx1Amount;
	}

	public String getGrossAmountAssetName() {
		return grossAmountAssetName;
	}

	public void setGrossAmountAssetName(String grossAmountAssetName) {
		this.grossAmountAssetName = grossAmountAssetName;
	}

	public Double getGfFeeAmount() {
		return gfFeeAmount;
	}

	public void setGfFeeAmount(Double gfFeeAmount) {
		this.gfFeeAmount = gfFeeAmount;
	}

	public String getGfCommissionAmountAssetName() {
		return gfCommissionAmountAssetName;
	}

	public void setGfCommissionAmountAssetName(String gfCommissionAmountAssetName) {
		this.gfCommissionAmountAssetName = gfCommissionAmountAssetName;
	}

	public Double getGfNetPriceAmount() {
		return gfNetPriceAmount;
	}

	public void setGfNetPriceAmount(Double gfNetPriceAmount) {
		this.gfNetPriceAmount = gfNetPriceAmount;
	}

	public String getNetPriceAssetName() {
		return netPriceAssetName;
	}

	public void setNetPriceAssetName(String netPriceAssetName) {
		this.netPriceAssetName = netPriceAssetName;
	}

	public Double getGfNetAssetAmount() {
		return gfNetAssetAmount;
	}

	public void setGfNetAssetAmount(Double gfNetAssetAmount) {
		this.gfNetAssetAmount = gfNetAssetAmount;
	}

	public Double getNetAmountAssetName() {
		return netAmountAssetName;
	}

	public void setNetAmountAssetName(Double netAmountAssetName) {
		this.netAmountAssetName = netAmountAssetName;
	}

	public Date getGfExecutionDate() {
		return gfExecutionDate;
	}

	public void setGfExecutionDate(Date gfExecutionDate) {
		this.gfExecutionDate = gfExecutionDate;
	}

	public Date getGfCustomerSettlementDate() {
		return gfCustomerSettlementDate;
	}

	public void setGfCustomerSettlementDate(Date gfCustomerSettlementDate) {
		this.gfCustomerSettlementDate = gfCustomerSettlementDate;
	}

	public String getGfCustomerSettlementStatusId() {
		return gfCustomerSettlementStatusId;
	}

	public void setGfCustomerSettlementStatusId(String gfCustomerSettlementStatusId) {
		this.gfCustomerSettlementStatusId = gfCustomerSettlementStatusId;
	}

	public Date getGfMarketSettlementDate() {
		return gfMarketSettlementDate;
	}

	public void setGfMarketSettlementDate(Date gfMarketSettlementDate) {
		this.gfMarketSettlementDate = gfMarketSettlementDate;
	}

	public String getGfMarketSettlementStatusId() {
		return gfMarketSettlementStatusId;
	}

	public void setGfMarketSettlementStatusId(String gfMarketSettlementStatusId) {
		this.gfMarketSettlementStatusId = gfMarketSettlementStatusId;
	}

	public Date getGfGlAccountDate() {
		return gfGlAccountDate;
	}

	public void setGfGlAccountDate(Date gfGlAccountDate) {
		this.gfGlAccountDate = gfGlAccountDate;
	}

	public Double getGfTaxAmount() {
		return gfTaxAmount;
	}

	public void setGfTaxAmount(Double gfTaxAmount) {
		this.gfTaxAmount = gfTaxAmount;
	}

	public Double getGfTaxCurrency2Id() {
		return gfTaxCurrency2Id;
	}

	public void setGfTaxCurrency2Id(Double gfTaxCurrency2Id) {
		this.gfTaxCurrency2Id = gfTaxCurrency2Id;
	}

	public Double getGfBrokerageFee1Amount() {
		return gfBrokerageFee1Amount;
	}

	public void setGfBrokerageFee1Amount(Double gfBrokerageFee1Amount) {
		this.gfBrokerageFee1Amount = gfBrokerageFee1Amount;
	}

	public Double getGfBrokerageCurrencyId() {
		return gfBrokerageCurrencyId;
	}

	public void setGfBrokerageCurrencyId(Double gfBrokerageCurrencyId) {
		this.gfBrokerageCurrencyId = gfBrokerageCurrencyId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		TradeOperation that = (TradeOperation) o;

		return new EqualsBuilder().append(gfTradeId, that.gfTradeId).append(gfOrderId, that.gfOrderId).append(contractId, that.contractId).append(clients, that.clients).append(gfTradeType, that.gfTradeType).append(gfTrdDate, that.gfTrdDate).append(gfAssetPairName, that.gfAssetPairName).append(tradeAmountAssetName, that.tradeAmountAssetName).append(gfTradeEx1Amount, that.gfTradeEx1Amount).append(gfGrossPriceAmount, that.gfGrossPriceAmount).append(grossPriceAssetName, that.grossPriceAssetName).append(gfGrossEx1Amount, that.gfGrossEx1Amount).append(grossAmountAssetName, that.grossAmountAssetName).append(gfFeeAmount, that.gfFeeAmount).append(gfCommissionAmountAssetName, that.gfCommissionAmountAssetName).append(gfNetPriceAmount, that.gfNetPriceAmount).append(netPriceAssetName, that.netPriceAssetName).append(gfNetAssetAmount, that.gfNetAssetAmount).append(netAmountAssetName, that.netAmountAssetName).append(gfExecutionDate, that.gfExecutionDate).append(gfCustomerSettlementDate, that.gfCustomerSettlementDate).append(gfCustomerSettlementStatusId, that.gfCustomerSettlementStatusId).append(gfMarketSettlementDate, that.gfMarketSettlementDate).append(gfMarketSettlementStatusId, that.gfMarketSettlementStatusId).append(gfGlAccountDate, that.gfGlAccountDate).append(gfTaxAmount, that.gfTaxAmount).append(gfTaxCurrency2Id, that.gfTaxCurrency2Id).append(gfBrokerageFee1Amount, that.gfBrokerageFee1Amount).append(gfBrokerageCurrencyId, that.gfBrokerageCurrencyId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(gfTradeId).append(gfOrderId).append(contractId).append(clients).append(gfTradeType).append(gfTrdDate).append(gfAssetPairName).append(tradeAmountAssetName).append(gfTradeEx1Amount).append(gfGrossPriceAmount).append(grossPriceAssetName).append(gfGrossEx1Amount).append(grossAmountAssetName).append(gfFeeAmount).append(gfCommissionAmountAssetName).append(gfNetPriceAmount).append(netPriceAssetName).append(gfNetAssetAmount).append(netAmountAssetName).append(gfExecutionDate).append(gfCustomerSettlementDate).append(gfCustomerSettlementStatusId).append(gfMarketSettlementDate).append(gfMarketSettlementStatusId).append(gfGlAccountDate).append(gfTaxAmount).append(gfTaxCurrency2Id).append(gfBrokerageFee1Amount).append(gfBrokerageCurrencyId).toHashCode();
	}

	@Override
	public String toString() {
		return "TradeOperation{" +
				"gfTradeId=" + gfTradeId +
				", gfOrderId=" + gfOrderId +
				", contractId='" + contractId + '\'' +
				", clients='" + clients + '\'' +
				", gfTradeType='" + gfTradeType + '\'' +
				", gfTrdDate=" + gfTrdDate +
				", gfAssetPairName='" + gfAssetPairName + '\'' +
				", tradeAmountAssetName='" + tradeAmountAssetName + '\'' +
				", gfTradeEx1Amount=" + gfTradeEx1Amount +
				", gfGrossPriceAmount=" + gfGrossPriceAmount +
				", grossPriceAssetName='" + grossPriceAssetName + '\'' +
				", gfGrossEx1Amount=" + gfGrossEx1Amount +
				", grossAmountAssetName='" + grossAmountAssetName + '\'' +
				", gfFeeAmount=" + gfFeeAmount +
				", gfCommissionAmountAssetName='" + gfCommissionAmountAssetName + '\'' +
				", gfNetPriceAmount=" + gfNetPriceAmount +
				", netPriceAssetName='" + netPriceAssetName + '\'' +
				", gfNetAssetAmount=" + gfNetAssetAmount +
				", netAmountAssetName=" + netAmountAssetName +
				", gfExecutionDate=" + gfExecutionDate +
				", gfCustomerSettlementDate=" + gfCustomerSettlementDate +
				", gfCustomerSettlementStatusId='" + gfCustomerSettlementStatusId + '\'' +
				", gfMarketSettlementDate=" + gfMarketSettlementDate +
				", gfMarketSettlementStatusId='" + gfMarketSettlementStatusId + '\'' +
				", gfGlAccountDate=" + gfGlAccountDate +
				", gfTaxAmount=" + gfTaxAmount +
				", gfTaxCurrency2Id=" + gfTaxCurrency2Id +
				", gfBrokerageFee1Amount=" + gfBrokerageFee1Amount +
				", gfBrokerageCurrencyId=" + gfBrokerageCurrencyId +
				'}';
	}
}
