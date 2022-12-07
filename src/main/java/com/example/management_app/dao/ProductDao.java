package com.example.management_app.dao;

import com.example.management_app.connection.PersistenceManager;
import com.example.management_app.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductDao implements Dao<Product> {

    EntityManagerFactory emf = PersistenceManager.getEntityManager();

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            TypedQuery<Product> query = em.createQuery("SELECT b FROM Product b", Product.class);
            productList = query.getResultList();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return productList;
    }

    @Override
    public void save(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(product);
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
    public Optional<Product> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Product product = em.createQuery("SELECT b FROM Product b  WHERE b.id = :idParam", Product.class)
                    .setParameter("idParam", id)
                    .getSingleResult();
            et.commit();
            return Optional.of(product);
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
    public void update(Product productUpdate) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Long id = productUpdate.getProductId();

        try {
            et.begin();
            Product product = em.find(Product.class, id);

            if (Objects.equals(product.getProductId(), productUpdate.getProductId())) {
                product.setDescription(productUpdate.getDescription());
                product.setPrice(productUpdate.getPrice());
                product.setVat(productUpdate.getVat());
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
    public void delete(Product productToDelete) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Product product = em.find(Product.class, productToDelete.getProductId());
            em.remove(product);
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
