package domain;

import base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = Transaction.TABLE_NAME)
public class Transaction extends BaseEntity<Long> {

    public static final String TABLE_NAME = "transaction";

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "time", columnDefinition = "DATE")
    private Date time;

    public Transaction() {
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
