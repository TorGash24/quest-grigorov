package ru.javarush.quest.repository;

import java.util.HashMap;
import java.util.Map;

public class Repository<K, T> {

    protected Map<K, T> repository = new HashMap<K, T>();

    public void save(K id, T value) {
        repository.put(id, value);
    }

    public T getById(K id) {
        return repository.get(id);
    }

    public boolean isExists(K id) {
        return repository.containsKey(id);
    }

    public boolean isEmpty() {
        return repository.isEmpty();
    }


}
