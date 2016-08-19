package com.country.sweden;

import java.util.EnumSet;

public enum  Country {
    Sweden("Sweden",EnumSet.allOf(Region.class));

    private String name;
    private EnumSet<Region> regions;

    Country(String name,EnumSet<Region> regions) {
        this.name = name;
        this.regions = regions;
    }

    public EnumSet<Region> getRegions() {
        return regions;
    }

    public String getName() {
        return name;
    }
}
