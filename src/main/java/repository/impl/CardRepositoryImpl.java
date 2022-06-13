package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Account;
import domain.Card;
import repository.CardRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Scanner;

public class CardRepositoryImpl extends BaseRepositoryImpl<Card, Long>
        implements CardRepository {

    public CardRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Card> getClassType() {
        return Card.class;
    }

    @Override
    public Card createCard(Account account) {

        Card newCard = new Card();

        Scanner stringInput = new Scanner(System.in);

        String cardNumber;

        do {

            System.out.println("Enter card number(it must br unique and consist of 16 digits exactly)");
            cardNumber = stringInput.nextLine();

        } while (cardNumber.length() != 16 || findByCardNumber(cardNumber) != null);

        newCard.setCardNumber(cardNumber);

        Scanner intInput = new Scanner(System.in);

        System.out.print("write the cvv2: ");
        newCard.setCvv2(intInput.nextInt());

        System.out.print("enter expiration_date in order of (year , month , day) with a" +
                " space between each number : ");
        newCard.setExpirationDate(new Date(intInput.nextInt(), intInput.nextInt() - 1, intInput.nextInt()));

        newCard.setPrimaryPassword(1111);
        System.out.println("your primary password is 1111. you can change it any time you want.");

        newCard.setActive(true);

        return save(newCard);
    }

    @Override
    public Card findByCardNumber(String cardNumber) {

        return em.createQuery("from Card where Card .cardNumber = :cardNumber", Card.class)
                .setParameter("cardNumber", cardNumber).getSingleResult();
    }

    @Override
    public void changePassword(Card card) {
        System.out.println("1- change primary password");
        System.out.println(card.getSecondaryPassword() == null ? "2- set secondary password" : "change secondary password");

        Scanner intInput = new Scanner(System.in);

        int choice = intInput.nextInt();

        if (choice == 1) {

            System.out.print("enter current primary password: ");

            if (card.getPrimaryPassword() == intInput.nextInt()) {

                System.out.print("enter new password: ");

                card.setPrimaryPassword(intInput.nextInt());
            } else
                System.out.println("wrong password");
        } else if (choice == 2) {

            System.out.print("enter current primary password: ");

            if (card.getPrimaryPassword() == intInput.nextInt()) {

                System.out.print("enter new secondary password: ");

                card.setPrimaryPassword(intInput.nextInt());
            } else
                System.out.println("wrong password");

        }
    }


}
