package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import domain.Customer;
import repository.AccountRepository;
import repository.AddressRepository;
import repository.CustomerRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long>
        implements CustomerRepository {

    private AddressRepository addressRepository;

    private AccountRepository accountRepository;

    public CustomerRepositoryImpl(EntityManager em, AddressRepository addressRepository, AccountRepository accountRepository) {
        super(em);

        this.addressRepository = addressRepository;

        this.accountRepository = accountRepository;

    }

    @Override
    public Class<Customer> getClassType() {
        return Customer.class;
    }

    @Override
    public Customer createCustomer() {

        Scanner stringInput = new Scanner(System.in);

        Scanner intInput = new Scanner(System.in);

        Customer newCustomer = new Customer();

        System.out.print("enter first_name: ");
        newCustomer.setFirstName(stringInput.nextLine());

        System.out.print("enter last_name: ");
        newCustomer.setLastName(stringInput.nextLine());

        System.out.print("enter social_security_number: ");
        newCustomer.setSsn(stringInput.nextLine());

        System.out.print("enter date_of_birth in order of (year , month , day) with a" +
                " space between each number : ");
        newCustomer.setDateOfBirth(new Date(intInput.nextInt(), intInput.nextInt() - 1, intInput.nextInt()));

        System.out.print("enter phone_number: ");
        newCustomer.setPhoneNumber(stringInput.nextLine());

        System.out.println();

        System.out.println("define Address: ");
        newCustomer.setAddress(addressRepository.createAddress());

        System.out.println();

        System.out.println("setting up your first account");

        ArrayList<Account> accounts = new ArrayList<>();

        accounts.add(accountRepository.setUpAccount(newCustomer));

        newCustomer.setAccounts(accounts);

        return null;
    }

    @Override
    public Customer findBySsn(String Ssn) {
        return em.createQuery("from Customer where Customer.Ssn = :Ssn", Customer.class)
                .setParameter("Ssn", Ssn).getSingleResult();
    }


}
