package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Date;

public class History extends AppCompatActivity {

    static ArrayList<String> productType = new ArrayList<>();
    static ArrayList<String> productQuantity = new ArrayList<>();
    static ArrayList<String> productPrice = new ArrayList<>();
    static ArrayList<String> productDates = new ArrayList<>();
    ListView productListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Adapter adapter = new Adapter(this, productType.toArray(new String[0]), productPrice.toArray(new String[0]), productQuantity.toArray(new String[0]));
        productListView = findViewById(R.id.historyListView);
        productListView.setAdapter(adapter);

        productListView.setOnItemClickListener((adapterView, view, index, l) -> {
            Intent intent = new Intent(History.this, Detail.class);
            intent.putExtra("product", productType.get(index));
            intent.putExtra("price", productQuantity.get(index));
            intent.putExtra("date", productDates.get(index));
            startActivity(intent);
        });
    }




    public static void updateHistory(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myKey", MODE_PRIVATE);
        productType.add(sharedPreferences.getString("product", ""));
        productQuantity.add(sharedPreferences.getString("price", ""));
        productPrice.add(sharedPreferences.getString("quantity", ""));
        productDates.add(String.valueOf(new Date()));
    }
}