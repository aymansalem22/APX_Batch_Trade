package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;


@XStreamAlias("dec1:IDMonedas")
public class IDMonedas {

    @XStreamAlias("dec1:TipoMoneda")
    private String currencyType;

    @XStreamAlias("dec1:MonedaVirtual")
    private VirtualCurrency virtualCurrency;

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public VirtualCurrency getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(VirtualCurrency virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    @Override
    public String toString() {
        return "IDMonedas{" +
                "currencyType='" + currencyType + '\'' +
                ", virtualCurrency=" + virtualCurrency +
                '}';
    }
}
