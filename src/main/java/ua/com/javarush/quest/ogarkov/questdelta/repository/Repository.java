package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.AbstractEntity;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T extends AbstractEntity> {

    Collection<T> find(T entity);

    Optional<T> get(long id);

    Collection<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}