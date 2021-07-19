package com.example.navigationdraweractivity.ui.price.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdraweractivity.R;
import com.example.navigationdraweractivity.ui.price.Model.ItemData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    private final Context context;
    private final List<ItemData> itemDataList;

    public MyItemAdapter(Context context, List<ItemData> itemDataList){
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        myViewHolder.txt_item_price.setText("₹"+itemDataList.get(i).getPrice()+"");
        Picasso.get().load(itemDataList.get(i).getImage()).into(myViewHolder.img_item);

        myViewHolder.linearLayout.setOnClickListener(view ->
                Toast.makeText(context, ""+itemDataList.get(i).getName()+"\n"+"₹"+itemDataList.get(i).getPrice()+"", Toast.LENGTH_SHORT).show());
//        {
//            @Override
//            public void OnItemClickListener(View view, int position) {
//                Toast.makeText(context, ""+itemDataList.get(i).getName()+"\n"+"₹"+itemDataList.get(i).getPrice()+"/KG", Toast.LENGTH_SHORT).show();
//
//
//            }
//
//            @Override
//            public void onItemClickListener(View view, int position) {
//                Toast.makeText(context, ""+itemDataList.get(i).getName()+"\n"+"₹"+itemDataList.get(i).getPrice()+"/KG", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (itemDataList != null ? itemDataList.size() : 0 );
    }
//implements View.OnClickListener
    public static class MyViewHolder extends RecyclerView.ViewHolder  {
       TextView txt_item_title;
       TextView txt_item_price;
       ImageView img_item;
       LinearLayout linearLayout;

//       IItemClickListener iItemClickListener;
//
//       public void setiItemClickListener(IItemClickListener iItemClickListener){
//           this.iItemClickListener = iItemClickListener;
//       }

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           txt_item_title = itemView.findViewById(R.id.tvTitle);
           txt_item_price = itemView.findViewById(R.id.tvPrice);
           img_item = itemView.findViewById(R.id.itemImage);
           linearLayout = itemView.findViewById(R.id.linear123);

           //itemView.setOnClickListener(this);
       }

//       @Override
//       public void onClick(View view) {
//           iItemClickListener.onItemClickListener(view,getAdapterPosition());
//
//       }
   }


}
