package com.example.automatedwarehouseinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> itemsArrayList;
    boolean firstTime = false;
    Item tempItem;
    int itemIndex = -1;
    InventoryAdapter adapter;
    RecyclerView inventoryRecyclerView;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startupFunction();
        instance = this;
    }

    private void saveItems() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor editor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemsArrayList);
        editor.putString("task list", json);
        editor.apply();
    }

    public void InventoryViewSetup(){
        FloatingActionButton fabAddNewItem;
        inventoryRecyclerView = findViewById(R.id.inventoryRecyclerView);
        itemsArrayList = new ArrayList<>();
        loadItems();
        adapter = new InventoryAdapter(this, itemsArrayList);
        inventoryRecyclerView.setAdapter(adapter);
        inventoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabAddNewItem = findViewById(R.id.addNewItemFab);
        fabAddNewItem.setOnClickListener(view1 -> {
            Intent i = new Intent(this, ItemDetailsActivity.class);
            startActivity(i);
        });
        adapter.notifyDataSetChanged();
    }

    private void loadItems() {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("task list", null);
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
        itemsArrayList = gson.fromJson(json, type);
    }

    public void startEditNewItemActivity(){
        ItemDetailsActivity act = new ItemDetailsActivity();
        Intent intent = new Intent(MainActivity.this, act.getClass());
        intent.putExtra("edit", true);
        Gson gson = new Gson();
        String myJson = gson.toJson(tempItem);
        intent.putExtra("myjson", myJson);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        saveItems();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private void splashScreenFunction() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.frameLayoutContainer, SplashFragment.class, null)
                .commit();
    }

    public void addNewItem(Item tempItem) {
        itemsArrayList.add(tempItem);
        adapter.notifyDataSetChanged();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void updateData(Item updateItem) {
        itemsArrayList.set(itemIndex, updateItem);
        adapter.notifyDataSetChanged();
        inventoryRecyclerView.invalidate();
    }

    void startupFunction(){
        if(firstTime) {
            splashScreenFunction();
            firstTime = false;
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                        setContentView(R.layout.layout_inventory_list);
                        InventoryViewSetup();
                    },
                    5000);
        } else {
            setContentView(R.layout.layout_inventory_list);
            InventoryViewSetup();
        }
    }
}