package com.itemstore.location.country.sweden;

import com.itemstore.location.Location;

import java.util.Optional;

public interface LocationParser {

     static LocationParser getSwedishParser() {
            return new LocationParserSweden();
     }

     Optional<Location> parse(String result);
}
