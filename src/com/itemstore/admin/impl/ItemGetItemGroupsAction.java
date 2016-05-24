package com.itemstore.admin.impl;

import com.itemstore.admin.Action;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
