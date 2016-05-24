package com.itemstore.api;

import com.itemstore.api.response.ItemGroupsForUserResponse;
import com.itemstore.engine.FlowEngine;
import com.itemstore.commons.AsyncService;
import com.itemstore.engine.model.ItemGroup;
import com.itemstore.engine.model.tag.TagContainer;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path("/item")
public class Item {

    private static final Logger logger = Logger.getLogger(Item.class.getName());

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemGroupsForUser(@Context HttpServletRequest request,
                                         @PathParam("userId") String userId) {

        logger.fine("getItemGroupsForUser() userId =" + userId);


        List<ItemGroup> itemGroups = FlowEngine.getInstance().getItemGroupsForUser(userId);
        ItemGroupsForUserResponse response = ItemGroupsForUserResponse.createResponse(itemGroups);

        return Response.status(Response.Status.OK).entity(response).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemComponentsById(@Context HttpServletRequest request, String itemId) {

        //FIXME TODOD
        return Response.status(200).entity("").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchItemComponentsByTags(@Context HttpServletRequest request, List<String> tags) {
        //FIXME TODOD
        return Response.status(200).entity("").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchItemComponents(@Context HttpServletRequest request, String searchToken) {
        //FIXME TODOD
        return Response.status(200).entity("").build();
    }



    /*@POST
    @Path("/{itemId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(@Context HttpServletRequest request,
                             @PathParam("itemId") final String itemId, //FIXME ItemComponet object
                             @PathParam("userId") final String userId) {
*/
        /*final Application application = ApplicationFactory.instance().getApplication(applicationName);

        if (application == null) {
            return Response.status(404).entity(String.format("Invalid applicationName %s", applicationName)).build();
        }

        try {
            AsyncService.instance().submit(new Runnable() {
                @Override
                public void run() {
                    Model model = application.getModel();
                    model.toggleLikeItem(itemId, userId);
                    model.save();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(503).entity("Unable to save item like").build();   //FIXME
        }
*/
    //     return Response.status(200).build();
    // }

    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)  //APPLICATION_FORM_URLENCODED  //MULTIPART_FORM_DATA
    // JAXBElement<com.appback.model.Item> item
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //MultivaluedMap<String, String> formParams

    //  @Consumes(MediaType.APPLICATION_JSON)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(@Context HttpServletRequest request,
                            MultivaluedMap<String, String> parameters) {

        String title = parameters.getFirst("title");
        String description = parameters.getFirst("description");
        String imageURL1 = parameters.getFirst("imageURL1");
        String sourceURL = parameters.getFirst("sourceURL");
        String targetURL = parameters.getFirst("targetURL");
        String youTubeVideoID = parameters.getFirst("youTubeVideoID");
        String tagsParameter = StringUtils.trimToNull(parameters.getFirst("tags"));
        List<String> tags = Collections.emptyList();
        if (tagsParameter != null) {
            tags = Arrays.asList(StringUtils.split(tagsParameter, ","));
        }


        com.itemstore.engine.model.Item.Builder builder = new com.itemstore.engine.model.Item.Builder();
        builder.title(title).tags(TagContainer.create(tags))
                .description(description).imageURL1(imageURL1).
                        sourceURL(sourceURL).articleURL1(targetURL).youTubeVideoID(youTubeVideoID);

        final com.itemstore.engine.model.Item item = builder.build();

        //FIXME filter
        try {
            AsyncService.instance().submit(new Runnable() {
                @Override
                public void run() {
                    FlowEngine.getInstance().registerItem(item);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).build();
    }
}
