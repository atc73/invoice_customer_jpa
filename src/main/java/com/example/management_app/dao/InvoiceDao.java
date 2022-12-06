package com.example.management_app.dao;

import com.example.management_app.connection.PersistenceManager;
import com.example.management_app.model.Customer;
import com.example.management_app.model.Invoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InvoiceDao implements Dao<Invoice>{

    EntityManagerFactory emf = PersistenceManager.getEntityManager();

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoiceList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            TypedQuery<Invoice> query = em.createQuery("SELECT b FROM Invoice b", Invoice.class);
            invoiceList = query.getResultList();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return invoiceList;
    }

    @Override
    public void save(Invoice invoice) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(invoice);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Invoice invoice = em.createQuery("SELECT b FROM Invoice b  WHERE b.id = :idParam", Invoice.class)
                    .setParameter("idParam", id)
                    .getSingleResult();
            et.commit();
            return Optional.of(invoice);
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return Optional.empty();
    }


    @Override
    public void update(Invoice invoiceUpdate) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Long id = invoiceUpdate.getInvoiceId();

        try {
            et.begin();
            Invoice invoice = em.find(Invoice.class, id);

            if (Objects.equals(invoice.getInvoiceId(), invoiceUpdate.getInvoiceId())) {
                invoice.setInvoiceDate(invoiceUpdate.getInvoiceDate());
                invoice.setTotal(invoiceUpdate.getTotal());
                invoice.setCustomer(invoiceUpdate.getCustomer());
                invoice.setAssociationList(invoiceUpdate.getAssociationList());
            }
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }

    }


    @Override
    public void delete(Invoice invoiceToDelete) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Invoice invoice = em.find(Invoice.class, invoiceToDelete.getInvoiceId());
            em.remove(invoice);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }
}
