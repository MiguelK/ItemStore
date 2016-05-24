package com.itemstore.admin.dto;


import com.itemstore.engine.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
    public String title;

    public static List<ItemDTO> map(List<Item> items) {

        List<ItemDTO> result = new ArrayList<ItemDTO>();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.title = item.getTitle();
            result.add(itemDTO);
        }

        return result;
    }
}
