package com.oleg4789.hidroponicstore.domain;

public class StorageItem {
    private int storageItemId;
    private Product product;// должно ли так быть ?
    private int amount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStorageItemId() {
        return storageItemId;
    }

    public void setStorageItemId(int storageItemId) {
        this.storageItemId = storageItemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
