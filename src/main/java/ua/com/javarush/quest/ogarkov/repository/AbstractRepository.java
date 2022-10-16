package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.AbstractEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    private static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    protected final Map<Long, T> map = new HashMap<>();

    protected <V> boolean isOk(T pattern, T current, Function<T, V> fieldGetter) {
        V currentFieldValue = fieldGetter.apply(current);
        V patternFieldValue = fieldGetter.apply(pattern);
        return patternFieldValue == null || patternFieldValue.equals(currentFieldValue);
    }

    @Override
    public T get(long id) {
        return map.get(id);
    }

    @Override
    public Collection<T> getAll() {
        return map.values();
    }

    @Override
    public Collection<T> getAll(int pageNumber, int pageSize) {
        Collection<T> entities = map.values();
        int pageIndex = pageNumber - 1;
        long currentPage = entities.size() / pageSize >= pageIndex
                ? pageIndex
                : 0L;
        return entities.stream()
                .sorted(Comparator.comparingLong(AbstractEntity::getId))
                .skip(currentPage * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public void create(T entity) {
        Long entityId = entity.getId();
        if (entityId == null || entityId < 1 || map.containsKey(entityId)) {
            entity.setId(id.incrementAndGet());
        }
        update(entity);
    }

    @Override
    public abstract Collection<T> find(T pattern);

    @SafeVarargs
    protected final <V> Collection<T> find(T pattern, Function<T, V>... fieldGetter) {
        return map.values()
                .stream()
                .filter(value ->
                        Arrays.stream(fieldGetter)
                                .allMatch(function ->
                                        isOk(pattern, value, function)))
                .toList();
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
