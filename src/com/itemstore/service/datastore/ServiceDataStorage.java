package com.itemstore.service.datastore;

import com.itemstore.engine.model.SnapShotItemGroups;

import java.io.File;
import java.util.Optional;

public interface ServiceDataStorage {

    static ServiceDataStorage useDefault() {
        return new ServiceDataStorageDisk();
    }

    void save(SnapShotItemGroups snapShotItemGroups);

    File getPodDataHomeDir();

    Optional<SnapShotVersion> getCurrentVersion();
}
