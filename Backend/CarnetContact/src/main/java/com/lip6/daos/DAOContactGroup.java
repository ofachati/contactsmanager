package com.lip6.daos;

import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class DAOContactGroup implements IDAOContactGroup {
    @Override
    public ContactGroup getContactGroupById(long id) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        return em.find(ContactGroup.class, id);
    }

    @Override
    public void addContactGroup(ContactGroup contactGroup) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            if (contactGroup.getContacts() == null) {
                contactGroup.setContacts(new HashSet<>());
            }

            Set<Contact> attachedContacts = new HashSet<>();
            for (Contact contact : contactGroup.getContacts()) {
                if (contact.getIdContact() != 0) {
                    attachedContacts.add(em.find(Contact.class, contact.getIdContact()));
                }
            }
            contactGroup.setContacts(attachedContacts);

            em.persist(contactGroup);

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
    public void updateContactGroup(ContactGroup contactGroup) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(contactGroup);
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
    public void deleteContactGroup(long id) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            ContactGroup contactGroup = em.find(ContactGroup.class, id);
            if (contactGroup != null) {
                em.remove(contactGroup);
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
    public List<ContactGroup> getAllContactGroups() {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ContactGroup> cq = cb.createQuery(ContactGroup.class);
            Root<ContactGroup> root = cq.from(ContactGroup.class);
            cq.select(root);

            TypedQuery<ContactGroup> query = em.createQuery(cq);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
