package com.zem.diveschool.persistence.services.impl.springdatajpa;

import com.zem.diveschool.persistence.model.BaseEntity;
import com.zem.diveschool.persistence.services.CrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractServiceSDJpaImpl<T extends BaseEntity, I extends Long, R extends CrudRepository<T, I>>
                                           implements CrudService<T, I>  {
    private final R objectRepository;

    protected AbstractServiceSDJpaImpl(R objectRepository) {
         this.objectRepository = objectRepository;
    }

    public Set<T> findAll() {
        Set<T> objects = new HashSet<>();
        objectRepository.findAll().forEach(objects::add);
        return objects;
    }

    public Optional<T> findById(I id) {
        return objectRepository.findById(id);
    }

    @Transactional
    public T save(T object) {
        return objectRepository.save(object);
    }

    @Transactional
    public void delete(T object) {
        objectRepository.delete(object);
    }

    public void deleteById(I id) {
        objectRepository.deleteById(id);
    }

    @Transactional
    public <S extends T> List<S> saveAll(@NotNull Iterable<S> objects) {

        List<S> result = new ArrayList<>();

        for (S object : objects) {
            result.add((S) save(object));
        }
        return result;
    }

    public R getObjectRepository() {
        return objectRepository;
    }
}
