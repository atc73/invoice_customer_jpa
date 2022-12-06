package com.example.management_app.dao;

import com.example.management_app.connection.PersistenceManager;
import com.example.management_app.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerDao implements Dao<Customer>{


    EntityManagerFactory emf = PersistenceManager.getEntityManager();

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            TypedQuery<Customer> query = em.createQuery("SELECT b FROM Customer b", Customer.class);
            customerList = query.getResultList();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return customerList;
    }



    @Override
    public void save(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(customer);
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
    public Optional<Customer> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        System.out.println("Je suis dans le findById");
        System.out.println(id);
        try {
            et.begin();
            Customer customer = em.createQuery("SELECT b FROM Customer b  WHERE b.id = :idParam", Customer.class)
                    .setParameter("idParam", id)
                    .getSingleResult();
            et.commit();
            return Optional.of(customer);
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
    public void update(Customer customerUpdate) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Long id = customerUpdate.getCustomerId();

        try {
            et.begin();
            Customer customer = em.find(Customer.class, id);

            if (Objects.equals(customer.getCustomerId(), customerUpdate.getCustomerId())) {
                customer.setName(customerUpdate.getName());
                customer.setAddress(customerUpdate.getAddress());
                customer.setPostcode(customerUpdate.getPostcode());
                customer.setCity(customerUpdate.getCity());
                customer.setPhoneNumber(customerUpdate.getPhoneNumber());
                customer.setEmail(customerUpdate.getEmail());
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
    public void delete(Customer customerToDelete) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Customer customer = em.find(Customer.class, customerToDelete.getCustomerId());
            em.remove(customer);
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
