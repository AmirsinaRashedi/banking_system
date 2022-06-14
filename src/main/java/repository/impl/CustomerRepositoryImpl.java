package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import domain.Customer;
import repository.AccountRepository;
import repository.AddressRepository;
import repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long>
        implements CustomerRepository {

    private AddressRepository addressRepository;

    private AccountRepository accountRepository;

    public CustomerRepositoryImpl(EntityManager em, AddressRepository addressRepository) {
        super(em);

        this.addressRepository = addressRepository;
    }

    public CustomerRepositoryImpl(EntityManager em, AddressRepository addressRepository, AccountRepository accountRepository) {
        super(em);

        this.addressRepository = addressRepository;

        this.accountRepository = accountRepository;

    }

    public void setAccountRepository(AccountRepository accountRepository) {
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

//        System.out.print("enter date_of_birth in order of (year , month , day) with a" +
//                " space between each number : ");
//        newCustomer.setDateOfBirth(new Date(intInput.nextInt(), intInput.nextInt() - 1, intInput.nextInt()));

        System.out.print("enter phone_number: ");
        newCustomer.setPhoneNumber(stringInput.nextLine());

        System.out.println();

        System.out.println("define Address: ");
        newCustomer.setAddress(addressRepository.createAddress());

        newCustomer = save(newCustomer);

        System.out.println();

        System.out.println("setting up your first account");

        ArrayList<Account> accounts = new ArrayList<>();

        accounts.add(accountRepository.setUpAccount(newCustomer));

        newCustomer.setAccounts(accounts);

        return newCustomer;
    }

    @Override
    public Customer findBySsn(String Ssn) {

        try {
            return em.createQuery("from Customer C where C.Ssn = :Ssn", Customer.class)
                    .setParameter("Ssn", Ssn).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }


}
