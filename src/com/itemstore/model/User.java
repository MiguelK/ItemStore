package com.itemstore.model;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private String mail;
    private String lastName;
    private String image1;
    private String image2;
    private String description1;
    private long latitude;
    private long longitude;
    private String firstName;

    private String id;
    private Set<String> excludeTags = new HashSet<String>();
    private Set<String> receivedItems = new HashSet<String>();
    private Set<String> favoriteTags = new HashSet<String>();


    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public List<String> getExcludeTags() {
        return new ArrayList<String>(excludeTags);
    }

    public List<String> getFavoriteTags() {
        return new ArrayList<String>(favoriteTags);
    }

    public void addExcludeTag(String tag) {
        excludeTags.add(tag);
    }

    public List<String> getReceivedItems() {
        return new ArrayList<String>(receivedItems);
    }

    public String getId() {
        return id;
    }

    //Used for test mock
    public void setId(String id) {
        this.id = id;
    }

    public void addReceivedItems(List<String> items) {
        receivedItems.addAll(items);
    }

    public void addExcludeTags(List<String> tags) {
        excludeTags.addAll(tags);
    }


    public void addFavoriteTags(List<String> tags) {
        favoriteTags.addAll(tags);
    }


    public String getMail() {
        return mail;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getDescription1() {
        return description1;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
