package com.request;

import com.google.gson.Gson;
import com.request.GEOServiceRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

//geocode
public class APIRequest implements GEOServiceRequest {

    public String getGEOResponse(double longitude, double latitude) {
    try {
        String lat = longitude + "," + latitude;
        URL obj = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            return null;
        }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


        System.out.println(response.toString());


            return response.toString();//Optional.ofNullable(location);

    } catch (Exception e) {
        e.printStackTrace();
        return  null;
    }
}


    static class Xdata {
        String long_name;

        public String getLong_name() {
            return long_name;
        }

        public void setLong_name(String long_name) {
            this.long_name = long_name;
        }

        @Override
        public String toString() {
            return "Xdata{" +
                    "long_name=" + long_name +
                    '}';
        }
    }
}
