package com.lip6.daos;

import com.lip6.entities.ContactGroup;

import java.util.List;

public interface IDAOContactGroup {
    ContactGroup getContactGroupById(long id);
    void addContactGroup(ContactGroup contactGroup);
    void updateContactGroup(ContactGroup contactGroup);
    void deleteContactGroup(long id);
    List<ContactGroup> getAllContactGroups();
}
