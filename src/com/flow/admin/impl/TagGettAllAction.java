package com.flow.admin.impl;

import com.flow.engine.FlowEngine;
import com.flow.admin.Action;
import com.flow.admin.dto.TagDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TagGettAllAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {
        List<String> tags = FlowEngine.getInstance().getItemTags();
        return TagDTO.map(tags);

    }
}
