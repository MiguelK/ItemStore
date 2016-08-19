package com.itemstore.engine.model.tag;
import java.util.Optional;

public interface TagStore {

     TagStore S = new TagStoreHardcoded();

    Optional<Tag> getTag(String tagName);


    static TagStore getInstnce(){
        return S;//new TagStoreHardcoded();
    }
}
