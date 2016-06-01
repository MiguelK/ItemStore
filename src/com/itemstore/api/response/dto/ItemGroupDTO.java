package com.itemstore.api.response.dto;

import com.itemstore.engine.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupDTO  {

    private List<ItemDTO> items = new ArrayList<ItemDTO>();

    public static ItemGroupDTO createResponse(ItemGroup itemGroup) {
        ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
        itemGroupDTO.items.addAll(ItemDTO.createResponse(itemGroup.getItems()));

        return itemGroupDTO;
    }

    public static List<ItemGroupDTO> createResponse(List<ItemGroup> itemGroups) {
        List<ItemGroupDTO> responses = new ArrayList<ItemGroupDTO>();

        for (ItemGroup itemGroup : itemGroups) {
            responses.add(ItemGroupDTO.createResponse(itemGroup));
        }

        return responses;
    }

    public List<ItemDTO> getItems() {
        return items;
    }
}
