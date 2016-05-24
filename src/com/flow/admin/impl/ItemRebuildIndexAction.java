package com.flow.admin.impl;

import com.flow.engine.FlowEngine;
import com.flow.admin.Action;
import com.google.common.base.Stopwatch;

import javax.servlet.http.HttpServletRequest;

public class ItemRebuildIndexAction extends Action {


    @Override
    protected Object execute(HttpServletRequest request) {

        Stopwatch started = Stopwatch.createStarted();

        FlowEngine.getInstance().rebuildIndex();

        return started.stop().toString();

    }
}
