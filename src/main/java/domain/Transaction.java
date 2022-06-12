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

    @Column(name = "time")
    private Date time;


}
