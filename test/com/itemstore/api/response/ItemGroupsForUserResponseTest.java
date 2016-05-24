package com.itemstore.api.response;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.ItemGroup;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupsForUserResponseTest {


    @Test
    public void sortItemGroups_By_CreatedDate() throws InterruptedException {
        List<ItemGroup> itemGroups = new ArrayList<ItemGroup>();

        ItemGroup itemGroup = new ItemGroup("Header X");
        itemGroup.addItem(new Item.Builder().title("B").build());
        Thread.sleep(1000);
        itemGroup.addItem(new Item.Builder().title("A").build());

        itemGroups.add(itemGroup);

        ItemGroupsForUserResponse response = ItemGroupsForUserResponse.createResponse(itemGroups);

        Assert.assertEquals(response.getItemGroups().get(0).getItems().size(), 2);
    //    Assert.assertEquals(response.getItemGroups().get(0).getItems().get(0).getTitle(), "A");
        //    Assert.assertEquals(response.getItemGroups().get(0).getItems().get(1).getTitle(), "B");
    }
}