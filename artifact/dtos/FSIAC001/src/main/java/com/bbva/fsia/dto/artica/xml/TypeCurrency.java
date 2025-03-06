package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dec1:MonedaVirtual")
public class TypeCurrency {

    @XStreamAlias("dec1:DenominacionMonedaVirtual")
    private String currencyName;

    @XStreamAlias("dec1:SiglasMonedaVirtual")
    private String currencySymbol;


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

    @Override
    public String toString() {
        return "TypeCurrency{" +
                "currencyName='" + currencyName + '\'' +
                ", currencySymbol='" + currencySymbol + '\'' +
                '}';
    }
}
