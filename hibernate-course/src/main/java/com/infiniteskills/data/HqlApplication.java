package com.infiniteskills.data;

import com.infiniteskills.data.entities.Account;
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
            lazyLoadingExample(session);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
            factory.close();
        }
    }
    private static void functionExample(Session session){
        Query query = session.createQuery("SELECT distinct t.account " +
                "FROM Transaction t" +
                " WHERE t.amount > 500 and lower(t.transactionType) = 'deposit'");
        List<Account> accounts = query.list();

        for (Account account:accounts) {
            System.out.println(account);
        }
    }
    private static  void namedQueryExample(Session session){
        Query query = session.getNamedQuery("Account.largeDeposits");

        List<Account> accounts = query.list();

        for (Account account:accounts) {
            System.out.println(account);
        }
    }
    private static void lazyLoadingExample(Session session){
        Query query = session.getNamedQuery("Account.largeDeposits");
        List<Account> accounts = query.list();

        for (Account account:accounts) {
            System.out.println(account);
            System.out.println(account.getBank().getName());
        }
    }
}
