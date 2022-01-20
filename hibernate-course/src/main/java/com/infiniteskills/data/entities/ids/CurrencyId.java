package com.infiniteskills.data.entities.ids;

import java.io.Serializable;

public class CurrencyId implements Serializable {
    //The fields must match with the column name in the table
    private String name;
    private String countryName;

    public CurrencyId() {
    }

    public CurrencyId(String name, String countryName) {
        this.name = name;
        this.countryName = countryName;
    }

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

    @Override
    public String toString() {
        return "CurrencyId{" +
                "name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
