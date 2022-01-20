package com.infiniteskills.data;

import com.infiniteskills.data.entities.Transaction;

import javax.persistence.*;
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

            TypedQuery<Transaction> query = em.createQuery("from Transaction t " +
                    "WHERE  (t.amount between 75 and 100)"+
                    "AND t.title like '%s'"+
                    "order by t.title",Transaction.class);
            List<Transaction> transactions = query.getResultList();
            for (Transaction t:transactions) {
                System.out.println(t.getTitle());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            em.close();
            factory.close();
        }
    }
}
