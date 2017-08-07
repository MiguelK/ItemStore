package com.itemstore.api;

import com.itemstore.api.response.dto.ItemGroupResponse;
import com.itemstore.engine.ItemEngine;
import com.itemstore.engine.SearchItemGroupQuery;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/itemGroup")
public class ItemGroup {

    private static final Logger LOG = Logger.getLogger(ItemGroup.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItemGroups(@Context HttpServletRequest httpServletRequest) {

        List<com.itemstore.engine.model.ItemGroup> itemGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        LOG.info("itemGroups = " + itemGroups.size());
        ItemGroupResponse response = ItemGroupResponse.create(itemGroups);

        return Response.status(Response.Status.OK).entity(response).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemGroupsFromDate(@Context HttpServletRequest httpServletRequest) {

        List<com.itemstore.engine.model.ItemGroup> itemGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate(); //FIXME

        LOG.info("itemGroups = " + itemGroups.size());
        ItemGroupResponse response = ItemGroupResponse.create(itemGroups);

        return Response.status(Response.Status.OK).entity(response).build();
    }

}
