package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import domain.Card;
import domain.Customer;
import domain.Transaction;
import repository.AccountRepository;
import repository.CardRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import util.Menu;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Scanner;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<Transaction, Long>
        implements TransactionRepository {

    private CardRepository cardRepository;

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    public TransactionRepositoryImpl(EntityManager em, AccountRepository accountRepository, CardRepository cardRepository, CustomerRepository customerRepository) {
        super(em);

        this.accountRepository = accountRepository;

        this.cardRepository = cardRepository;

        this.customerRepository = customerRepository;
    }

    @Override
    public Class<Transaction> getClassType() {
        return Transaction.class;
    }

    @Override
    public Transaction createTransaction(Account sender, Account receiver, int amount) {

        Transaction newTransaction = new Transaction();

        newTransaction.setSender(sender);

        newTransaction.setReceiver(receiver);

        newTransaction.setAmount(amount);

        newTransaction.setTime(new Date(System.currentTimeMillis()));

        return save(newTransaction);
    }

    @Override
    public void doATransaction() {

        Scanner stringInput = new Scanner(System.in);

        String senderCardNumber;

        Account senderAccount;

        do {

            System.out.print("enter your card number(it must be 16 digits): ");
            senderCardNumber = stringInput.nextLine();

        } while (senderCardNumber.length() != 16);

        if (cardRepository.findByCardNumber(senderCardNumber) == null || !cardRepository.findByCardNumber(senderCardNumber).getActive())
            throw new RuntimeException("this card number is invalid");

        Card senderCard = cardRepository.findByCardNumber(senderCardNumber);

        senderAccount = accountRepository.findByCard(senderCard);

        String receiverCardNumber;

        Account receiverAccount;

        do {

            System.out.print("enter receivers card number(it must be 16 digits): ");
            receiverCardNumber = stringInput.nextLine();

        } while (receiverCardNumber.length() != 16);

        if (senderCardNumber.equals(receiverCardNumber))
            throw new RuntimeException("can't transfer money to the same account");

        Card receiverCard = cardRepository.findByCardNumber(receiverCardNumber);

        if (receiverCard == null || !receiverCard.getActive())
            throw new RuntimeException("this card number is invalid");

        receiverAccount = accountRepository.findByCard(receiverCard);

        System.out.print("enter the amount you want to transfer: ");

        Scanner intInput = new Scanner(System.in);

        int amount = intInput.nextInt();

        if (amount <= 0)
            throw new RuntimeException("amount not acceptable!");

        if (amount + 500 > senderAccount.getBalance())
            throw new RuntimeException("low balance");

        validateOwner(senderCard);

        senderAccount.setBalance(senderAccount.getBalance() - (amount + 500));

        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);

        accountRepository.save(receiverAccount);

        createTransaction(senderAccount, receiverAccount, amount);
    }

    @Override
    public void validateOwner(Card card) {

        Scanner stringInput = new Scanner(System.in);

        Scanner intInput = new Scanner(System.in);

        int flagCount = 0;

        if (card.getSecondaryPassword() == null)
            throw new RuntimeException("this card does not have a secondary password");

        while (true) {

            System.out.print("enter cvv2:");

            int cvv2 = intInput.nextInt();

            System.out.print("enter secondary password:");

            String secondaryPassword = stringInput.nextLine();

            if (!card.getSecondaryPassword().equals(secondaryPassword))
                flagCount++;

            if (flagCount == 3) {

                System.out.println("you have entered an used an incorrect password 3 times. your card is suspended! ");

                cardRepository.rollbackTransaction();

                cardRepository.beginTransaction();

                card.setActive(false);

                cardRepository.save(card);

                Account suspendedAccount = accountRepository.findByCard(card);

                suspendedAccount.setCard(null);

                accountRepository.save(suspendedAccount);

                cardRepository.commitTransaction();


                throw new RuntimeException("card is now disabled");

            }

            if (card.getCvv2() != cvv2 || !card.getSecondaryPassword().equals(secondaryPassword))
                System.out.println("invalid information!");
            else
                break;

        }


    }

    @Override
    public void findTransaction() {

        Menu.transactionMenu();

        Scanner intInput = new Scanner(System.in);

        Scanner stringInput = new Scanner(System.in);

        int choice = intInput.nextInt();

        switch (choice) {

            case 1: {

                Account senderAccount;

                System.out.println("enter your Ssn:");

                String ssn = stringInput.nextLine();

                Customer currentCustomer = customerRepository.findBySsn(ssn);

                int accountCount = 0;

                if (currentCustomer != null) {

                    for (Account account : accountRepository.findByOwnerSsn(ssn)) {

                        System.out.println(++accountCount + "- " + account);

                    }

                    System.out.print("select the account you want to see the transactions: ");

                    int accountSelected = intInput.nextInt();

                    if (accountSelected > 0 && accountSelected <= accountCount)
                        senderAccount = accountRepository.findByOwnerSsn(ssn).get(accountSelected - 1);
                    else
                        throw new RuntimeException("input out of bounds");

                } else
                    throw new RuntimeException("this user does not exist");

                findBySenderAccount(senderAccount);

                break;

            }
            case 2: {

                System.out.print("enter date_of_birth in order of (year , month , day) with a" +
                        " space between each number : ");
                Date transactionDate = new Date(intInput.nextInt() - 1900, intInput.nextInt() - 1, intInput.nextInt());

                findByDate(transactionDate);

                break;
            }
            case 3: {

                Account senderAccount;

                System.out.println("enter your Ssn:");

                String ssn = stringInput.nextLine();

                Customer currentCustomer = customerRepository.findBySsn(ssn);

                int accountCount = 0;

                if (currentCustomer != null) {

                    for (Account account : accountRepository.findByOwnerSsn(ssn)) {

                        System.out.println(++accountCount + "- " + account);

                    }

                    System.out.print("select the account you want to see the transactions: ");

                    int accountSelected = intInput.nextInt();

                    if (accountSelected > 0 && accountSelected <= accountCount)
                        senderAccount = accountRepository.findByOwnerSsn(ssn).get(accountSelected - 1);
                    else
                        throw new RuntimeException("input out of bounds");

                } else
                    throw new RuntimeException("this user does not exist");

                System.out.print("enter date_of_birth in order of (year , month , day) with a" +
                        " space between each number : ");
                Date transactionDate = new Date(intInput.nextInt() - 1900, intInput.nextInt() - 1, intInput.nextInt());

                findBySenderAccountAndDate(senderAccount, transactionDate);

                break;
            }

            default: {
                throw new IndexOutOfBoundsException("index out of bounds");
            }

        }

    }


    @Override
    public void findBySenderAccount(Account account) {

        for (Transaction transaction : em.createQuery("from Transaction t where t.sender = : account", Transaction.class).setParameter("account", account).getResultList()) {
            System.out.println(transaction);
        }

    }

    @Override
    public void findByDate(java.util.Date date) {

        for (Transaction transaction : em.createQuery("from Transaction t where t.time = : time", Transaction.class).setParameter("time", date).getResultList()) {
            System.out.println(transaction);
        }

    }

    @Override
    public void findBySenderAccountAndDate(Account account, java.util.Date date) {

        for (Transaction transaction : em.createQuery("from Transaction t where t.time = : Time and t.sender = :account", Transaction.class).setParameter("Time", date).setParameter("account", account).getResultList()) {
            System.out.println(transaction);
        }

    }
}


