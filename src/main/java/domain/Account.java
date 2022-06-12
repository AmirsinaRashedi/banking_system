package domain;

import base.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = Account.TABLE_NAME)
public class Account extends BaseEntity<Long> {

    public static final String TABLE_NAME = "account";


    @Column(name = "balance")
    private Integer balance;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer owner;

    @OneToOne
    private Card card;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Branch branch;


    public Account() {
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
