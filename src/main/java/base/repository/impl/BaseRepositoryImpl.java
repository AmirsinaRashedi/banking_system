package base.repository.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<T, ID> {


    protected EntityManager em;

    public BaseRepositoryImpl(EntityManager em) {

        this.em = em;

    }

    @Override
    public T save(T t) {

        em.persist(t);

        return t;
    }

    @Override
    public T update(T t) {

        T newEntity;

        newEntity = em.merge(t);

        return newEntity;

    }

    @Override
    public void delete(T t) {

        t = em.find(getClassType(), t.getId());

        em.remove(t);


    }

    @Override
    public T findById(ID id) {

        T newEntity;

        newEntity = em.find(getClassType(), id);

        return newEntity;
    }


    public abstract Class<T> getClassType();
}
