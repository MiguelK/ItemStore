package com.itemstore.admin.impl;

import com.itemstore.engine.ItemEngine;
import com.itemstore.admin.Action;
import com.itemstore.engine.model.Item;
import com.itemstore.commons.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ItemGetByTagsAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {

        String tagsParam = StringUtils.trimToNull(request.getParameter("itemTagTree"));

        final List<String> tags = StringUtil.split(tagsParam);

        return ItemEngine.getInstance().searchItems(new org.apache.commons.collections4.Predicate<Item>() {
            @Override
            public boolean evaluate(Item item) {

                boolean tagsMatch = true;
                if (!tags.isEmpty()) {
                    throw new UnsupportedOperationException("FIXME tagTree");
                    //tagsMatch = CollectionUtils.containsAny(item.getItemTagTree(), itemTagTree);
                }


                return tagsMatch;
            }
        }); 
    }
}
