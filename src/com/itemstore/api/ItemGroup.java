package com.itemstore.api;

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
    public Response searchItemGroups(@Context HttpServletRequest request,
                                     @QueryParam("excludeTagForest") String excludeTagForest,
                                     @QueryParam("favoriteTagForest") String favoriteTagForest,
                                     @QueryParam("skipItemGroupIds")List<Integer> skipItemGroupIds,
                                     @QueryParam("itemGroupIds") List<Integer> itemGroupIds,
                                     @QueryParam("maxResultSize") Integer maxResultSize) {

        //List<String> tags) FIXME etc more params  favoriteTagFilter, skipTagFilter
        //int resultSize =10
            //swe_sport_zlatan
           //swe_sport_fotboll_os_zlatan
            //eng_news
        List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        LOG.info("excludeTagForest=" + excludeTagForest);

        //List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemEngine.getInstance()
          //      .searchItemsByTags(Collections.singletonList("Nyheter")); //FIXME use TagForest

        ItemGroupResponse res = ItemGroupResponse.create(itemGroupGroups);

        return Response.status(Response.Status.OK).entity(res).build();
    }

   /* @GET
    @Path("/search/tag2/{tagName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchItemGroupsByTags(@Context HttpServletRequest request,
                                            @PathParam("tagName") String tagName) {

        List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemEngine.getInstance()
                .searchItemsByTags(Collections.singletonList(tagName));
        //List<String> tags) FIXME etc more params
        //int resultSize =10

    //    List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemStore.getInstance().searchItemGroups();//FIXME

        ItemGroupResponse res = ItemGroupResponse.create(itemGroupGroups);

        return Response.status(Response.Status.OK).entity(res).build();
    } */

   /* @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemGroupsById(@Context HttpServletRequest request,
                                      @QueryParam("itemGroupIds") List<Integer> itemGroupIds) {

        //alla goupId's //FIXME check id's
        List<com.itemstore.engine.model.ItemGroup> itemGroupGroups = ItemEngine.getInstance().getAllItemGroupsSortedByDate();

        ItemGroupResponse res = ItemGroupResponse.create(itemGroupGroups);

        return Response.status(200).entity(res).build();
    }*/

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

    /*@POST //FIXME Remove ????
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


        Item.Builder builder = new Item.Builder();
        builder.title(title).tags(TagContainer.create(tags))
                .description(description).imageURL1(imageURL1).
                sourceURL(sourceURL).targetURL(targetURL).youTubeVideoID(youTubeVideoID);

        final Item item = builder.build();

        //FIXME filter
        try {
            AsyncService.instance().submit(new Runnable() {
                @Override
                public void run() {
                    ItemEngine.getInstance().registerItem(item);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).build();
    }*/
}
