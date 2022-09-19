package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.AbstractEntity;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    protected void setLastId(long lastId) {
        id.set(lastId);
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
    public Collection<T> getAll(int pageNumber, int pageSize) {
        Collection<T> entities = map.values();
        long currentPage =
                entities.size()/pageSize >= pageNumber-1
                ? pageNumber-1
                : 0L;
        return entities.stream()
                .sorted(Comparator.comparingLong(AbstractEntity::getId))
                .skip(currentPage * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
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
