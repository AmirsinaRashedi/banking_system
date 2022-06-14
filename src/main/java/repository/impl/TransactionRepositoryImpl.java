package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import domain.Card;
import domain.Transaction;
import repository.AccountRepository;
import repository.CardRepository;
import repository.TransactionRepository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Scanner;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<Transaction, Long>
        implements TransactionRepository {

    private CardRepository cardRepository;

    private AccountRepository accountRepository;

    public TransactionRepositoryImpl(EntityManager em, AccountRepository accountRepository, CardRepository cardRepository) {
        super(em);

        this.accountRepository = accountRepository;

        this.cardRepository = cardRepository;
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

        senderAccount = accountRepository.findByCard(cardRepository.findByCardNumber(senderCardNumber));

        String receiverCardNumber;

        Account receiverAccount;

        do {

            System.out.print("enter receivers card number(it must be 16 digits): ");
            receiverCardNumber = stringInput.nextLine();

        } while (senderCardNumber.length() != 16);

        Card senderCard = cardRepository.findByCardNumber(receiverCardNumber);

        if (senderCard == null || !senderCard.getActive())
            throw new RuntimeException("this card number is invalid");

        receiverAccount = accountRepository.findByCard(senderCard);

        System.out.print("enter the amount you want to transfer: ");

        Scanner intInput = new Scanner(System.in);

        int amount = intInput.nextInt();


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

                card.setActive(false);

                cardRepository.save(card);

                throw new RuntimeException("card is now disabled");

            }

            if (card.getCvv2() != cvv2 || !card.getSecondaryPassword().equals(secondaryPassword))
                throw new RuntimeException("invalid information");
            else
                break;

        }


    }
}
