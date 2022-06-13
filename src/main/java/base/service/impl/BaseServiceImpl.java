package base.service.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseService<T, ID> {

    protected BaseRepository repository;

    public BaseServiceImpl(BaseRepository repository) {

        this.repository = repository;

    }

    @Override
    public T save(T t) {

        try {

            repository.beginTransaction();

            t = (T) repository.save(t);

            repository.commitTransaction();

        } catch (Exception e) {

            e.printStackTrace();

            repository.rollbackTransaction();

        }

        return t;

    }


    @Override
    public void delete(T t) {

        try {

            repository.beginTransaction();

            repository.delete(t);

            repository.commitTransaction();

        } catch (Exception e) {

            e.printStackTrace();

            repository.rollbackTransaction();
        }

    }

    @Override
    public T findById(ID id) {
        return (T) repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

}
