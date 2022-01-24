package com.infiniteskills.data;

import com.infiniteskills.data.entities.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class JpaApplication {
    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            selectionExample(em);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
            factory.close();
        }
    }

    private static void selectionExample(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(root).orderBy(cb.asc(root.get("title")));

        TypedQuery<Transaction> query = em.createQuery(criteriaQuery);

        List<Transaction> transactions = query.getResultList();

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }
    private static void restrinctionsExample(EntityManager em){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        Path<BigDecimal> amountPath = root.get("amount");
        Path<String> transactionTypePath = root.get("transactionType");

        criteriaQuery.select(root)
                .where(cb.and(
                        cb.le(amountPath, new BigDecimal("20.00")),
                        cb.equal(transactionTypePath, "Withdrawl")));

        TypedQuery<Transaction> query = em.createQuery(criteriaQuery);

        List<Transaction> transactions = query.getResultList();

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }

    private static void pagingExample(EntityManager em){
        int pageNumber = 2;
        int pageSize = 4;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(root);

        TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);

        List<Transaction> transactions = query.getResultList();

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }

    }
}
