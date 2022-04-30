package com.zem.diveschool.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Slot> slots = new HashSet<>();

    //
    // Constructors
    //

    @Builder
    public Course(Long id, String name, Level level, Set<Slot> slots) {
        super(id);
        this.name = name;
        this.level = level;
        this.slots = slots;
    }

    //
    // Methods
    //
    public void add(Slot slot) {
        this.slots.add(slot);
    }
    public void delete(Slot slot) { this.slots.remove(slot); }
}
