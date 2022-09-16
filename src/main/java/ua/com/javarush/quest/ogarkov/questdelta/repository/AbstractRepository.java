package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.AbstractEntity;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public abstract class AbstractRepository <T extends AbstractEntity> implements Repository <T> {

    private static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    protected final Map<Long, T> map = new HashMap<>();
    @Override
    public abstract Collection<T> find(T pattern);

    protected  <V> boolean isOk(T pattern, T current, Function<T, V> fieldGetter) {
        V currentFieldValue = fieldGetter.apply(current);
        V patternFieldValue = fieldGetter.apply(pattern);
        return patternFieldValue == null || patternFieldValue.equals(currentFieldValue);
    }

    @Override
    public Optional<T> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Collection<T> getAll() {
        return map.values();
    }

    @Override
    public void create(T entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(T entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }
}
