package com.itemstore.engine;


import com.itemstore.engine.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class ItemStore {
    private static final ItemStore INSTANCE = new ItemStore();

    public static ItemStore getInstance(){
        return INSTANCE;
    }

    public List<ItemGroup> searchItemGroups() {
        return new ArrayList<ItemGroup>();
    }

    //FIXME get/serch methods only

}
