package com.zem.diveschool.persistence.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends BaseEntity<Country> {
    @Column(name = "country")
    private String country;
    @Column(name = "isocode")
    private String isoCode;

    @Builder
    public Country(Long id, String country, String isocode) {
        super(id);
        this.country = country;
        this.isoCode = isocode;
    }
}