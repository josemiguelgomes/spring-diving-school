package com.zem.zemdivingschool.dto;

import com.zem.zemdivingschool.persistence.model.Language;


public class SlotLanguageDto {
    private Long id;
    private Language language;

    //
    // Constructor
    //
    public SlotLanguageDto() {
    }

    //
    // Getters & Setters
    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
