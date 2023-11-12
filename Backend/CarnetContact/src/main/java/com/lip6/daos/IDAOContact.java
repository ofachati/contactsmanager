package com.lip6.daos;

import java.util.List;

import com.lip6.entities.Contact;

public interface IDAOContact {

	
	public boolean addContact(String lastname, String firstname, String email);
	public boolean addContact(Contact contact);
	boolean updateContact(Contact contact);
    boolean deleteContact(long id);
    Contact getContactById(long id);
    List<Contact> getAllContacts();
	
}
