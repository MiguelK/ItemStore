package com.itemstore.admin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        //ChannelDTO has no getter/setters
        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public String toJSON(HttpServletRequest request) {
        Object result = execute(request);
        try {
            return OBJECT_MAPPER.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object execute(HttpServletRequest request);//FIXME add HttpServletRequest
}
