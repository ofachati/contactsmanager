package com.lip6.services;

import com.lip6.daos.IDAOAddress;
import com.lip6.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    private final IDAOAddress daoAddress;

    @Autowired
    public AddressService(IDAOAddress daoAddress) {
        this.daoAddress = daoAddress;
    }

    @Override
    public Address getAddressById(long id) {
        return daoAddress.getAddressById(id);
    }

    @Override
    public void createAddress(Address address) {
        daoAddress.addAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        daoAddress.updateAddress(address);
    }

    @Override
    public void deleteAddress(long id) {
        daoAddress.deleteAddress(id);
    }
}
