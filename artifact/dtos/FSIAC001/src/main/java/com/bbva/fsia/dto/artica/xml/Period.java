package com.bbva.fsia.dto.artica.xml;


import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("dec1:Periodo")
public class Period {

    @XStreamAlias("dec1:Ejercicio")
    private String fiscalYear;

    @XStreamAlias("dec1:Periodo")
    private String period;

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Period{" +
                "fiscalYear='" + fiscalYear + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
