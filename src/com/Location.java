package com;

import com.country.sweden.Country;

import java.io.Serializable;
import java.util.Optional;

//https://maps.googleapis.com/maps/api/geocode/json?latlng=56.6634447,16.35677899999996
public class Location implements Serializable {

    private Country country;//Sweden
    private String reqion; //Stockholms län
    private String city; //Stockholm optional
    private String district; //Södermalm optional

     public Location(Country country, String reqion, String city,String district) {
        this.country = country;
        this.reqion = reqion;
        this.city = city;
         this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public String getCountry() {
        return country.getName();
    }

    public String getReqion() {
        return reqion;
    }

    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    boolean isSwedish(){
        return country==Country.Sweden;
    }

    boolean fetchedFromCache(){
        return false;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", reqion='" + reqion + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
