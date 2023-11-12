package com.lip6.services;

import com.lip6.entities.ContactGroup;

import java.util.List;

public interface IContactGroupService {
    ContactGroup getContactGroupById(long id);
    void createContactGroup(ContactGroup contactGroup);
    void updateContactGroup(ContactGroup contactGroup);
    void deleteContactGroup(long id);
    List<ContactGroup> getAllContactGroups();
}
