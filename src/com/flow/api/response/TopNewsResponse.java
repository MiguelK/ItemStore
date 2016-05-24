package com.flow.api.response;

import com.flow.api.response.dto.ItemDTO;
import com.flow.engine.model.Item;

import java.io.Serializable;
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
