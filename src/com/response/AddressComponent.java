package com.response;

import java.util.ArrayList;
import java.util.Collection;

public class AddressComponent {
    private String long_name;
    private String short_name;
    private Collection<String> types = new ArrayList<>();

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public Collection<String> getTypes() {
        return types;
    }

    public void setTypes(Collection<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                "long_name='" + long_name + '\'' +
                ", short_name='" + short_name + '\'' +
                ", types=" + types +
                '}';
    }
}
