package com.zem.diveschool.persistence.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "slot_language")
public class SlotLanguage extends BaseEntity {
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    private Slot slot;

    public SlotLanguage() {
    }

    public SlotLanguage(Long id, Language language) {
        super(id);
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotLanguage that = (SlotLanguage) o;
        return language == that.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(language);
    }

    @Override
    public String toString() {
        return "SlotLanguage{" +
                "language=" + language +
                '}';
    }
}
