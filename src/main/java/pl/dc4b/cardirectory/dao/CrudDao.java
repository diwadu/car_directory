package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.BaseEntity;

import java.util.List;

public interface CrudDao<T extends BaseEntity> {
    T create(T entity);
    List<T> getAll();
    T read(int id);
    T update(T entity);
    void delete(int id);
}
