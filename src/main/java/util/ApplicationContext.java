package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class ApplicationContext {

    private static AccountService accountService;
    private static BranchService branchService;
    private static CardService cardService;
    private static CustomerService customerService;
    private static EmployeeService employeeService;
    private static TransactionService transactionService;
    private static AccountRepository accountRepository;
    private static AddressRepository addressRepository;
    private static BranchRepository branchRepository;
    private static CardRepository cardRepository;
    private static CustomerRepository customerRepository;
    private static EmployeeRepository employeeRepository;
    private static TransactionRepository transactionRepository;

    private ApplicationContext() {
    }

    public static AccountService getAccountService() {

        if (accountService == null)
            accountService = new AccountServiceImpl(getAccountRepository());

        return accountService;
    }

    public static BranchService getBranchService() {

        if (branchService == null)
            branchService = new BranchServiceImpl(getBranchRepository());

        return branchService;
    }

    public static CardService getCardService() {

        if (cardService == null)
            cardService = new CardServiceImpl(getCardRepository());

        return cardService;
    }

    public static CustomerService getCustomerService() {

        if (customerService == null)
            customerService = new CustomerServiceImpl(getCustomerRepository());

        return customerService;
    }

    public static EmployeeService getEmployeeService() {

        if (employeeService == null)
            employeeService = new EmployeeServiceImpl(getEmployeeRepository(), getBranchRepository());

        return employeeService;
    }

    public static TransactionService getTransactionService() {

        if (transactionService == null)
            transactionService = new TransactionServiceImpl(getTransactionRepository());

        return transactionService;
    }

    public static AccountRepository getAccountRepository() {

        if (accountRepository == null)
            accountRepository = new AccountRepositoryImpl(HibernateUtil.getEntityManager(), getBranchRepository(), getCardRepository(), getCustomerRepository());

        return accountRepository;
    }

    public static AddressRepository getAddressRepository() {

        if (addressRepository == null)
            addressRepository = new AddressRepositoryImpl(HibernateUtil.getEntityManager());

        return addressRepository;
    }

    public static BranchRepository getBranchRepository() {

        if (branchRepository == null)
            branchRepository = new BranchRepositoryImpl(HibernateUtil.getEntityManager(), getAddressRepository(), getEmployeeRepository());

        return branchRepository;
    }

    public static CardRepository getCardRepository() {

        if (cardRepository == null)
            cardRepository = new CardRepositoryImpl(HibernateUtil.getEntityManager());

        return cardRepository;
    }

    public static CustomerRepository getCustomerRepository() {

        if (customerRepository == null)
            customerRepository = new CustomerRepositoryImpl(HibernateUtil.getEntityManager(), getAddressRepository(), getAccountRepository());

        return customerRepository;
    }

    public static EmployeeRepository getEmployeeRepository() {

        if (employeeRepository == null)
            employeeRepository = new EmployeeRepositoryImpl(HibernateUtil.getEntityManager());

        return employeeRepository;
    }

    public static TransactionRepository getTransactionRepository() {

        if (transactionRepository == null)
            transactionRepository = new TransactionRepositoryImpl(HibernateUtil.getEntityManager(), getAccountRepository(), getCardRepository());

        return transactionRepository;
    }
}
