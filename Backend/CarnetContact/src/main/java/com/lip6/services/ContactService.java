package com.lip6.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lip6.daos.DAOContact;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.PhoneNumber;

public class ContactService implements IContactService {

    private final IDAOContact daoContact;

    //@Autowired
    public ContactService(IDAOContact daoContact) {
        this.daoContact = daoContact;
    }

    @Override
    public void createContact(String fname, String lname, String email) {
    	daoContact.addContact( fname,  lname,  email);
        System.out.println("Contact added successfully");
    }

    @Override
    public boolean createContact(Contact contact) {
        return daoContact.addContact(contact);
    }

    @Override
    public boolean updateContact(Contact contact) {
        return daoContact.updateContact(contact);
    }

    @Override
    public boolean deleteContact(long id) {
        return daoContact.deleteContact(id);
    }

    @Override
    public Contact getContactById(long id) {
        return daoContact.getContactById(id);
    }
    
    @Override
    public List<Contact> getAllContacts() {
        return daoContact.getAllContacts();
    }
    
    
    
    

}
