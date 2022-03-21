package com.example.automatedwarehouseinventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private List<Item> itemList;
    private LayoutInflater mInflater;
    Context context;

    InventoryAdapter(Context context, List<Item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.itemList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item tempItem = itemList.get(position);
        holder.itemNameTextView.setText("" + tempItem.getItemName());
        holder.itemMinimumTextView.setText("" + tempItem.getItemMinStock());
        holder.itemInStockTextView.setText("" + tempItem.getItemInStock());
        holder.itemInStoreTextView.setText("" + tempItem.getItemInStore());
        holder.itemOrderedTextView.setText("" + tempItem.itemOrderedStock);
        holder.itemDeficitTextView.setText("" + tempItem.getItemDeficitStock());
        if(tempItem.itemPictureId != -1){
            holder.itemImageView.setImageResource(tempItem.itemPictureId);
        }
        holder.itemDescriptionTextView.setText(tempItem.getItemShortDesc());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView, itemDescriptionTextView, itemMinimumTextView, itemInStockTextView, itemInStoreTextView, itemOrderedTextView, itemDeficitTextView;
        ImageView itemImageView;

        ViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemDescriptionTextView = itemView.findViewById(R.id.practicalDescTextView);
            itemMinimumTextView = itemView.findViewById(R.id.itemMinimum);
            itemInStockTextView = itemView.findViewById(R.id.itemInStock);
            itemInStoreTextView = itemView.findViewById(R.id.itemInStore);
            itemOrderedTextView = itemView.findViewById(R.id.itemOrdered);
            itemDeficitTextView = itemView.findViewById(R.id.itemDeficit);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            itemView.setOnClickListener(view -> {
                ((MainActivity) context).tempItem = itemList.get(getAdapterPosition());
                ((MainActivity) context).itemIndex = getAdapterPosition();
                ((MainActivity) context).startEditNewItemActivity();

            });
        }

    }
}