package com.itemstore.api;

import com.itemstore.api.response.dto.ItemGroupResponse;
import com.itemstore.engine.ItemEngine;
import com.pusher.ChannelSubscriptionService;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/match")
public class Match {

    private static final Logger LOG = Logger.getLogger(ItemGroup.class.getName());

    @POST
    @Path("/create")
    public Response create(@Context HttpServletRequest request,
                                @PathParam("name") String name) {
    //FIXME add description, image, date

        String unigueMatchId = "77";

        //FIXME ChannelSubscriptionService

        return Response.status(Response.Status.OK).entity(unigueMatchId).build();
    }

    @POST
    @Path("/subscribe/{matchId}")
    public Response subscribe(@Context HttpServletRequest request,
                              @PathParam("matchId") String matchId,
                              @QueryParam("deviceToken") String deviceToken) {
        //FIXME add description, image, date

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/update/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMatchResult(@PathParam("matchId") String matchId,
                                      @QueryParam("matchResult") String matchResult) {


        LOG.info("updateMatchResult() matchId=" + matchId + ",matchResult=" + matchResult);

        ChannelSubscriptionService.getInstance().notifySubscribers("match1");

        return Response.status(Response.Status.OK).build();
    }

     /*   @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response push(@Context HttpServletRequest httpServletRequest) {

        LOG.info("test create push message");

        ChannelSubscriptionService.getInstance().notifySubscribers("match1");


        return Response.status(Response.Status.OK).build();
    } */
}

