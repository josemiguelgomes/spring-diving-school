package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Level;
import com.zem.diveschool.persistence.model.Slot;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto extends GenericDto<CourseDto> {
    private String name;
    private Level level;

    private Set<SlotDto> slots = new HashSet<>();

    @Builder
    public CourseDto(Long id, String name, Level level, Set<SlotDto> slots) {
        super(id);
        this.name = name;
        this.level = level;
        this.slots = slots;
    }
}
