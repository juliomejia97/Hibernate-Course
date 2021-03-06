package com.infiniteskills.data.entities;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="bank")
public class Bank {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="BANK_ID")
    private Long bankId;

    @Column(name="NAME")
    private String name;

    @Embedded
    private Address address = new Address();

    @Column(name="IS_INTERNATIONAL")
    private boolean international;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "ADDRESS_TYPE", nullable = true)
    private String addressType;

    @ElementCollection
    @CollectionTable(name = "bank_contact", joinColumns = @JoinColumn(name = "BANK_ID"))
    @MapKeyColumn(name = "POSITION_TYPE")
    @Column(name = "NAME")
    private Map<String,String> contacts = new HashMap<>();
    public Bank(){}

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return address.getAddressLine1();
    }

    public void setAddressLine1(String addressLine1) {
        this.address.setAddressLine1(addressLine1);
    }

    public String getAddressLine2() {
        return address.getAddressLine2();
    }

    public void setAddressLine2(String addressLine2) {
        this.address.setAddressLine2(addressLine2);
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }

    public String getState() {
        return address.getState();
    }

    public void setState(String state) {
        this.address.setState(state);
    }

    public String getZipCode() {
        return address.getZipCode();
    }

    public void setZipCode(String zipCode) {
        this.address.setZipCode(zipCode);
    }


    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Map<String,String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String,String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", international=" + international +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
