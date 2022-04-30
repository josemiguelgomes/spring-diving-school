package com.zem.diveschool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericDto<T extends GenericDto>  implements Comparable<T> {
    private Long id;

    @Override
    public int compareTo(T o) {
        return id.compareTo(o.getId());
    }
}
