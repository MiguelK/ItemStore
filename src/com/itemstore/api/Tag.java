package com.itemstore.api;


import com.itemstore.api.response.TagTreeResponse;
import com.itemstore.model.tag.TagContainer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tag")
public class Tag {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagTree(@Context HttpServletRequest request) {
        TagTreeResponse response = TagTreeResponse.create(TagContainer.getLanguageTags(),
                TagContainer.getSecondLevelTagsForLanguageCategory(),
                TagContainer.getFirstLevelTagsByLanguageKey());

        return Response.status(200).entity(response).build();
    }
}
