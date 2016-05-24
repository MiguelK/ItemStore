package com.flow.admin.impl;

import com.flow.admin.Action;
import com.flow.admin.dto.ItemGetItemGroupsDTO;
import com.flow.engine.FlowEngine;
import com.flow.engine.model.ItemGroup;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ItemGetItemGroupsAction extends Action {

    @Override
    protected Object execute(HttpServletRequest request) {

        String userId = StringUtils.trimToNull(request.getParameter("userId"));

       // List<ItemGroup> components =
        //         FlowEngine.getInstance().getItemComponentsForUser(userId);

        throw  new UnsupportedOperationException();
       // return ItemGetItemGroupsDTO.map(components);
    }
}
