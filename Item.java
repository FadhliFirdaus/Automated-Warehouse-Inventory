package com.example.automatedwarehouseinventory;

import java.io.Serializable;
import java.util.Locale;
import java.util.Random;

public class Item {
    String itemName, itemId, itemMetrics, itemShortDesc;
    int itemQuantity, itemInStock, itemMinStock, itemDeficitStock, itemPictureId, itemInStore,itemThresholdPercent, globalThreshold, itemOrderedStock;
    boolean overThreshold;

    public Item(String name, String itemMetrics, int itemInStock, int itemMinStock, int itemInStore, int itemOrderedStock,  int itemPictureId) {
        this.itemName = name;
        this.itemMetrics = itemMetrics;
        this.itemInStock = itemInStock;
        this.itemMinStock = itemMinStock;
        this.itemInStore = itemInStore;
        this.itemOrderedStock = itemOrderedStock;
        this.itemPictureId = itemPictureId;
        RandomString random = new RandomString(10);
        this.itemId = random.nextString();
        itemDeficitStock = itemMinStock - (itemInStore + itemOrderedStock + itemInStock);
        itemDeficitStock = itemMinStock - (itemInStore + itemInStock + itemOrderedStock);
        itemThresholdPercent = itemDeficitStock/itemMinStock * 100;
        if(itemThresholdPercent > globalThreshold){
            overThreshold = true;
            itemShortDesc = "Item is in dire need of restocking";
        }

    }

    public String getItemShortDesc() {
        return itemShortDesc;
    }

    public int getItemOrderedStock() {
        return itemOrderedStock;
    }

    public void setItemShortDesc(String itemShortDesc) {
        this.itemShortDesc = itemShortDesc;
    }

    public int isItemOrderedStock() {
        return itemOrderedStock;
    }

    public void setItemOrderedStock(int itemOrderedStock) {
        this.itemOrderedStock = itemOrderedStock;
    }


    public int getItemPictureId() {
        return itemPictureId;
    }

    public void setItemPictureId(int itemPictureId) {
        this.itemPictureId = itemPictureId;
    }

    public int getItemInStore() {
        return itemInStore;
    }

    public void setItemInStore(int itemInStore) {
        this.itemInStore = itemInStore;
    }

    public int getItemThresholdPercent() {
        return itemThresholdPercent;
    }

    public void setItemThresholdPercent(int itemThresholdPercent) {
        this.itemThresholdPercent = itemThresholdPercent;
    }

    public int getGlobalThreshold() {
        return globalThreshold;
    }

    public void setGlobalThreshold(int globalThreshold) {
        this.globalThreshold = globalThreshold;
    }

    public boolean isOverThreshold() {
        return overThreshold;
    }

    public void setOverThreshold(boolean overThreshold) {
        this.overThreshold = overThreshold;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemMetrics() {
        return itemMetrics;
    }

    public void setItemMetrics(String itemMetrics) {
        this.itemMetrics = itemMetrics;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemInStock() {
        return itemInStock;
    }

    public void setItemInStock(int itemInStock) {
        this.itemInStock = itemInStock;
    }

    public int getItemMinStock() {
        return itemMinStock;
    }

    public void setItemMinStock(int itemMinStock) {
        this.itemMinStock = itemMinStock;
    }

    public int getItemDeficitStock() {
        return itemDeficitStock;
    }

    public void setItemDeficitStock(int itemDeficitStock) {
        this.itemDeficitStock = itemDeficitStock;
    }
}
