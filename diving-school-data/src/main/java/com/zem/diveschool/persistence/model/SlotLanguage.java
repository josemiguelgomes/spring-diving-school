package com.zem.diveschool.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "slot_language")
public class SlotLanguage extends BaseEntity<SlotLanguage> {
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    private Slot slot;

    @Builder
    public SlotLanguage(Long id, Language language, Slot slot) {
        super(id);
        this.language = language;
        this.slot = slot;
    }
}
