package com.infiniteskills.data;

import com.infiniteskills.data.entities.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.List;

public class HibernateApplication {
    public static void main(String[] args) {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction transaction = null;



        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();

            selectionExample(session);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }

    private static void selectionExample(Session session){
        Criteria criteria = session.createCriteria(Transaction.class);

        criteria.addOrder(Order.asc("title"));

        List<Transaction> transactions = criteria.list();

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }
    private static void restrinctionExample(Session session){
        Criterion criterion = Restrictions.le("amount",new BigDecimal("20.00"));
        Criterion criterion1 = Restrictions.eq("transactionType","Withdrawl");

        Criteria criteria = session.createCriteria(Transaction.class);

        criteria.add(Restrictions.and(criterion,criterion1)).addOrder(Order.desc("title"));

        List<Transaction> transactions = criteria.list();

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }

    }
    private static void pagingExample(Session  session){
        int pageNumber = 2;
        int pageSize = 4;

        Criteria criteria = session.createCriteria(Transaction.class)
                .addOrder(Order.desc("title"));
        criteria.setFirstResult((pageNumber-1)*pageSize);
        criteria.setMaxResults(pageSize);
        List<Transaction> transactions = criteria.list();

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }
}
