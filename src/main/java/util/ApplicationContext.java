package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

public class ApplicationContext {

    public static AccountService accountService;
    public static BranchService branchService;
    public static CardService cardService;
    public static CustomerService customerService;
    public static EmployeeService employeeService;
    public static TransactionService transactionService;
    public static AccountRepository accountRepository;
    public static AddressRepository addressRepository;
    public static BranchRepository branchRepository;
    public static CardRepository cardRepository;
    public static CustomerRepository customerRepository;
    public static EmployeeRepository employeeRepository;
    public static TransactionRepository transactionRepository;

    private ApplicationContext() {
    }

    static {
        addressRepository = new AddressRepositoryImpl(HibernateUtil.getEntityManager());
        employeeRepository = new EmployeeRepositoryImpl(HibernateUtil.getEntityManager());
        cardRepository = new CardRepositoryImpl(HibernateUtil.getEntityManager());
        branchRepository = new BranchRepositoryImpl(HibernateUtil.getEntityManager(), addressRepository, employeeRepository);
        customerRepository = new CustomerRepositoryImpl(HibernateUtil.getEntityManager(), addressRepository);
        accountRepository = new AccountRepositoryImpl(HibernateUtil.getEntityManager(), branchRepository, cardRepository, customerRepository);
        customerRepository.setAccountRepository(accountRepository);
        transactionRepository = new TransactionRepositoryImpl(HibernateUtil.getEntityManager(), accountRepository, cardRepository);
        accountService = new AccountServiceImpl(accountRepository);
        branchService = new BranchServiceImpl(branchRepository);
        cardService = new CardServiceImpl(cardRepository);
        customerService = new CustomerServiceImpl(customerRepository);
        employeeService = new EmployeeServiceImpl(employeeRepository, branchRepository);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }


}
