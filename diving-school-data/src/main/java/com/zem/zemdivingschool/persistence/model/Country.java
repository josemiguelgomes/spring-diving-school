package com.zem.zemdivingschool.persistence.model;

import java.util.Objects;

public class Country {
    private String regionName;

    public Country() {
    }

    public Country(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(regionName, country.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName);
    }

    @Override
    public String toString() {
        return "Country{" +
                "regionName='" + regionName + '\'' +
                '}';
    }
}
