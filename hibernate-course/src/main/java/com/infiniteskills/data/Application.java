package com.infiniteskills.data;

import com.infiniteskills.data.entities.*;
import com.infiniteskills.data.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("infinite-finances");
        EntityManager em = factory.createEntityManager(); // Is like the Session (Hibernate)
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Bank bank = em.find(Bank.class,1L);
        em.detach(bank); // Detach only a spec entity || em.clear(); //All entities in the context will be detached
        System.out.println(em.contains(bank));
        bank.setName("Something else");

        Bank bank2 = em.merge(bank);
        bank.setName("Something else 2"); //Entity Detached

        transaction.commit();
        em.close();
        factory.close();
    }

    private static Date getMyBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1999);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DATE, 20);
        return calendar.getTime();
    }

    private static User createDummyUser() {
        User user = new User();
        Address address = new Address();
        address.setAddressLine1("Line 1");
        address.setAddressLine2("Line 2");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("12345");
        Address address2 = new Address();
        address2.setAddressLine1("Line 3");
        address2.setAddressLine2("Line 4");
        address2.setCity("New York");
        address2.setState("NY");
        address2.setZipCode("12345");
        user.getAddress().add(address);
        user.getAddress().add(address2);
        user.setBirthDate(new Date());
        user.setCreatedDate(new Date());
        user.setCreatedBy("Juls");
        user.setEmailAddress("julio");
        user.setFirstName("Diana");
        user.setLastName("Mejia");
        user.setLastUpdateDate(new Date());
        user.setLastUpdateBy("Juls");
        return user;
    }

    private static Account createNewAccount() {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Kevin Bowersox");
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName("Savings Account");
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Kevin Bowersox");
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }

    private static Transaction createNewBeltPurchase(Account account) {
        Transaction beltPurchase = new Transaction();
        beltPurchase.setAccount(account);
        beltPurchase.setTitle("Dress Belt");
        beltPurchase.setAmount(new BigDecimal("50.00"));
        beltPurchase.setClosingBalance(new BigDecimal("0.00"));
        beltPurchase.setCreatedBy("Kevin Bowersox");
        beltPurchase.setCreatedDate(new Date());
        beltPurchase.setInitialBalance(new BigDecimal("0.00"));
        beltPurchase.setLastUpdatedBy("Kevin Bowersox");
        beltPurchase.setLastUpdatedDate(new Date());
        beltPurchase.setNotes("New Dress Belt");
        beltPurchase.setTransactionType("Debit");
        return beltPurchase;
    }

    private static Transaction createShoePurchase(Account account) {
        Transaction shoePurchase = new Transaction();
        shoePurchase.setAccount(account);
        shoePurchase.setTitle("Work Shoes");
        shoePurchase.setAmount(new BigDecimal("100.00"));
        shoePurchase.setClosingBalance(new BigDecimal("0.00"));
        shoePurchase.setCreatedBy("Kevin Bowersox");
        shoePurchase.setCreatedDate(new Date());
        shoePurchase.setInitialBalance(new BigDecimal("0.00"));
        shoePurchase.setLastUpdatedBy("Kevin Bowersox");
        shoePurchase.setLastUpdatedDate(new Date());
        shoePurchase.setNotes("Nice Pair of Shoes");
        shoePurchase.setTransactionType("Debit");
        return shoePurchase;
    }

    private static Bank createBank() {
        Bank bank = new Bank();
        bank.setName("First United Federal");
        bank.setAddressLine1("103 Washington Plaza");
        bank.setAddressLine2("Suite 332");
        bank.setAddressType("PRIMARY");
        bank.setCity("New York");
        bank.setCreatedBy("Kevin Bowersox");
        bank.setCreatedDate(new Date());
        bank.setInternational(false);
        bank.setLastUpdatedBy("Kevin Bowersox");
        bank.setLastUpdatedDate(new Date());
        bank.setState("NY");
        bank.setZipCode("10000");
        return bank;
    }

    private static void randomMethod() throws Exception {
        if (Math.random() < 0.3) {
            System.out.println("There is any problem");
        }else{
            System.out.println("There is a problem");
            throw new Exception("Rolling back");
        }
    }


}
