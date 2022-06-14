package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import domain.Account;
import domain.Card;
import repository.CardRepository;
import service.CardService;

public class CardServiceImpl extends BaseServiceImpl<Card, Long>
        implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(BaseRepository repository, CardRepository cardRepository) {

        super(repository);

        this.cardRepository = cardRepository;

    }

    @Override
    public Card createCard(Account account) {

        Card newCard = null;

        try {

            cardRepository.beginTransaction();

            newCard = cardRepository.createCard(account);

            cardRepository.commitTransaction();

        } catch (Exception e) {

            cardRepository.rollbackTransaction();

            e.getStackTrace();

            System.out.println(e.getMessage());

            return null;

        }

        return newCard;
    }

    @Override
    public void changePassword(Card card) {

        try {

            cardRepository.beginTransaction();

            cardRepository.changePassword(card);

            cardRepository.commitTransaction();

        } catch (Exception e) {

            cardRepository.rollbackTransaction();

            e.getStackTrace();

            System.out.println(e.getMessage());
        }

    }
}
