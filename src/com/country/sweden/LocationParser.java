package com.country.sweden;

import com.Location;

import java.util.Optional;

public interface LocationParser {

     static LocationParser getSwedishParser() {
            return new LocationParserSweden();
     }

     Optional<Location> parse(String result);
}
