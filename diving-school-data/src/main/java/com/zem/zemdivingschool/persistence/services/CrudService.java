package com.zem.zemdivingschool.persistence.services;

import java.util.Set;

public interface CrudService<T, I> {

    Set<T> findAll();

    T findById(I id);

    T save(T object);

    void delete(T object);

    void deleteById(I id);
}
