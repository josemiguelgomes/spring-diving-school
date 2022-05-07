package com.zem.diveschool.data.impl;


import com.zem.diveschool.converters.ConvertObjectToObject;
import com.zem.diveschool.dto.GenericDto;
import com.zem.diveschool.persistence.model.BaseEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class AbstractDtoServiceImpl<T extends GenericDto, I extends Long, E extends BaseEntity, S> {

    protected ConvertObjectToObject<E, T> entityToDto;

    protected ConvertObjectToObject<T, E> dtoToEntity;

    protected S service;

    @Autowired
    public final void setBeans(ConvertObjectToObject<E, T> entityToDto,
                               ConvertObjectToObject<T, E> dtoToEntity,
                               S service) {
        this.entityToDto = entityToDto;
        this.dtoToEntity = dtoToEntity;
        this.service = service;
    }

    Set<T> findAll() {
        Class[] parameterType = null;
        try {
            Method meth = service.getClass().getMethod("findAll", parameterType);
            return entityToDto.convert((Set<E>) meth.invoke(service, null));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    Optional<T> findById(I id) {
        Class[] parameterType = new Class[1];
        parameterType[0] = id.getClass();
        try {
            Method meth = service.getClass().getMethod("findById", parameterType);
            Optional<E> e = (Optional<E>) meth.invoke(service, id);
            T t = entityToDto.convert(e.get());
            return Optional.of(t);
//          return Optional.of(entityToDto.convert((E) meth.invoke(service, id)));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    T save(T object) {
        Class[] parameterType = new Class[1];
        parameterType[0] = object.getClass();
        try {
            Method meth = service.getClass().getMethod("save", parameterType);
            E e = dtoToEntity.convert((T) object);
            E eSaved = (E) meth.invoke(service, e);
            return entityToDto.convert(eSaved);
//          return entityToDto.convert((E) meth.invoke(service, dtoToEntity.convert((T) object)));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    void delete(T object) {
        Class[] parameterType = new Class[1];
        parameterType[0] = object.getClass();
        try {
            Method meth = service.getClass().getMethod("delete", parameterType);
            meth.invoke(service, dtoToEntity.convert(object));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteById(I id) {
        Class[] parameterType = new Class[1];
        parameterType[0] = id.getClass();
        try {
            Method meth = service.getClass().getMethod("deleteById", parameterType);
            meth.invoke(service, id);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <S extends T> List<S> saveAll(@NotNull Iterable<S> entities) {

        List<S> result = new ArrayList<>();

        for (S entity : entities) {
            result.add((S) save(entity));
        }

        return result;
    }
}
