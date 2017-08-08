package com.itemstore.location.country.sweden;

import com.itemstore.location.Location;


import java.util.EnumSet;
import java.util.Optional;

public class LocationParserSweden implements LocationParser {

    public static final String COUNTRY_NAME = "Sweden";

    @Override
    public Optional<Location> parse(String result) {

        if (!result.contains(COUNTRY_NAME)) {
            return null;
        }

        //FIXME Parse...
        Country country = Country.Sweden;
        EnumSet<Region> regions = country.getRegions();
        Region region = regions.stream().filter(r -> result.contains(r.getName())).findFirst().orElse(null);

        if(region == null){
            return null;
        }

        City city1 = region.getCities().stream().filter(c -> result.contains(c.getName())).findFirst().orElse(null);

        District district1=null;
        if(city1!=null){
            district1 = city1.getDistricts().stream().filter(d -> result.contains(d.getName())).findFirst().orElse(null);
        }

        String city = city1==null  ? null :city1.getName();
        String district = district1==null ? null : district1.getName();
        Location location = new Location(country, region.getName(), city, district);

        return Optional.ofNullable(location);
    }
}
