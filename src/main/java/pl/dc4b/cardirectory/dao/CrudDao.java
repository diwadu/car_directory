package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.BaseEntity;

import java.util.List;

public interface CrudDao<T extends BaseEntity> {
    void create(T entity);
    List<T> getAll();
    T getById(int id);
    void update(T entity);
    void delete(int id);
}
