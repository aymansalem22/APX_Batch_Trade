package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;


public class VirtualCurrency {


    private String currencyType;

    private String currencyName;

    private String currencySymbol;

    private Integer numberOfUnits;

    private Double currencyValue;

    private String valueSource;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date custodyEndDate;

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
