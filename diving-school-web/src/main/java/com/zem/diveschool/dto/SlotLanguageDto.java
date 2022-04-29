package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Language;


public class SlotLanguageDto extends GenericDto<SlotLanguageDto> {
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
