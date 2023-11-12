package com.lip6.services;

import com.lip6.entities.PhoneNumber;
import java.util.List;

public interface IPhoneNumberService {
    PhoneNumber getPhoneNumberById(long id);
    void createPhoneNumber(PhoneNumber phoneNumber);
    void updatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(long id);
    List<PhoneNumber> getAllPhoneNumbers();
}
