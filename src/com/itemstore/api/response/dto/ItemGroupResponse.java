package com.itemstore.api.response.dto;

import com.itemstore.engine.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupResponse {

    private List<ItemGroupDTO> allItemGroupsSortedByDate = new ArrayList<ItemGroupDTO>();
    private final String createdDate;


    private ItemGroupResponse(List<ItemGroupDTO> itemGroups) {
        this.allItemGroupsSortedByDate = itemGroups;
        createdDate = "1234FIXME"; //FIXME
    }

    public List<ItemGroupDTO> getAllItemGroupsSortedByDate() {
        return allItemGroupsSortedByDate;
    }

    public static ItemGroupResponse create(List<ItemGroup> itemGroupGroups) {

        List<ItemGroupDTO> items = new ArrayList<ItemGroupDTO>();

        for (ItemGroup itemGroupGroup : itemGroupGroups) {
            ItemGroupDTO response = ItemGroupDTO.createResponse(itemGroupGroup);
            items.add(response);
        }

        ItemGroupResponse result = new ItemGroupResponse(items);


        return result;
    }
}
