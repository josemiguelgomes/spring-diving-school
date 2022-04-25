package com.zem.zemdivingschool.persistence.services.impl.map;

import com.zem.zemdivingschool.persistence.model.BaseEntity;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class AbstractMapService <T extends BaseEntity, I extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(I id) {
        return map.get(id);
    }

    T save(T object) {

        if(object != null) {
            if(object.getId() == null){
                object.setId(getNextId());
            }

            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }
    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(I id) {
        map.remove(id);
    }

    public <S extends T> List<S> saveAll(@NotNull Iterable<S> entities) {

        List<S> result = new ArrayList<>();

        for (S entity : entities) {
            result.add((S) save(entity));
        }

        return result;
    }

    private Long getNextId() {

        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }

}
