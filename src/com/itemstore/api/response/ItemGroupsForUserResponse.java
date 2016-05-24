package com.itemstore.api.response;

import com.itemstore.api.response.dto.ItemGroupDTO;
import com.itemstore.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupsForUserResponse {

    public List<ItemGroupDTO> getItemGroups() {
        return itemGroups;
    }

    private List<ItemGroupDTO> itemGroups = new ArrayList<ItemGroupDTO>();

    public static ItemGroupsForUserResponse createResponse(List<ItemGroup> itemGroups) {

        ItemGroupsForUserResponse itemGroupsForUserResponse = new ItemGroupsForUserResponse();
        itemGroupsForUserResponse.itemGroups.addAll(ItemGroupDTO.createResponse(itemGroups));

        return itemGroupsForUserResponse;
    }
}
