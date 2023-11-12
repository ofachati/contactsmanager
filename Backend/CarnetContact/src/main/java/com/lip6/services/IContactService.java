package com.lip6.services;

import java.util.List;

import com.lip6.entities.Contact;

public interface IContactService {
    void createContact(String fname, String lname, String email);
    boolean createContact(Contact contact);
    boolean updateContact(Contact contact);
    boolean deleteContact(long id);
    Contact getContactById(long id);
    List<Contact> getAllContacts();
}
