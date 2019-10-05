package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context context;
    private List<Items> list;
    public ItemsAdapter(Context context,List list){
        this.context= context;
        this.list = list;
    }
    @NonNull
    @Override
    public ItemsAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items, null);
        return new ItemsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsViewHolder itemViewHolder, int i) {
        itemViewHolder.progressBar.setVisibility(View.VISIBLE);
        Items items = list.get(i);
        Glide.with(context).load(items.getUrl()).into(itemViewHolder.item_image);
        itemViewHolder.item_name.setText(items.getName());
        itemViewHolder.item_price.setText("Rs "+items.getPrice()+".00");
        itemViewHolder.linearLayout.setOnClickListener(v-> Toast.makeText(context,items.getName(),Toast.LENGTH_SHORT).show());
        itemViewHolder.progressBar.setVisibility(View.GONE);
    }
    @Override
    public int getItemCount() { return list.size(); }
    class ItemsViewHolder extends RecyclerView.ViewHolder{
        TextView item_name,item_price;
        LinearLayout linearLayout;
        ImageView item_image;
        ProgressBar progressBar;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
            linearLayout = itemView.findViewById(R.id.pendrive);
            item_image = itemView.findViewById(R.id.image);
            item_name = itemView.findViewById(R.id.name);
            item_price = itemView.findViewById(R.id.price);
        }
    }
}
