package com.itemstore.engine;

public class BasicIndexBuilderTest {

   /* @Test
    public void buildIndex_3_composite_exclude_eng() {
        List<Item> items = new ArrayList<Item>();

        items.add(new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test DUPLICATE").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test xbbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Eng", "Sport"))).build());

        User user1 = new User();
        user1.addExcludeTag("Eng");

        BasicIndexBuilder indexBuilder = new BasicIndexBuilder(items);

        Map<String, BasicIndexBuilder.UserResult> userItems = indexBuilder.buildIndexForUsers().getUserItems();
        Assert.assertEquals(userItems.get(user1.getId()).getItemGroups().size(), 3);
    }

    @Test
    public void buildIndex_2_composite() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some stuff...").build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport", "Mode"))).description("bara bra").build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport", "Mode"))).description("bara bra").build());

        User user1 = new User();
        user1.addExcludeTag("Eng");

        BasicIndexBuilder indexBuilder = new BasicIndexBuilder(items);

        Map<String, BasicIndexBuilder.UserResult> userItems = indexBuilder.buildIndexForUsers().getUserItems();

        Assert.assertEquals(userItems.get(user1.getId()).getItemGroups().size(), 2);
    }

    @Test
    public void addReceivedItems_1() {
        Item item = new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").build();
        List<Item> items = Collections.singletonList(item);

        User user = new User();

        Map<String, BasicIndexBuilder.UserResult> userItems = new BasicIndexBuilder(items)
                .buildIndexForUsers().getUserItems();
        Assert.assertEquals(userItems.get(user.getId()).getItemGroups().size(), 1);

        user.addReceivedItems(Collections.singletonList(item.getId()));

        userItems = new BasicIndexBuilder(items)
                .buildIndexForUsers().getUserItems();
        Assert.assertNull(userItems.get(user.getId()));
    }

    @Test
    public void topNews_should_be_sorted_by_createdDate() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item.Builder().title("Test aaa").tags(TagContainer.create(Arrays.asList("Swe", "Sport"))).build());
        items.add(new Item.Builder().title("Test ssss").tags(TagContainer.create(Arrays.asList("Swe", "News"))).build());
        items.add(new Item.Builder().title("Test bbb").tags(TagContainer.create(Arrays.asList("Swe", "Sport", "Mode"))).build());


        BasicIndexBuilder indexBuilder = new BasicIndexBuilder(items);

        Map<String, BasicIndexBuilder.UserResult> userItems = indexBuilder.buildIndexForUsers().getUserItems();

        List<ItemGroup> itemGroups = userItems.get("id_1").getItemGroups();

        Assert.assertEquals(itemGroups.size(), 3);
    }*/
}