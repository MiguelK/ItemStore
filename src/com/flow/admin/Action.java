package com.flow.admin;

import com.flow.commons.JsonUtil;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {

    public String toJSON(HttpServletRequest request) {
        Object result = execute(request);
        return JsonUtil.toJson(result);
    }

    protected abstract Object execute(HttpServletRequest request);//FIXME add HttpServletRequest
}
