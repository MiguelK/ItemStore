package com.itemstore.api.response.dto;

import com.itemstore.engine.model.Item;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ItemDTO implements Serializable {
    private String title;
    private String description;
    private String imageURL1;
    private String imageURL2;
    private String youTubeVideoID;
    private String targetURL;
    private String sourecURL;

    private Date publishedDate;
    private String publishedDateFormated;

    private String id;
    private List<String> tags = new ArrayList<String>();

    public static ItemDTO createResponse(Item item) {
        ItemDTO itemResponseDTO = new ItemDTO();
        itemResponseDTO.title = item.getTitle();
        itemResponseDTO.description = item.getDescription();
        itemResponseDTO.imageURL1 = item.getImageURL1();
        itemResponseDTO.imageURL2 = item.getImageURL2();
        itemResponseDTO.sourecURL = item.getSourceURL();
        itemResponseDTO.publishedDate = item.getPublishedDate();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        itemResponseDTO.publishedDateFormated = formatter.format(item.getPublishedDate()); //FIXME perf
        itemResponseDTO.targetURL = item.getTargetURL();
        itemResponseDTO.youTubeVideoID = item.getYouTubeVideoID();
        itemResponseDTO.id = item.getId();
      //  itemResponseDTO.tags.addAll(item.getTags()); //FIXME from/to client/server tagTree

        return itemResponseDTO;
    }

    public static List<ItemDTO> createResponse(List<Item> items) {
        List<ItemDTO> responses = new ArrayList<ItemDTO>();
        for (Item item : items) {
            responses.add(ItemDTO.createResponse(item));
        }

        return responses;
    }

    public String getPublishedDateFormated() {
        return publishedDateFormated;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL1() {
        return imageURL1;
    }

    public String getImageURL2() {
        return imageURL2;
    }

    public String getYouTubeVideoID() {
        return youTubeVideoID;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public String getSourecURL() {
        return sourecURL;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }
}
