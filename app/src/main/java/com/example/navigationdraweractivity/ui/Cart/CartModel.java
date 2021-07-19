package com.example.navigationdraweractivity.ui.Cart;

public class CartModel {

    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String CropStatus;
    String CropKey;

    public CartModel(String cropCategory, String cropName, String cropQuantity, String cropPrice, String cropStatus, String cropKey) {
        CropCategory = cropCategory;
        CropName = cropName;
        CropQuantity = cropQuantity;
        CropPrice = cropPrice;
        CropStatus = cropStatus;
        CropKey = cropKey;
    }

    public String getCropKey() {
        return CropKey;
    }

    public void setCropKey(String cropKey) {
        CropKey = cropKey;
    }

    public String getCropCategory() {
        return CropCategory;
    }

    public void setCropCategory(String cropCategory) {
        CropCategory = cropCategory;
    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
    }

    public String getCropQuantity() {
        return CropQuantity;
    }

    public void setCropQuantity(String cropQuantity) {
        CropQuantity = cropQuantity;
    }

    public String getCropPrice() {
        return CropPrice;
    }

    public void setCropPrice(String cropPrice) {
        CropPrice = cropPrice;
    }

    public String getCropStatus() {
        return CropStatus;
    }

    public void setCropStatus(String cropStatus) {
        CropStatus = cropStatus;
    }
}
