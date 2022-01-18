package com.infiniteskills.data.entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.*;

@Entity
@Table (name="finances_user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long user_id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @OneToOne(mappedBy = "user")
    private Credential credential;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "users")
    private Set<Account> accounts = new HashSet<>();
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE",nullable = false)
    private Date birthDate;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @ElementCollection
    @CollectionTable(name = "user_address",joinColumns = @JoinColumn(name = "USER_ID"))
    @AttributeOverrides({@AttributeOverride(name = "addressLine1",column = @Column(name = "USER_ADDRESS_LINE_1")),
                        @AttributeOverride(name="addressLine2",column = @Column(name = "USER_ADDRESS_LINE_2"))})
    private List<Address> address = new ArrayList<>();

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdateBy;
    @Column(name = "CREATED_DATE",updatable = false)
    private Date createdDate;
    @Column(name = "CREATED_BY",updatable = false)
    private String createdBy;
    @Transient
    private boolean valid;
    @Formula("lower(datediff(curdate(),birth_date)/365)")
    private int age;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
