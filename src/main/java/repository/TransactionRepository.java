package repository;

import base.repository.BaseRepository;
import domain.Account;
import domain.Card;
import domain.Transaction;

public interface TransactionRepository extends BaseRepository<Transaction, Long> {

    Transaction createTransaction(Account sender, Account receiver, int amount);

    void doATransaction();

    void validateOwner(Card card);
}
