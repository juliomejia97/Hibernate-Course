package com.infiniteskills.data;

import com.infiniteskills.data.entities.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HqlApplication {
    public static void main(String[] args) {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction transaction = null;
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT t from Transaction t " +
                    "WHERE t.amount > 75 AND t.transactionType = 'Withdrawl' ");
            List<Transaction> transactions = query.list();
            for (Transaction t:transactions) {
                System.out.println(t.getTitle());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            factory.close();
        }
    }
}
