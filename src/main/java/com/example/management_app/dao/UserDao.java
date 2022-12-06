package com.example.management_app.dao;

import com.example.management_app.connection.PersistenceManager;
import com.example.management_app.model.Invoice;
import com.example.management_app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class UserDao {

    EntityManagerFactory emf = PersistenceManager.getEntityManager();

    public Optional<User> findByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            User user = em.createQuery("SELECT b FROM User b  WHERE b.username = :usernameParam", User.class)
                    .setParameter("usernameParam", username)
                    .getSingleResult();
            et.commit();
            return Optional.of(user);
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return Optional.empty();
    }
}

