package com.zem.zemdivingschool.persistence.model;

import java.util.Objects;

public class Location {
    String streetAddress;
    String postalCode;
    String city;
    String stateProvince;
    Country country;

    //
    // Constructors
    //

    public Location() {
    }

    public Location(String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }


    //
    // Methods
    //


    //
    // Setters & Getters
    //

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


    //
    //
    //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(streetAddress, location.streetAddress)
            && Objects.equals(postalCode, location.postalCode)
            && Objects.equals(city, location.city)
            && Objects.equals(stateProvince, location.stateProvince)
            && Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetAddress, postalCode, city, stateProvince, country);
    }

    @Override
    public String toString() {
        return "Location{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                ", country=" + country +
                '}';
    }
}
