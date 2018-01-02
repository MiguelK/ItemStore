package com.itemstore.api;

import com.itemstore.api.response.dto.ItemGroupResponse;
import com.itemstore.engine.ItemEngine;
import com.pusher.ChannelSubscriptionService;


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

@Path("/game")
public class Game {

    private static final Logger LOG = Logger.getLogger(ItemGroup.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response push(@Context HttpServletRequest httpServletRequest) {

        LOG.info("test create push message");

        ChannelSubscriptionService.getInstance().notifySubscribers("match1");


        return Response.status(Response.Status.OK).build();
    }
}

