package com.infiniteskills.data;

import com.infiniteskills.data.entities.Account;
import org.hibernate.Session;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class JpqlApplication {
    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            namedQueryExample(em);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            em.close();
            factory.close();
        }
    }

    private static void functionExample(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT t.account.name, " +
                "concat(concat(t.account.bank.name,' '), t.account.bank.address.state) " +
                "FROM Transaction t " +
                "WHERE t.amount > :amount AND t.transactionType = 'withdrawl'");
        query.setParameter("amount",new BigDecimal("99"));
        List<Object[]> accounts = query.getResultList();
        for (Object[] a:accounts) {
            System.out.println(a[0]);
            System.out.println(a[1]);
        }
    }
    private static void namedQueryExample(EntityManager em){
        Query query = em.createNamedQuery("Account.byWithdrawlAmount");
        query.setParameter("amount",new BigDecimal("99"));
        List<Object[]> accounts = query.getResultList();
        for (Object[] a:accounts) {
            System.out.println(a[0]);
            System.out.println(a[1]);
        }
    }
}
