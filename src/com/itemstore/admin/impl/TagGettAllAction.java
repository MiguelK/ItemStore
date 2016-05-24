package com.itemstore.admin.impl;

import com.itemstore.engine.FlowEngine;
import com.itemstore.admin.Action;
import com.itemstore.admin.dto.TagDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TagGettAllAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {
        List<String> tags = FlowEngine.getInstance().getItemTags();
        return TagDTO.map(tags);

    }
}
