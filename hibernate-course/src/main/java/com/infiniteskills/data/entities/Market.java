package com.infiniteskills.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "market")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MARKET_ID",nullable = false)
    private Long marketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "CURRENCY_NAME",referencedColumnName = "NAME"),
            @JoinColumn(name = "COUNTRY_NAME",referencedColumnName = "COUNTRY_NAME")
    })
    private Currency currency;

    @Column(name = "MARKET_NAME",nullable = true)
    private String marketName;

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String toString() {
        return "Market{" +
                "marketId=" + marketId +
                ", currency=" + currency.toString() +
                ", marketName='" + marketName + '\'' +
                '}';
    }
}
