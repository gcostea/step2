package net.herbert.step2.model;

import java.util.Objects;

public class City {
    private String name;
    private String country;
    private String subcountry;
    private Integer geonameid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubcountry() {
        return subcountry;
    }

    public void setSubcountry(String subcountry) {
        this.subcountry = subcountry;
    }

    public Integer getGeonameid() {
        return geonameid;
    }

    public void setGeonameid(Integer geonameid) {
        this.geonameid = geonameid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(country, city.country) &&
                Objects.equals(subcountry, city.subcountry) &&
                Objects.equals(geonameid, city.geonameid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, subcountry, geonameid);
    }
}
