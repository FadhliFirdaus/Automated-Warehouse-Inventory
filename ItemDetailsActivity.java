package com.example.automatedwarehouseinventory;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView cancel, add, itemMinTV, itemInStockTV, itemInStoreTV, itemOrderedTV,itemDeficitTV;
    EditText itemNameET, itemMetricsET;
    FloatingActionButton fabAddNewPicture;
    Button itemMinAdd, itemMinSubs, itemInStockAdd, itemInStockSubs, itemInStoreAdd, itemInStoreSubs, itemOrderedAdd, itemOrderedSubs;
    int pictureId = -1;
    ImageView itemImage;
    Item tempItem;
    Context context;

    public void setTempItem(Item tempItem) {
        this.tempItem = tempItem;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_barang);
        cancel = findViewById(R.id.cancelTextView);
        add = findViewById(R.id.addTextView);
        itemImage = findViewById(R.id.pictureImageView);
        itemMinTV = findViewById(R.id.itemMinimumTV);
        itemInStockTV = findViewById(R.id.itemInStockTV);
        itemOrderedTV = findViewById(R.id.itemOrderedTV);
        itemDeficitTV = findViewById(R.id.itemDeficitTV);
        itemInStoreTV = findViewById(R.id.itemInStoreTV);
        itemNameET = findViewById(R.id.editTextItemName);
        itemMetricsET = findViewById(R.id.editTextItemMetrics);
        fabAddNewPicture = findViewById(R.id.fabAddPicture);
        itemMinAdd = findViewById(R.id.addNewItemFab);
        itemMinSubs = findViewById(R.id.itemMinSubs);
        itemInStockAdd = findViewById(R.id.itemInStockAdd);
        itemInStockSubs = findViewById(R.id.itemInStockSubs);
        itemInStoreAdd = findViewById(R.id.itemInStoreAdd);
        itemInStoreSubs = findViewById(R.id.itemInStoreSubs);
        itemOrderedAdd = findViewById(R.id.itemOrderedAdd);
        itemOrderedSubs = findViewById(R.id.itemOrderedSubs);
        itemMinAdd = findViewById(R.id.itemMinAdd);
        itemOrderedAdd = findViewById(R.id.itemOrderedAdd);
        itemInStockAdd = findViewById(R.id.itemInStockAdd);
        itemInStoreAdd = findViewById(R.id.itemInStoreAdd);
        cancel.setOnClickListener(view -> finish());
        itemMinAdd.setOnClickListener(addTextView(itemMinTV));
        itemOrderedAdd.setOnClickListener(addTextView(itemOrderedTV));
        itemInStoreAdd.setOnClickListener(addTextView(itemInStoreTV));
        itemInStockAdd.setOnClickListener(addTextView(itemInStockTV));
        itemMinSubs.setOnClickListener(subsTextView(itemMinTV));
        itemOrderedSubs.setOnClickListener(subsTextView(itemOrderedTV));
        itemInStockSubs.setOnClickListener(subsTextView(itemInStockTV));
        itemInStoreSubs.setOnClickListener(subsTextView(itemInStoreTV));
        fabAddNewPicture.setOnClickListener(view -> addNewPicture());
        bulkSet(itemInStockTV);
        bulkSet(itemDeficitTV);
        bulkSet(itemInStoreTV);
        bulkSet(itemMinTV);
        bulkSet(itemOrderedTV);
        bulkSet(add);
        Intent intent = getIntent();
        add.setOnClickListener(view -> addItemInInventory());
        if(intent.hasExtra("edit")) {
            if (intent.getExtras().getBoolean("edit")) {
                showItemData();
                add.setText("Update");
                add.setOnClickListener(view -> updateItem());
            }
        } else {
            add.setOnClickListener(view -> addItemInInventory());
        }
        countDeficit();
    }

    private void showItemData() {
        Gson gson = new Gson();
        tempItem = gson.fromJson(getIntent().getStringExtra("myjson"), Item.class);
        itemNameET.setText("" + tempItem.getItemName());
        itemMetricsET.setText("" + tempItem.getItemMetrics());
        itemMinTV.setText("" + tempItem.getItemMinStock());
        itemInStockTV.setText("" + tempItem.getItemInStock());
        itemInStoreTV.setText("" + tempItem.getItemInStore());
        itemOrderedTV.setText("" + tempItem.getItemOrderedStock());
        if(tempItem.itemPictureId != -1){
            itemImage.setImageResource(tempItem.itemPictureId);
        }
    }

    private void addNewPicture() {
        //TODO
    }

    private void addItemInInventory() {
        Item addItem = new Item(
                itemNameET.getText().toString(),
                itemMetricsET.getText().toString(),
                Integer.parseInt(itemInStockTV.getText().toString()),
                Integer.parseInt(itemMinTV.getText().toString()),
                Integer.parseInt(itemInStoreTV.getText().toString()),
                Integer.parseInt(itemOrderedTV.getText().toString()),
                pictureId
        );
        MainActivity.getInstance().addNewItem(addItem);
        finish();
    }

    private void updateItem(){
        Item updateItem = new Item(
                "" + itemNameET.getText().toString(),
                "" + itemMetricsET.getText().toString(),
                Integer.parseInt(itemInStockTV.getText().toString()),
                Integer.parseInt(itemMinTV.getText().toString()),
                Integer.parseInt(itemInStoreTV.getText().toString()),
                Integer.parseInt(itemOrderedTV.getText().toString()),
                pictureId
        );
        itemInStockSubs.hasFocus();
        countDeficit();
        MainActivity.getInstance().updateData(updateItem);
        finish();
    }


    private void bulkSet(TextView tv){
        tv.setOnFocusChangeListener((view, b) -> countDeficit());
    }

    private View.OnClickListener addTextView(TextView textView){
        return view -> {
                textView.setText("" + (Integer.parseInt(textView.getText().toString()) + 1));
                countDeficit();
        };
    }

    private void countDeficit(){
        int min, inStock, inStore, ordered, deficit;
        min = Integer.parseInt(itemMinTV.getText().toString());
        inStock = Integer.parseInt(itemInStockTV.getText().toString());
        inStore = Integer.parseInt(itemInStoreTV.getText().toString());
        ordered = Integer.parseInt(itemOrderedTV.getText().toString());
        deficit = min - (inStock + inStore + ordered);
        itemDeficitTV.setText("" + deficit);
    }


    private View.OnClickListener subsTextView(TextView textView){
        return view -> {
            if(!textView.getText().toString().equals("0"))
            textView.setText("" + (Integer.parseInt(textView.getText().toString()) - 1));
            countDeficit();
        };
    }

}
