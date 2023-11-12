package com.lip6.daos;

import com.lip6.entities.PhoneNumber;
import java.util.List;

public interface IDAOPhoneNumber {
    PhoneNumber getPhoneNumberById(long id);
    void addPhoneNumber(PhoneNumber phoneNumber);
    void updatePhoneNumber(PhoneNumber phoneNumber);
    void deletePhoneNumber(long id);
    List<PhoneNumber> getAllPhoneNumbers();
}
