package com.itemstore.api.request;

import com.itemstore.commons.StringUtil;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRequest {

    @FormParam("firstName")
    private String firstName;

    @FormParam("userId")
    private String userId;

    @FormParam("excludeTags")
    private String excludeTags;

    @FormParam("favoriteTags")
    private String favoriteTags;

    @FormParam("receivedItems")
    public String receivedItems;

    public UserRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public List<String> getReceivedItems() {
        return StringUtil.split(receivedItems);
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getExcludeTags() {
        return StringUtil.split(excludeTags);
    }

    public List<String> getFavoriteTags() {
        return StringUtil.split(favoriteTags);
    }
}
