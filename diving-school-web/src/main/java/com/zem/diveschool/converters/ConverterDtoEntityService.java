package com.zem.diveschool.converters;

import java.util.Collection;
import java.util.Set;

/*
 *
 * T - Dto
 * U - Entity
 *
 * */
public interface ConverterDtoEntityService<T, U> {

    U convertFromDto(final T dto);

    T convertFromEntity(final U entity);

    Set<U> convertFromDtos(final Collection<T> dtos);

    Set<T> convertFromEntities(final Collection<U> entities);
}
