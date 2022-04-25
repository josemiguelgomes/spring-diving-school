package com.zem.zemdivingschool.persistence.services.impl.springdatajpa;

import com.zem.zemdivingschool.persistence.model.BaseEntity;
import com.zem.zemdivingschool.persistence.services.CrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public abstract class AbstractSDJpaService <T extends BaseEntity, I extends Long, R extends CrudRepository<T, I>>
                                           implements CrudService<T, I>  {
    private final R objectRepository;

    protected AbstractSDJpaService(R objectRepository) {
         this.objectRepository = objectRepository;
    }

    public Set<T> findAll() {
        Set<T> objects = new HashSet<>();
        objectRepository.findAll().forEach(objects::add);
        return objects;
    }

    public T findById(I id) {
        return objectRepository.findById(id).orElse(null);
    }

    public T save(T object) {
        return objectRepository.save(object);
    }

    public void delete(T object) {
        objectRepository.delete(object);
    }

    public void deleteById(I id) {
        objectRepository.deleteById(id);
    }

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
