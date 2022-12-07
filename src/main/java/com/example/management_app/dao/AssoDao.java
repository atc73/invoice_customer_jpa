package com.example.management_app.dao;

import com.example.management_app.connection.PersistenceManager;
import com.example.management_app.model.Customer;
import com.example.management_app.model.InvoiceProductAssociation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class AssoDao {
    EntityManagerFactory emf = PersistenceManager.getEntityManager();

    public void save(InvoiceProductAssociation ipa) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(ipa);
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
