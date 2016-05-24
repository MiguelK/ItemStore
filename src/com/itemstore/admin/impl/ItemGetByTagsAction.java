package com.itemstore.admin.impl;

import com.itemstore.engine.FlowEngine;
import com.itemstore.admin.Action;
import com.itemstore.engine.model.Item;
import com.itemstore.commons.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ItemGetByTagsAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {

        String tagsParam = StringUtils.trimToNull(request.getParameter("tags"));

        final List<String> tags = StringUtil.split(tagsParam);

        return FlowEngine.getInstance().searchItems(new org.apache.commons.collections4.Predicate<Item>() {
            @Override
            public boolean evaluate(Item item) {

                boolean tagsMatch = true;
                if (!tags.isEmpty()) {
                    tagsMatch = CollectionUtils.containsAny(item.getTags(), tags);
                }


                return tagsMatch;
            }
        }); 
    }
}
