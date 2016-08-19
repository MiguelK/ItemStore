package com;

import com.country.sweden.LocationParser;
import com.request.APIRequest;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocationService {
    //  //http://grepcode.com/file/repo1.maven.org/maven2/org.mapdb/mapdb/0.9.1/examples/Custom_Value.java
    private static final String DB_NAME = "geo_locations.db";

    private static final LocationService INSTANCE = new LocationService();
    private static final String MAP_NAME = "map";

    static LocationService getInstance() {
        return INSTANCE;
    }

    Optional<Location> getLocation(double longitude, double latitude){
        String key = longitude + "," + latitude; //FIXME
        DB db = DBMaker
                .fileDB(DB_NAME).transactionEnable()
                .make();
        Serializer<Location> serializer = new CustomSerializer();
        Map<String, Location> locations = db.hashMap(MAP_NAME, Serializer.STRING, serializer).createOrOpen();

        Location location = locations.get(key);

        if(location!=null){
            return Optional.ofNullable(location);//Optional.ofNullable(location);
        }

        APIRequest apiRequest = new APIRequest();

        String jsonResult = apiRequest.getGEOResponse(longitude, latitude);

        Optional<Location> location1 =  LocationParser.getSwedishParser().parse(jsonResult);

//        location = apiRequest.getGEOResponse().get();//MAx wait 3 sec //FIXME
        if(location1.isPresent()){
            locations.put(key, location1.get());
            System.out.println("New location " + location1.get());
            db.commit();  //persist changes into disk
        }

        db.close();

        return location1;
    }

    void clearCache() {

        DB db = DBMaker
                .fileDB(DB_NAME).transactionEnable()
                .make();

        Serializer<Location> serializer = new CustomSerializer();
        Map<String, Location> map = db.hashMap(MAP_NAME, Serializer.STRING, serializer).createOrOpen(); //key serializer is null (use default)

        map.clear();

        db.commit();
        db.close();
    }

    public List<Location> getLoactions() {

        DB db =null;
        try {
             db = DBMaker
                    .fileDB(DB_NAME).transactionEnable()
                    .make();

            Serializer<Location> serializer = new CustomSerializer();
            Map<String, Location> map = db.hashMap(MAP_NAME, Serializer.STRING, serializer).createOrOpen(); //key serializer is null (use default)

            return map.values().stream().collect(Collectors.toList());
        }finally {
            if(db!=null){
                db.close();
            }
        }
    }

    private static final class CustomSerializer implements Serializer<Location>, Serializable
    {
        public int compare(Location first, Location second) {
            return 0;
        }

        public void serialize( DataOutput2 out,  Location value) throws IOException {
            byte[] bs = SerializableUtil.serialize(value);
            Serializer.BYTE_ARRAY.serialize(out, bs);
        }

        public Location deserialize( DataInput2 input, int available) throws IOException {

            byte[] bs = Serializer.BYTE_ARRAY.deserialize(input, available);
            try {
                return (Location) SerializableUtil.deserialize(bs);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
