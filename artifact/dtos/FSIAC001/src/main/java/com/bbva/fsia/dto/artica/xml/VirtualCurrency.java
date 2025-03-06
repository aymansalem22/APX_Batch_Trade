package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("dec1:MonedaVirtual")
public class VirtualCurrency {

    @XStreamAlias("dec1:TipoMonedaVirtual")
    private TypeCurrency typeCurrency;

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


    public TypeCurrency getTypeCurrency() {
        return typeCurrency;
    }

    public void setTypeCurrency(TypeCurrency typeCurrency) {
        this.typeCurrency = typeCurrency;
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

    public Date getCustodyEndDate() {
        return custodyEndDate;
    }

    public void setCustodyEndDate(Date custodyEndDate) {
        this.custodyEndDate = custodyEndDate;
    }

    public Double getBalanceAtYearEnd() {
        return balanceAtYearEnd;
    }

    public void setBalanceAtYearEnd(Double balanceAtYearEnd) {
        this.balanceAtYearEnd = balanceAtYearEnd;
    }

    @Override
    public String toString() {
        return "VirtualCurrency{" +
                "typeCurrency=" + typeCurrency +
                ", numberOfUnits=" + numberOfUnits +
                ", currencyValue=" + currencyValue +
                ", valueSource='" + valueSource + '\'' +
                ", custodyEndDate=" + custodyEndDate +
                ", balanceAtYearEnd=" + balanceAtYearEnd +
                '}';
    }
}
