package com.country.sweden;

public enum District {

    Sodermalm("Södermalm"),
    Enskede_Arsta_Vantor("Enskede-Årsta-Vantör"),
    Hagersten_Liljeholmen("Hägersten-Liljeholmen"),
            Norrmalm("Norrmalm"),
    Bromma("Bromma"),
    Hasselby_Vallingby("Hässelby-Vällingby"),
            Ostermalm("Östermalm"),
    Kungsholmen("Kungsholmen"),
            Farsta("Farsta"),
    Rinkeby_Kista("Rinkeby-Kista"),
            Skarpnack("Skarpnäck"),
    Spanga_Tensta("Spånga-Tensta"),
            Skarholmen("Skärholmen"),
    Alvsjo("Älvsjö");
    private String name;

    District(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
