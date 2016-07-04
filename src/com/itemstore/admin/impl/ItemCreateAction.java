package com.itemstore.admin.impl;

import com.itemstore.engine.ItemEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.ItemDTO;
import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag3.ItemTagTree;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemCreateAction extends Action {


    @Override
    protected Object execute(HttpServletRequest request) {

   /*     String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imageURL1 = request.getParameter("imageURL1");
        String sourceURL = request.getParameter("sourceURL");
        String targetURL = request.getParameter("targetURL");
        String youTubeVideoID = request.getParameter("youTubeVideoID");

        String tagsParameter = StringUtils.trimToNull(request.getParameter("itemTagTree"));
        List<String> tags = Collections.emptyList();
        if (tagsParameter != null) {
            tags = Arrays.asList(StringUtils.split(tagsParameter, ","));
        }

        Item item = new Item.Builder().title(title).itemTagTree(new ItemTagTree.Builder(tags).build())
                .description(description).imageURL1(imageURL1).
                        sourceURL(sourceURL).targetURL(targetURL).
                        youTubeVideoID(youTubeVideoID).build();

        ItemEngine.getInstance().registerItem(item);

        return ItemDTO.map(Collections.singletonList(item));
*/
        throw new UnsupportedOperationException();
    }
}
