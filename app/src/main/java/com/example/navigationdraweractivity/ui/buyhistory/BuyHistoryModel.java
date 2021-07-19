package com.example.navigationdraweractivity.ui.buyhistory;

public class BuyHistoryModel {

    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String UserUid;
    String BuyKey;

    public BuyHistoryModel(String cropCategory, String cropName, String cropQuantity, String cropPrice, String userUid, String buyKey) {
        CropCategory = cropCategory;
        CropName = cropName;
        CropQuantity = cropQuantity;
        CropPrice = cropPrice;
        UserUid = userUid;
        BuyKey = buyKey;
    }

    public String getUserUid() {
        return UserUid;
    }

    public void setUserUid(String userUid) {
        UserUid = userUid;
    }

    public String getBuyKey() {
        return BuyKey;
    }

    public void setSellKey(String sellKey) {
        BuyKey = sellKey;
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
}

