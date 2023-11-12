package com.lip6.services;

import com.lip6.entities.Address;

public interface IAddressService {
    Address getAddressById(long id);
    void createAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(long id);
}
