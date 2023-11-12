package com.lip6.daos;

import com.lip6.entities.PhoneNumber;
import com.lip6.util.JpaUtil;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DAOPhoneNumber implements IDAOPhoneNumber {
    @Override
    public PhoneNumber getPhoneNumberById(long id) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        return em.find(PhoneNumber.class, id);
    }

    @Override
    public void addPhoneNumber(PhoneNumber phoneNumber) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(phoneNumber);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(phoneNumber);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletePhoneNumber(long id) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            PhoneNumber phoneNumber = em.find(PhoneNumber.class, id);
            if (phoneNumber != null) {
                em.remove(phoneNumber);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<PhoneNumber> cq = cb.createQuery(PhoneNumber.class);
            Root<PhoneNumber> root = cq.from(PhoneNumber.class);
            cq.select(root);

            TypedQuery<PhoneNumber> query = em.createQuery(cq);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
