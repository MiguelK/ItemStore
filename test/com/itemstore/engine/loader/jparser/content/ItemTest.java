package com.itemstore.engine.loader.jparser.content;

import com.itemstore.engine.model.Item;
import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagRoot;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemTest {

    private static final String VALID_ARTICLE_URL = "http://www.aftonbladet.se/nyheter/article21364551.ab";
    private static final String VALID_SOURCE_URL = "http://www.aftonbladet.se";
    private static final String VALID_IMAGE_URL = "https://static.doubleclick.net/dynamic/5/28939274/media-cdn.tripadvisor.com/media/photo-o/03/9b/2d/e3/madrid.jpg_1439490097255_madrid.jpg";


    /*@Test
    public void getTitle() {
        Item item = new Item.Builder().title(" The title ").itemTagTree(TagContainer.create(Collections.singletonList("Swe")))
                .description("some stuff...").targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Assert.assertEquals("The title", item.getTitle());
    }*/

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void build_invalid_1() {
        new Item.Builder().title(" ").build();
    }

   /* @Test(expectedExceptions = IllegalArgumentException.class)
    public void build_invalid_2() {
        new Item.Builder().title("Test ccc").description(null).build();
    }*/


   /* @Test(expectedExceptions = IllegalArgumentException.class)
    public void build_invalid_4() {
        new Item.Builder().imageURL1("http://www.dn.se").itemTagTree(TagContainer.create(Collections.singletonList("asa"))).build();
    }*/


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void create_no_tagtree() {
            Item build = new Item.Builder().title("Test ccc").targetURL("d.se").imageURL1("dn.se/image.jpg").build();
            Assert.fail(build.getDescription());
    }

    @Test
    public void same_title_and_targetURL_should_be_same_id() {
        ItemTagTree itemTagTree = new ItemTagTree.Builder(TagRoot.ENG_NEWS).build();

        Item a = new Item.Builder().title("Samma title").itemTagTree(itemTagTree).targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Item b = new Item.Builder().title("Samma title").itemTagTree(itemTagTree).targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();

        Assert.assertEquals(a.getId(), b.getId());
    }

    /*
    @Test
    public void same_title_and_targetURL_trim_should_be_same_id() {
        Item a = new Item.Builder().title("Samma title ").targetURL(" http://www.aftonbladet.se/nyheter/article22906939.ab ").build();
        Item b = new Item.Builder().title("Samma title").targetURL(" http://www.aftonbladet.se/nyheter/article22906939.ab").build();

        Assert.assertEquals(a.getId(), b.getId());
    }

    @Test
    public void equalItem_title_and_target_url() {
        Item item1 = new Item.Builder().title("Test ccc").itemTagTree(TagContainer.create(Arrays.asList("Swe", "Sport"))).description("some text").
                targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Item item2 = new Item.Builder().title("Test ccc").itemTagTree(TagContainer.create(Arrays.asList("Swe", "sport"))).
                targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").description("some stuff...").build();
        Assert.assertTrue(item1.equals(item2));

        Item item11 = new Item.Builder().title("EXTRA Redan kaos – nu varnas för ännu mer snö")
                .targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Item item22 = new Item.Builder().title("EXTRA Redan kaos – nu varnas för ännu mer snö")
                .targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Assert.assertTrue(item11.equals(item22));
        Set<Item> x = new HashSet<Item>();
        x.add(item11);
        x.add(item22);
        Assert.assertTrue(x.size()==1);

    }

    @Test
    public void trimField() {
        Item item = new Item.Builder().title(" a ").
                itemTagTree(TagContainer.create(Collections.singletonList("Swe " + " Sport " + " "))).
                        description(" a ").imageURL1(" http://www.dn.se/x.jpg ").
                        youTubeVideoID(" 1234 ").sourceURL(" http://www.dn.se ").targetURL(" http://www.dn.se ").build();

        Assert.assertEquals("a", item.getDescription());
        Assert.assertEquals("http://www.dn.se", item.getTargetURL());
        Assert.assertEquals("a", item.getTitle());
        Assert.assertEquals("http://www.dn.se/x.jpg", item.getImageURL1());
        Assert.assertEquals("1234", item.getYouTubeVideoID());
        Assert.assertEquals("http://www.dn.se", item.getSourceURL());
        Assert.assertNotNull(item.getPublishedDate());
    }

    @Test
    public void testCreatedDate() {
        Assert.assertNotNull(new Item.Builder().title(" Some test").
                targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build().getPublishedDate());
    }

    //@Test
    public void encodeTitle() {
        Assert.assertEquals("på Arga Klara",new Item.Builder().title(" pÃ¥ Arga Klara").build().getPublishedDate());

        Assert.assertEquals("Bokmäke och fika",new Item.Builder().title(" BokmÃ¤rke och fika").build().getPublishedDate());
    }

    @Test
    public void sortOnCreatedDate() throws InterruptedException {
        List<Item> items = new ArrayList<Item>();

        Item itemA = new Item.Builder().title(" A").targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Thread.sleep(2000);
        Item itemB = new Item.Builder().title(" B").targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();

        items.add(itemB);
        items.add(itemA);

        Assert.assertEquals("B", items.get(0).getTitle());

        Collections.sort(items, Item.PUBLISHED_DATE_SORTER);
        Assert.assertEquals("A", items.get(0).getTitle());
    }

    @Test
    public void toJson() {
        Item item = new Item.Builder().title(" The title ")
                .itemTagTree(TagContainer.create(Collections.singletonList("Swe"))).description("some stuff...").
                        targetURL("http://www.aftonbladet.se/nyheter/article22906939.ab").build();
        Assert.assertNotNull(JsonUtil.toJson(item));
    }*/

}