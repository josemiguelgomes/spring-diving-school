package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Level;
import com.zem.diveschool.persistence.model.Slot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto extends GenericDto<CourseDto> {
    private Long id;
    private String name;
    private Level level;

    private Set<Slot> slots = new HashSet<>();
}
