package com.zem.diveschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
public abstract class GenericDto<T extends GenericDto>  implements Comparable<T> {
    protected final SimpleDateFormat dateFormat
//            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;

    protected GenericDto(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(T o) {
        return id.compareTo(o.getId());
    }
}
