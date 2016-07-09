package com.itemstore.engine;

import com.itemstore.engine.model.tag3.ItemTagTree;
import com.itemstore.engine.model.tag3.TagRoot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupSorterTest {

    @Test
    public void sort_every_other_extra_first() {

        //Sorted by data and filtered by max 5
        List<ItemGroupSortableMock> indexedItems = new ArrayList<>();
        indexedItems.addAll(create(TagRoot.SWE_DATA, 5));
        indexedItems.addAll(create(TagRoot.ENG_NEWS, 5));
        indexedItems.addAll(create(TagRoot.SWE_SPORT, 5));
        indexedItems.addAll(create(TagRoot.SWE_DATA, 2));
        indexedItems.addAll(create(TagRoot.SWE_SPORT, 3));
        indexedItems.addAll(create(TagRoot.SWE_NYHETER_EXTRA, 2));

        ItemGroupSorter itemGroupSorter = new ItemGroupSorter(indexedItems);
        //FIXME
        List<ItemGroupSortableMock> itemGroupSortables = (List<ItemGroupSortableMock>) itemGroupSorter.sort();

        Assert.assertEquals(itemGroupSortables.get(0).getTagRoot(), TagRoot.SWE_NYHETER_EXTRA);
        Assert.assertEquals(itemGroupSortables.get(1).getTagRoot(), TagRoot.SWE_NYHETER_EXTRA);

     //   Assert.assertEquals(itemGroupSortables.get(5).getTagRoot(), TagRoot.SWE_DATA);
        //   Assert.assertEquals(itemGroupSortables.get(5).getTagRoot(), TagRoot.SWE_DATA);


    }

    @Test
    public void sort_5_first_intact() {

        List<ItemGroupSortableMock> indexedItems = new ArrayList<>();
        ItemGroupSortableMock a = new ItemGroupSortableMock(ItemTagTree.create(TagRoot.SWE_DATA));
        indexedItems.add(a);
        ItemGroupSortableMock b = new ItemGroupSortableMock(ItemTagTree.create(TagRoot.SWE_DATA));
        indexedItems.add(b);
        ItemGroupSortableMock c = new ItemGroupSortableMock(ItemTagTree.create(TagRoot.SWE_DATA));
        indexedItems.add(c);
        ItemGroupSortableMock d = new ItemGroupSortableMock(ItemTagTree.create(TagRoot.SWE_SPORT));
        indexedItems.add(d);
        ItemGroupSortableMock e = new ItemGroupSortableMock(ItemTagTree.create(TagRoot.SWE_NYHETER));
        indexedItems.add(e);

        ItemGroupSorter itemGroupSorter = new ItemGroupSorter(indexedItems);
        List<? extends ItemGroupSortable> itemGroupSortables = itemGroupSorter.sort();

        Assert.assertEquals(itemGroupSortables.get(0), a);
        Assert.assertEquals(itemGroupSortables.get(1), b);
        Assert.assertEquals(itemGroupSortables.get(2), c);
        Assert.assertEquals(itemGroupSortables.get(3), d);
        Assert.assertEquals(itemGroupSortables.get(4), e);

    }

    private List<ItemGroupSortableMock> create(TagRoot tagRoot, int count) {

        List<ItemGroupSortableMock> sortableMocks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            sortableMocks.add(new ItemGroupSortableMock(ItemTagTree.create(tagRoot)));
        }

        return sortableMocks;
    }

    class ItemGroupSortableMock implements ItemGroupSortable {
        private ItemTagTree itemTagTree;

        public ItemGroupSortableMock(ItemTagTree itemTagTree) {
            this.itemTagTree = itemTagTree;
        }

        @Override
        public ItemTagTree getItemTagTree() {
            return itemTagTree;
        }

        public TagRoot getTagRoot() {
            return itemTagTree.getTagRoot();
        }
    }


}