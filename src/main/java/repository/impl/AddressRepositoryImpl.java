package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Address;
import repository.AddressRepository;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class AddressRepositoryImpl extends BaseRepositoryImpl<Address, Long>
        implements AddressRepository {

    public AddressRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Address> getClassType() {
        return Address.class;
    }

    @Override
    public Address createAddress() {

        Scanner stringInput = new Scanner(System.in);

        Address address = new Address();

        System.out.println("enter the requested address details:");

        System.out.print("province: ");
        address.setProvince(stringInput.nextLine());

        System.out.print("city: ");
        address.setCity(stringInput.nextLine());

        System.out.print("street name: ");
        address.setStreetName(stringInput.nextLine());

        System.out.print("postal code: ");
        address.setPostalCode(stringInput.nextLine());

        return address = save(address);

    }
}
