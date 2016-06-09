package com.itemstore.api;

import com.itemstore.engine.SearchItemGroupQuery;
import com.itemstore.api.response.dto.ItemGroupResponse;
import com.itemstore.engine.ItemEngine;

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
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchItemGroups(@Context HttpServletRequest httpServletRequest,
                                     @QueryParam("excludeTagFilter") String excludeTagFilter,
                                     @QueryParam("favoriteTagFilter") String favoriteTagFilter,
                                     @QueryParam("requiredItemFields") List<String> requiredItemFields, //FIXME implement
                                     @QueryParam("excludeItemGroupIds") List<Integer> excludeItemGroupIds,
                                     @QueryParam("itemGroupIds") List<Integer> itemGroupIds,
                                     @QueryParam("maxResultSize") Integer maxResultSize) {

        SearchItemGroupQuery searchQuery;

        try {
            searchQuery = SearchItemGroupQuery.create(excludeTagFilter, favoriteTagFilter, excludeItemGroupIds,
                    itemGroupIds, maxResultSize);
            LOG.info("excludeTagFilter=" + excludeTagFilter);
        } catch (SearchItemGroupQuery.InvalidRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        List<com.itemstore.engine.model.ItemGroup> itemGroups = ItemEngine.getInstance().search(searchQuery);

        ItemGroupResponse response = ItemGroupResponse.create(itemGroups);

        return Response.status(Response.Status.OK).entity(response).build();
    }
}
