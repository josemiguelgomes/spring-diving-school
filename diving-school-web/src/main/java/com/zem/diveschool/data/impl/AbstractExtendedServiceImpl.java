package com.zem.diveschool.data.impl;

import com.zem.diveschool.persistence.model.BaseEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractExtendedServiceImpl<T extends BaseEntity<?>, I extends Long, S> {

    protected S service;

    @Autowired
    public final void setBeans(S service) {
        this.service = service;
    }
    Set<T> findAll() {
        Class[] parameterType = null;
        try {
            // Get Method and invoke
            Method meth = service.getClass().getMethod("findAll", parameterType);
            return (Set<T>) meth.invoke(service, null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    Optional<T> findById(I id) {
        Class[] parameterType = new Class[1];
        parameterType[0] = id.getClass();
        try {
            Method meth = service.getClass().getMethod("findById", parameterType);
            return (Optional<T>) meth.invoke(service, id);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    T save(T object) {
        Class[] parameterType = new Class[1];
        parameterType[0] = object.getClass();
        try {
            Method meth = service.getClass().getMethod("save", parameterType);
            return (T) meth.invoke(service, (T) object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    void delete(T object) {
        Class[] parameterType = new Class[1];
        parameterType[0] = object.getClass();
        try {
            Method meth = service.getClass().getMethod("delete", parameterType);
            meth.invoke(service, object);
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

    protected void saveImageFile(I id, Byte[] byteObjects) {
        Class[] parameterType = new Class[2];
        parameterType[0] = id.getClass();
        parameterType[1] = byteObjects.getClass();
        Object[] args = new Class[2];
        args[0] = id;
        args[1] = byteObjects;
        try {
            Method meth = service.getClass().getMethod("saveImageFile", parameterType);
            meth.invoke(service, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
