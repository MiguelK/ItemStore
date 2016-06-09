package com.itemstore.admin.dto;

import com.itemstore.engine.model.ItemGroup;

import java.util.List;

public class ItemGetItemGroupsDTO {

    public int countItemGroups;
    public String description;

    public static ItemGetItemGroupsDTO map(List<ItemGroup> components) {

       /* StringBuilder description = new StringBuilder();
        for (ItemGroup component : components) {
            description.append("Tags=").append(component.getTags())
                    .append(" items=").append(component.collect().size()).append("<br>");
        }

        ItemGetItemGroupsDTO itemGroupsDTO = new ItemGetItemGroupsDTO();
        itemGroupsDTO.countItemGroups = components.size();
        itemGroupsDTO.description = description.toString();
        return itemGroupsDTO;*/
        throw new UnsupportedOperationException();
    }
}
