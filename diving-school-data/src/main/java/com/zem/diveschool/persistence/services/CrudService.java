package com.zem.diveschool.persistence.services;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public interface CrudService<T, I> {

    Set<T> findAll();

    T findById(I id);

    T save(T object);

    void delete(T object);

    void deleteById(I id);

    //
    <S extends T> List<S> saveAll(@NotNull Iterable<S> entities);

}
