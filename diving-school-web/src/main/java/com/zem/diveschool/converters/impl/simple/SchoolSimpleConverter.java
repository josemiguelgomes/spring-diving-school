package com.zem.diveschool.converters.impl.simple;

import com.zem.diveschool.converters.ConverterDtoEntityService;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 *
 * T - Dto
 * U - Entity
 *
 */

public abstract class SchoolSimpleConverter<T, U> implements ConverterDtoEntityService<T, U> {

    protected static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static {
     dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().toString()));
    }


    private final Function<T, U> fromDto;
    private final Function<U, T> fromEntity;

    public SchoolSimpleConverter(final Function<T, U> fromDto, final Function<U, T> fromEntity) {
        this.fromDto = fromDto;
        this.fromEntity = fromEntity;
    }


    public final U convertFromDto(final T dto) {
        return fromDto.apply(dto);
    }

    public final T convertFromEntity(final U entity) {
        return fromEntity.apply(entity);
    }

    public final Set<U> convertFromDtos(final Collection<T> dtos) {
        return dtos.stream().map(this::convertFromDto).collect(Collectors.toSet());
    }

    public final Set<T> convertFromEntities(final Collection<U> entities) {
        return entities.stream().map(this::convertFromEntity).collect(Collectors.toSet());
    }
}