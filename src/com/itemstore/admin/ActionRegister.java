package com.itemstore.admin;

import com.itemstore.admin.impl.ItemGetItemGroupsAction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionRegister {

    private static final Logger LOG = Logger.getLogger(ActionRegister.class.getName());

    private static final ActionRegister INSTANCE = new ActionRegister();

    private ActionRegister() {
    }

    public static ActionRegister getInstance() {
        return INSTANCE;
    }

    public Action getAction(String action) {
        String className = ItemGetItemGroupsAction.class.getPackage().getName() + "." + action;

        try {
            Class aClass = Class.forName(className);
            return (Action) aClass.newInstance();
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Unable to fins action " + action);
            throw new RuntimeException(e);
        }
    }
}
