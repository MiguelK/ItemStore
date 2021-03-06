package com.itemstore.admin.dto;

import java.util.ArrayList;
import java.util.List;

public class TagDTO {
    private String name;

    public static Object map(List<String> tags) {

        List<TagDTO> result = new ArrayList<TagDTO>();

        for (String tag : tags) {
            TagDTO t = new TagDTO();
            t.name = tag;
            result.add(t);
        }

        return result;
    }
}
