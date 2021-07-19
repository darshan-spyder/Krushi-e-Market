package com.example.navigationdraweractivity.ui.sellhistory;

public class SellHistoryModel {

    String CropCategory;
    String CropName;
    String CropQuantity;
    String CropPrice;
    String CropStatus;
    String UserUid;
    String SellKey;

    public SellHistoryModel(String cropCategory, String cropName, String cropQuantity, String cropPrice, String cropStatus, String userUid, String sellKey) {
        CropCategory = cropCategory;
        CropName = cropName;
        CropQuantity = cropQuantity;
        CropPrice = cropPrice;
        CropStatus = cropStatus;
        UserUid = userUid;
        SellKey = sellKey;
    }

    public String getCropStatus() {
        return CropStatus;
    }

    public void setCropStatus(String cropStatus) {
        CropStatus = cropStatus;
    }

    public String getUserUid() {
        return UserUid;
    }

    public void setUserUid(String userUid) {
        UserUid = userUid;
    }

    public String getSellKey() {
        return SellKey;
    }

    public void setSellKey(String sellKey) {
        SellKey = sellKey;
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
