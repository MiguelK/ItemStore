package com.itemstore.admin.impl;

import com.itemstore.engine.ItemEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.TagDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TagGettAllAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {
        List<String> tags = ItemEngine.getInstance().getAllItemTags();
        return TagDTO.map(tags);
}
}
