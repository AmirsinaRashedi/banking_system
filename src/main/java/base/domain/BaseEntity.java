package base.domain;


import java.io.Serializable;


public abstract class BaseEntity<ID extends Serializable> {

    private ID id;

    public BaseEntity() {
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
