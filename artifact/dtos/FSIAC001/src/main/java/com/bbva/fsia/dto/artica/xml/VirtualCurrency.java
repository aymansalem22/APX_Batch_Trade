package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;


@XStreamAlias("dec1:MonedaVirtual")
public class VirtualCurrency {

    @XStreamAlias("dec1:TipoMoneda")
    private String currencyType;

    @XStreamAlias("dec1:DenominacionMonedaVirtual")
    private String currencyName;

    @XStreamAlias("dec1:SiglasMonedaVirtual")
    private String currencySymbol;

    @XStreamAlias("dec1:NumMonedas")
    private Integer numberOfUnits;

    @XStreamAlias("dec1:ValorMonedas")
    private Double currencyValue;

    @XStreamAlias("dec1:OrigenValorMonedas")
    private String valueSource;

    @XStreamAlias("dec1:FechaFinCustodia")
    private Date custodyEndDate;

    @XStreamAlias("dec1:SaldoMonedaVirtual")
    private Double balanceAtYearEnd;


    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }


    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Integer numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }


    public Double getBalanceAtYearEnd() {
        return balanceAtYearEnd;
    }

    public void setBalanceAtYearEnd(Double balanceAtYearEnd) {
        this.balanceAtYearEnd = balanceAtYearEnd;
    }

    public Date getCustodyEndDate() {
        return custodyEndDate;
    }

    public void setCustodyEndDate(Date custodyEndDate) {
        this.custodyEndDate = custodyEndDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        VirtualCurrency that = (VirtualCurrency) o;

        return new EqualsBuilder().append(currencyType, that.currencyType).append(currencyName, that.currencyName).append(currencySymbol, that.currencySymbol).append(numberOfUnits, that.numberOfUnits).append(currencyValue, that.currencyValue).append(valueSource, that.valueSource).append(custodyEndDate, that.custodyEndDate).append(balanceAtYearEnd, that.balanceAtYearEnd).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(currencyType).append(currencyName).append(currencySymbol).append(numberOfUnits).append(currencyValue).append(valueSource).append(custodyEndDate).append(balanceAtYearEnd).toHashCode();
    }

    @Override
    public String toString() {
        return "VirtualCurrency{" +
                "currencyType='" + currencyType + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", currencySymbol='" + currencySymbol + '\'' +
                ", numberOfUnits=" + numberOfUnits +
                ", currencyValue=" + currencyValue +
                ", valueSource='" + valueSource + '\'' +
                ", custodyEndDate=" + custodyEndDate +
                ", balanceAtYearEnd=" + balanceAtYearEnd +
                '}';
    }
}
