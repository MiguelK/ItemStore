package com.itemstore.admin.impl;

import com.itemstore.engine.FlowEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.ItemDTO;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag.TagContainer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemCreateRandomAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {

        int itemsToGenerate = NumberUtils.toInt(request.getParameter("itemsToGenerate"), 1);

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String tagsParameter = StringUtils.trimToNull(request.getParameter("tags"));
        List<String> tags = Collections.emptyList();
        if (tagsParameter != null) {
            tags = Arrays.asList(StringUtils.split(tagsParameter, ","));
        }

        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < itemsToGenerate; i++) {
            Item.Builder builder = new Item.Builder();
            builder.title(title + "_" + i).tags(TagContainer.create(tags))
                    .description(description);
            items.add(builder.build());
        }

        FlowEngine.getInstance().registerItems(items);

        return ItemDTO.map(items);
    }
}
