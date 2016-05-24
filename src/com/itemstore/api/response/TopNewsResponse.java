package com.itemstore.api.response;

import com.itemstore.api.response.dto.ItemDTO;
import com.itemstore.model.Item;

import java.util.ArrayList;
import java.util.List;

public class TopNewsResponse  {

    private List<ItemDTO> items = new ArrayList<ItemDTO>();

    public List<ItemDTO> getItems() {
        return items;
    }

    public static TopNewsResponse createResponse(List<Item> items) {
        TopNewsResponse response = new TopNewsResponse();

        response.items.addAll(ItemDTO.createResponse(items));
        return response;
    }
}
