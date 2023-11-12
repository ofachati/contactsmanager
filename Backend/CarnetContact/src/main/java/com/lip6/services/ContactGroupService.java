package com.lip6.services;

import com.lip6.daos.IDAOContactGroup;
import com.lip6.entities.ContactGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactGroupService implements IContactGroupService {
    private final IDAOContactGroup daoContactGroup;

    @Autowired
    public ContactGroupService(IDAOContactGroup daoContactGroup) {
        this.daoContactGroup = daoContactGroup;
    }

    @Override
    public ContactGroup getContactGroupById(long id) {
        return daoContactGroup.getContactGroupById(id);
    }

    @Override
    public void createContactGroup(ContactGroup contactGroup) {
        daoContactGroup.addContactGroup(contactGroup);
    }

    @Override
    public void updateContactGroup(ContactGroup contactGroup) {
        daoContactGroup.updateContactGroup(contactGroup);
    }

    @Override
    public void deleteContactGroup(long id) {
        daoContactGroup.deleteContactGroup(id);
    }

    @Override
    public List<ContactGroup> getAllContactGroups() {
        return daoContactGroup.getAllContactGroups();
    }
}
