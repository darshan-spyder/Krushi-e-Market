package com.example.navigationdraweractivity.ui.price.Interface;



import com.example.navigationdraweractivity.ui.price.Model.ItemGroup;

import java.util.List;

public interface IFirebaseLoadListener {

    void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList);
    void onFirebaseLoadfailled(String message);

}
