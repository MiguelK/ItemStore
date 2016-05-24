package com.flow.engine;

import com.flow.commons.JsonUtil;
import com.flow.engine.model.ItemGroup;
import com.flow.engine.loader.rss.ChannelLoader;
import com.flow.engine.model.Item;
import com.flow.engine.FlowEngine;
import com.flow.engine.loader.ItemLoader;

import com.flow.engine.model.User;
import com.flow.engine.loader.UserLoader;
import com.flow.engine.model.tag.TagContainer;
import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class FlowFlowEngineTest {


    @BeforeMethod
    public void setUp() throws Exception {
        FlowEngine.getInstance().init(ChannelLoader.create(), new UserLoader(), new ItemLoader()); //FIXME
    }

    /*
    @Test
    public void getItemComponentsForUser_3_ItemComponent_3Items() {

        List<String> filter =Arrays.asList("Swe","Sport");
        User user = new User();
        user.addExcludeTag("Eng");

        Item itemComponent1_item1 = new Item.Builder().
                sourceURL("aftonbladet.se").
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                tags(TagContainer.create(filter)).
                        title("Han döms för brutala nyårsmordet").
                        description("JFohnny Rintanen, 40, döms för det blodiga mordet i Sala natten mot nyårsafton.").
                        articleURL1("http://www.aftonbladet.se/nyheter/article21013527.ab").build();

        Item itemComponent1_item2 = new Item.Builder().
                itemGroupId(itemComponent1_item1.getItemGroupId()).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                sourceURL("arbetarbladet.se").
                tags(TagContainer.create(filter)).
                        title("Nekar till mord – hävdar nödvärn").
                        description("Misstänkte mördaren Johnny Rintanen uppger att han agerade i nödvärn.\n" +
                                "På mordkvällen var han alkoholpåverkad och hade tagit tabletter när en konflikt uppstod med det 49-åriga offret.").
                        articleURL1("http://www.arbetarbladet.se/blaljus/brott/nekar-till-mord-havdar-nodvarn").build();

        Item itemComponent1_item3 = new Item.Builder().
                itemGroupId(itemComponent1_item1.getItemGroupId()).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                sourceURL("arbetarbladet.se").
                tags(TagContainer.create(filter)).
                        title("Nekar till mord – hävdar nödvärn").
                        description("Misstänkte mördaren Johnny Rintanen uppger att han agerade i nödvärn.\n" +
                                "På mordkvällen var han alkoholpåverkad och hade tagit tabletter när en konflikt uppstod med det 49-åriga offret.").
                        articleURL1("http://www.arbetarbladet.se/blaljus/brott/nekar-till-mord-havdar-nodvarn").build();

        //ItemComponent_2
        Item itemComponent2_item1 = new Item.Builder().
                sourceURL("aftonbladet.se").
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                tags(TagContainer.create(filter)).
                        title("Han döms för brutala nyårsmordet").
                        description("Johnny Rintanen, 40, döms för det blodiga mordet i Sala natten mot nyårsafton.").
                        articleURL1("http://www.aftonbladet.se/nyheter/article21013527.ab").build();

        Item itemComponent2_item2 = new Item.Builder().
                itemGroupId(itemComponent2_item1.getItemGroupId()).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                sourceURL("arbetarbladet.se").
                tags(TagContainer.create(filter)).
                        title("Nekar till mord – hävdar nödvärn").
                        description("Misstänkte mördaren Johnny Rintanen uppger att han agerade i nödvärn.\n" +
                                "På mordkvällen var han alkoholpåverkad och hade tagit tabletter när en konflikt uppstod med det 49-åriga offret.").
                        articleURL1("http://www.arbetarbladet.se/blaljus/brott/nekar-till-mord-havdar-nodvarn").build();

        Item itemComponent2_item3 = new Item.Builder().
                itemGroupId(itemComponent2_item1.getItemGroupId()).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                sourceURL("arbetarbladet.se").
                tags(TagContainer.create(filter)).
                        title("Nekar till mord – hävdar nödvärn").
                        description("Misstänkte mördaren Johnny Rintanen uppger att han agerade i nödvärn.\n" +
                                "På mordkvällen var han alkoholpåverkad och hade tagit tabletter när en konflikt uppstod med det 49-åriga offret.").
                        articleURL1("http://www.arbetarbladet.se/blaljus/brott/nekar-till-mord-havdar-nodvarn").build();

        //ItemComponent_3 (Video)
        Item itemComponent3_item1 = new Item.Builder().
                title("Some title").
                description("Soem descr").
                sourceURL("aftonbladet.se").
                youTubeVideoID("wTzR2mTEk-8").
                tags(TagContainer.create(filter)).build();

        Item itemComponent3_item2 = new Item.Builder().
                title("Some title").
                description("Soem descr").
                itemGroupId(itemComponent3_item1.getItemGroupId()).
                youTubeVideoID("4k6-b4SzX6o").
                tags(TagContainer.create(filter)).build();

        Item itemComponent3_item3 = new Item.Builder().
                title("Some title").
                description("Soem descr").
                itemGroupId(itemComponent3_item1.getItemGroupId()).
                youTubeVideoID("gn6A5nP3k_I").
                tags(TagContainer.create(filter)).build();

        FlowEngine.getInstance().registerItem(itemComponent1_item1);
        FlowEngine.getInstance().registerItem(itemComponent1_item2);
        FlowEngine.getInstance().registerItem(itemComponent1_item3);

        FlowEngine.getInstance().registerItem(itemComponent2_item1);
        FlowEngine.getInstance().registerItem(itemComponent2_item2);
        FlowEngine.getInstance().registerItem(itemComponent2_item3);

        FlowEngine.getInstance().registerItem(itemComponent3_item1);
        FlowEngine.getInstance().registerItem(itemComponent3_item2);
        FlowEngine.getInstance().registerItem(itemComponent3_item3);

        FlowEngine.getInstance().start();

        // Assert.assertEquals(3,FlowEngine.getInstance().getItemComponentsForUser(user.getId()).size());
        // FlowEngine.getInstance().getItemGroupsForUser()
        //#2 Search and get json data from FlowEngine

        List<ItemGroup> itemGroupsForUser = FlowEngine.getInstance().getItemGroupsForUser(user.getId());//asJson()
        String json = JsonUtil.toJson(itemGroupsForUser);
        System.out.println("Result = " + json); //itemA1 and itemA2 as composite (Both has swe tag and same itemGroupId)

        Assert.assertFalse(json.contains("tagId"));
        //3# update FlowEngine add,remove, reindex etc..

    }
*/
    /*@Test
    public void getItemComponentsForUser_1_ItemComponent_2Items() {
        List<String> filter = Arrays.asList("Swe","Sport");

        User user = new User();
        user.addExcludeTag("Eng");

        String itemGroupId ="xxx9999";
        Item item1 = new Item.Builder().
                sourceURL("aftonbladet.se").
                itemGroupId(itemGroupId).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                tags(TagContainer.create(filter)).
                title("Han döms för brutala nyårsmordet").
                description("Johnny Rintanen, 40, döms för det blodiga mordet i Sala natten mot nyårsafton.").
                articleURL1("http://www.aftonbladet.se/nyheter/article21013527.ab").build();

        Item item2 = new Item.Builder().
                itemGroupId(itemGroupId).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                sourceURL("arbetarbladet.se").
                tags(TagContainer.create(filter)).
                        title("Nekar till mord – hävdar nödvärn").
                        description("Misstänkte mördaren Johnny Rintanen uppger att han agerade i nödvärn.\n" +
                                "På mordkvällen var han alkoholpåverkad och hade tagit tabletter när en konflikt uppstod med det 49-åriga offret.").
                        articleURL1("http://www.arbetarbladet.se/blaljus/brott/nekar-till-mord-havdar-nodvarn").build();


        FlowEngine.getInstance().registerItem(item1);
        FlowEngine.getInstance().registerItem(item2);

        FlowEngine.getInstance().rebuildIndex();

        Assert.assertEquals(1, FlowEngine.getInstance().getItemComponentsForUser(user.getId()).size());

        List<ItemGroup> itemGroupsForUser = FlowEngine.getInstance().getItemGroupsForUser(user.getId());

        Assert.assertFalse(JsonUtil.toJson(itemGroupsForUser).contains("tagId"));
    }
*/
   /* @Test
    public void sortByDate() {

        User user = new User();
        String itemGroupId ="xxx9999";
        Item item1 = new Item.Builder().
                sourceURL("aftonbladet.se").
                itemGroupId(itemGroupId).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                tags(TagContainer.create(Arrays.asList("Swe","Sport"))).
                title("Han döms för brutala nyårsmordet").
                description("Johnny Rintanen, 40, döms för det blodiga mordet i Sala natten mot nyårsafton.").
                articleURL1("http://www.aftonbladet.se/nyheter/article21013527.ab").build();

        Item item2 = new Item.Builder().
                itemGroupId(itemGroupId).
                imageURL1("http://gfx.aftonbladet-cdn.se/image/21013849/258/normal/25b1f7760867f/Skärmavbild+2015-06-23+kl.+20.29.31.jpg").
                sourceURL("arbetarbladet.se").
                tags(TagContainer.create(Arrays.asList("Swe","Sport"))).
                title("Nekar till mord – hävdar nödvärn").
                description("Misstänkte mördaren Johnny Rintanen uppger att han agerade i nödvärn.\n" +
                        "På mordkvällen var han alkoholpåverkad och hade tagit tabletter när en konflikt uppstod med det 49-åriga offret.").
                articleURL1("http://www.arbetarbladet.se/blaljus/brott/nekar-till-mord-havdar-nodvarn").build();


        FlowEngine.getInstance().registerItem(item1);
        FlowEngine.getInstance().registerItem(item2);

        FlowEngine.getInstance().rebuildIndex();

        Assert.assertEquals(1, FlowEngine.getInstance().getItemComponentsForUser(user.getId()).size());

        List<ItemGroup> itemGroupsForUser = FlowEngine.getInstance().getItemGroupsForUser(user.getId());

        Assert.assertFalse(JsonUtil.toJson(itemGroupsForUser).contains("tagId"));
    } */
}