package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import domain.Transaction;
import repository.TransactionRepository;
import service.TransactionService;

public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long>
        implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(BaseRepository repository, TransactionRepository transactionRepository) {

        super(repository);

        this.transactionRepository = transactionRepository;

    }

    @Override
    public void doATransaction() {

        try {

            transactionRepository.beginTransaction();

            transactionRepository.doATransaction();

            transactionRepository.commitTransaction();

        } catch (Exception e) {

            transactionRepository.rollbackTransaction();

            e.getStackTrace();

            System.out.println(e.getMessage());

        }

    }
}
