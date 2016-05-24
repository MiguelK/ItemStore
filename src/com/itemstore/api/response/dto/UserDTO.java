package com.itemstore.api.response.dto;

import com.itemstore.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {
    private String mail;
    private String lastName;
    private String image1;
    private String image2;
    private String description1;
    private long latitude;
    private long longitude;
    private String id;

    private String firstName;
    private List<String> tags = new ArrayList<String>(); //FIXME rename to excludeTags
    private List<String> receivedItems = new ArrayList<String>();

    public static List<UserDTO> createResponse(List<User> users) {

        List<UserDTO> userDTOs = new ArrayList<UserDTO>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.id = user.getId();
            userDTO.firstName = user.getFirstName();
            userDTO.lastName = user.getLastName();
            userDTO.receivedItems = user.getReceivedItems();
            userDTO.tags.addAll(user.getExcludeTags());
            userDTOs.add(userDTO);
        }


        return userDTOs;
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

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getReceivedItems() {
        return receivedItems;
    }
}
