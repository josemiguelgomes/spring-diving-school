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
@Table(name = "locations")
public class Location extends BaseEntity<Location> {
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "state_province")
    private String stateProvince;
 //   @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;

    //
    // Constructors
    //
    @Builder
    public Location(Long id,
                    String streetAddress,
                    String postalCode,
                    String city,
                    String stateProvince,
                    Country country) {
        super(id);
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }
}
