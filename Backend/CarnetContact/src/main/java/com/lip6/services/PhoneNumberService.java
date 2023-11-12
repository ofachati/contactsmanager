package com.lip6.services;

import com.lip6.daos.IDAOPhoneNumber;
import com.lip6.entities.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService implements IPhoneNumberService {
    private final IDAOPhoneNumber daoPhoneNumber;

    @Autowired
    public PhoneNumberService(IDAOPhoneNumber daoPhoneNumber) {
        this.daoPhoneNumber = daoPhoneNumber;
    }

    @Override
    public PhoneNumber getPhoneNumberById(long id) {
        return daoPhoneNumber.getPhoneNumberById(id);
    }

    @Override
    public void createPhoneNumber(PhoneNumber phoneNumber) {
        daoPhoneNumber.addPhoneNumber(phoneNumber);
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        daoPhoneNumber.updatePhoneNumber(phoneNumber);
    }

    @Override
    public void deletePhoneNumber(long id) {
        daoPhoneNumber.deletePhoneNumber(id);
    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {
        return daoPhoneNumber.getAllPhoneNumbers();
    }
}
