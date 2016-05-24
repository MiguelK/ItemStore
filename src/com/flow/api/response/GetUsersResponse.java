package com.flow.api.response;

import com.flow.api.response.dto.UserDTO;
import com.flow.engine.model.User;

import java.util.ArrayList;
import java.util.List;

public class GetUsersResponse {

    private List<UserDTO> users = new ArrayList<UserDTO>();

    public List<UserDTO> getUsers() {
        return users;
    }

    public static GetUsersResponse create(List<User> users) {
        GetUsersResponse response = new GetUsersResponse();

        response.users.addAll(UserDTO.createResponse(users));
        return response;
    }
}
