package com.oleg4789.hidroponicstore.domain;

import java.util.List;

public class Storage {
    private int storageId;// если склад один ему наверное не нужно имя и id и сушьность в бд под него не нужна
    private String name;
    private List<StorageItem> storageItems;

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StorageItem> getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(List<StorageItem> storageItems) {
        this.storageItems = storageItems;
    }
}
