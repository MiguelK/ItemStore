package com.itemstore.api;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/tags")
public class Tag {

    private static final Logger LOG = Logger.getLogger(Tag.class.getName());


    @POST
    @Path("{deviceToken}/tags")
    public Response subscribeToTags(@PathParam("deviceToken") String deviceToken,
                                       @QueryParam("tags") String tags) {

        LOG.info("SUBSCRIBE: deviceToken=" + deviceToken + " subscribes to tags=" + tags);

       // Station station = Station.getStationById(subscriberStationId);

        // StationChannelKey channelGUID = StationChannelKey.parse(stationId, channelId);

        //ChannelSubscriptionService.getInstance().addSubscriber(station.getId(), channelGUID);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{deviceToken}/tags")
    public Response unSubscribeFromTags(@PathParam("deviceToken") String deviceToken,
                                           @QueryParam("tags") String tags) {

        LOG.info("UNSUBSCRIBE: deviceToken=" + deviceToken  + ",tags=" + tags);

        //Station station = Station.getStationById(subscriberStationId);

        //StationChannelKey channelGUID = StationChannelKey.parse(stationId, channelId);

        //ChannelSubscriptionService.getInstance().removeSubscriber(station.getDeviceToken(),channelGUID);

        return Response.status(Response.Status.OK).build();
    }
}
