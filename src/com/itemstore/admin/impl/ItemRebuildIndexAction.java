package com.itemstore.admin.impl;

import com.itemstore.engine.ItemEngine;
import com.itemstore.admin.Action;
import com.google.common.base.Stopwatch;

import javax.servlet.http.HttpServletRequest;

public class ItemRebuildIndexAction extends Action {


    @Override
    protected Object execute(HttpServletRequest request) {

        Stopwatch started = Stopwatch.createStarted();

        ItemEngine.getInstance().rebuildIndex();

        return started.stop().toString();

    }
}
