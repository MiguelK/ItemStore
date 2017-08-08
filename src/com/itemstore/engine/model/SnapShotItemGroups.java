package com.itemstore.engine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SnapShotItemGroups implements Serializable {

    private List<ItemGroup> allItemGroupsSortedByDate = new ArrayList<>();

    private final String createdDate;

    public SnapShotItemGroups(List<ItemGroup> allItemGroupsSortedByDate) {
        this.allItemGroupsSortedByDate = new ArrayList<>(allItemGroupsSortedByDate);
        createdDate = "2017-12-12:21:22"; //FIXME
    }

    public List<ItemGroup> getAllItemGroupsSortedByDate() {
        return allItemGroupsSortedByDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}
