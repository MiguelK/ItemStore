package com.itemstore.api;

import com.itemstore.api.request.SearchItemGroupRequest;
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
import java.util.ArrayList;
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
                                     @QueryParam("excludeItemGroupIds") List<String> excludeItemGroupIds,
                                     @QueryParam("itemGroupIds") List<Integer> itemGroupIds,
                                     @QueryParam("maxResultSize") Integer maxResultSize) {

        SearchItemGroupRequest request = SearchItemGroupRequest.create(excludeTagFilter, favoriteTagFilter, excludeItemGroupIds,
                itemGroupIds, maxResultSize);

        try {
            request.validate();
        } catch (SearchItemGroupRequest.InvalidRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        //1# if itemGroupIds>0 return only theese itemGroups
        //TagFilter f = TagForest.create(excludeTagFilter)
        //TagFilter a = TagForest.create(favoriteTagFilter)

        //List<String> tags) FIXME etc more params  favoriteTagFilter, skipTagFilter
        //int resultSize =10
        //swe_sport_zlatan
        //swe_sport_fotboll_os_zlatan
        //eng_news
        List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate();
        List<com.itemstore.engine.model.ItemGroup> filtered = new ArrayList<>();

        if(excludeItemGroupIds!=null && excludeItemGroupIds.size()>0){
            for (com.itemstore.engine.model.ItemGroup itemGroupGroup : itemGroupGroups) {
                if(excludeItemGroupIds.contains(itemGroupGroup.getItems().get(0).getId())){ //FIXME
                    continue;
                }
                filtered.add(itemGroupGroup);
            }
        } else {
            filtered.addAll(itemGroupGroups);
        }

        //List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemEngine.getInstance()
          //      .searchItemsByTags(Collections.singletonList("Nyheter")); //FIXME use TagForest

        ItemGroupResponse response = ItemGroupResponse.create(filtered);

        if (excludeItemGroupIds != null) {
            LOG.info("excludeItemGroupIds=" + excludeItemGroupIds.size());
        }

        return Response.status(Response.Status.OK).entity(response).build();
    }
}
