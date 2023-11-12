package com.lip6.daos;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;
import com.lip6.util.JpaUtil;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAOContact implements IDAOContact{
	
	
	@Override
    public boolean addContact(String firstname, String lastname, String email) {
        boolean success = false;
        try {
            EntityManager em = JpaUtil.getEmf().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Contact contact = new Contact(lastname, firstname, email);
            em.persist(contact);
            tx.commit();
            em.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
	
	
	@Override
	public boolean addContact(Contact contact) {
	    boolean success = false;
	    try {
	        EntityManager em = JpaUtil.getEmf().createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        // Mergee detached entity to make it manaaaaged
	        Contact managedContact = em.merge(contact);

	        // Initialize the phones       if  null
	        if (managedContact.getPhones() == null) {  managedContact.setPhones(new HashSet<>()); }
	        // Set the contact for each phone number
	        for (PhoneNumber phoneNumber : managedContact.getPhones()) {
	            phoneNumber.setContact(managedContact);
	            em.persist(phoneNumber);
	        }

	        // Set the contact for the address
	        if (managedContact.getAddress() != null) {
	            managedContact.getAddress().setContact(managedContact);
	            em.persist(managedContact.getAddress()); // Persist the address
	        }

	        em.persist(managedContact); // Persist the new contact

	        tx.commit();
	        em.close();
	        success = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return success;
	}

    
    @Override
    public List<Contact> getAllContacts() {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
            Root<Contact> root = cq.from(Contact.class);
            cq.select(root);

            TypedQuery<Contact> query = em.createQuery(cq);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    @Override
    public boolean updateContact(Contact contact) {
        boolean success = false;
        try {
            EntityManager em = JpaUtil.getEmf().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Merge the detached entity to make it managed
            Contact managedContact = em.merge(contact);

            // Set the contact for each phone number
            for (PhoneNumber phoneNumber : managedContact.getPhones()) {
                phoneNumber.setContact(managedContact);
                em.persist(phoneNumber); // Persist the phone number
            }

            // Set the contact for the address
            if (managedContact.getAddress() != null) {
                managedContact.getAddress().setContact(managedContact);
                em.persist(managedContact.getAddress()); // Persist the address
            }

            // Merge the updated contact
            em.merge(managedContact);

            tx.commit();
            em.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    @Override
    public boolean deleteContact(long id) {
        boolean success = false;
        try {
            EntityManager em = JpaUtil.getEmf().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Contact contact = em.find(Contact.class, id);
            if (contact != null) {
                em.remove(contact);
                success = true;
            }
            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    @Override
    public Contact getContactById(long id) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        try {
            return em.find(Contact.class, id);
        } finally {
            em.close();
        }
    }

    
    
    
    //query JPQL  exmpls
    public List<Contact> findContactsWithMostPhoneNumbers() {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        return em.createQuery("SELECT c FROM Contact c LEFT JOIN FETCH c.phones p GROUP BY c ORDER BY COUNT(p) DESC", Contact.class)
                 .setMaxResults(1) // Limit the result to one record with the most phone numbers
                 .getResultList();
    }


    public List<Contact> findContactsByCity(String city) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        return em.createQuery("SELECT c FROM Contact c WHERE c.address.city = :city", Contact.class)
                 .setParameter("city", city)
                 .getResultList();
    }

    
    
    
}
