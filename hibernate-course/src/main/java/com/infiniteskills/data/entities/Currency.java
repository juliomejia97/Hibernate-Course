package com.infiniteskills.data.entities;

import com.infiniteskills.data.entities.ids.CurrencyId;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@IdClass(CurrencyId.class)
public class Currency {

    @Id
    @Column(name = "NAME")
    private String name;

    @Id
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "SYMBOL")
    private String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
