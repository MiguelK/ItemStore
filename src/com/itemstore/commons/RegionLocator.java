package com.itemstore.commons;

import javax.servlet.http.HttpServletRequest;

class RegionLocator {

    public Region locateRegion(HttpServletRequest request) {
        return Region.Swe_Stockholm; //FIXME ????
    }

}
