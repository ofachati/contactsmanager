package com.lip6.daos;

import com.lip6.entities.Address;

public interface IDAOAddress {
    Address getAddressById(long id);
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(long id);
}
