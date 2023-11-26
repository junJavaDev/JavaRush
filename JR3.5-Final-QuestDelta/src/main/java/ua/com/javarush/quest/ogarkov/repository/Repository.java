package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<T extends AbstractEntity> {

    Collection<T> find(T entity);

    T get(long id);

    Collection<T> getAll();

    Collection<T> getAll(int pageNumber, int pageSize);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
