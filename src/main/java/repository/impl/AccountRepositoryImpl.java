package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import domain.Branch;
import domain.Card;
import domain.Customer;
import repository.AccountRepository;
import repository.BranchRepository;
import repository.CardRepository;
import repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long>
        implements AccountRepository {

    private BranchRepository branchRepository;

    private CardRepository cardRepository;

    private CustomerRepository customerRepository;

    public AccountRepositoryImpl(EntityManager em, BranchRepository branchRepository, CardRepository cardRepository, CustomerRepository customerRepository) {

        super(em);

        this.branchRepository = branchRepository;

        this.cardRepository = cardRepository;

        this.customerRepository = customerRepository;
    }

    @Override
    public Class<Account> getClassType() {
        return Account.class;
    }

    @Override
    public Account setUpAccount(Customer owner) {

        Account newAccount = new Account();

        newAccount.setOwner(owner);

        int branchCount = 0;

        for (Branch branch : branchRepository.findAll())

            System.out.println(++branchCount + " " + branch.getAddress());

        Scanner intInput = new Scanner(System.in);

        int selectedBranch;

        do {

            System.out.print("select the branch you want to set up your account in: ");
            selectedBranch = intInput.nextInt();

        } while (selectedBranch < 1 || selectedBranch > branchCount);

        newAccount.setBranch(branchRepository.findAll().get(selectedBranch - 1));

        System.out.print("how much is the balance of your account: ");

        int balanceAmount = intInput.nextInt();

        if (balanceAmount < 0)
            throw new RuntimeException("invalid amount");

        newAccount.setBalance(balanceAmount);

        System.out.println("do you want to create a card for your account?(1-yes,2-no)");

        if (intInput.nextInt() == 1)
            cardRepository.createCard(newAccount);

        newAccount.setActive(true);


        return save(newAccount);
    }

    @Override
    public Account findByCard(Card card) {

        try {
            if (card.getActive())
                return em.createQuery("from Account A where " +
                                "A.card = :card", Account.class)
                        .setParameter("card", card)
                        .getSingleResult();
            else
                return null;
        } catch (NoResultException e) {
            return null;
        }


    }

    @Override
    public List<Account> findByOwnerSsn(String Ssn) {

        try {
            if (customerRepository.findBySsn(Ssn) != null)
                return em.createQuery("from Account A where A.owner = :owner", Account.class)
                        .setParameter("owner", customerRepository.findBySsn(Ssn))
                        .getResultList();

            else
                return null;
        } catch (NoResultException e) {
            return null;
        }


    }


}
