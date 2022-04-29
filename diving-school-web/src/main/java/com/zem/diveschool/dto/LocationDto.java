package com.zem.diveschool.dto;

import com.zem.diveschool.persistence.model.Country;

public class LocationDto extends GenericDto<LocationDto> {
    private Long id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private Country country;

    //
    // Constructor
    //
    public LocationDto() {
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
