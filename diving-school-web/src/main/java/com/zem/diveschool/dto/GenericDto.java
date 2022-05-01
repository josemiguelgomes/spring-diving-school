package com.zem.diveschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class GenericDto<T extends GenericDto>  implements Comparable<T> {
    private Long id;

    protected GenericDto(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(T o) {
        return id.compareTo(o.getId());
    }
}
