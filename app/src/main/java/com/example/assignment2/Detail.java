package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;
import androidx.annotation.Nullable;
import android.os.Bundle;

public class Detail extends AppCompatActivity {

    TextView name;
    TextView price;
    TextView date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        name  = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        date = findViewById(R.id.productDate);

        name.setText("Product : " + intent.getStringExtra("product"));
        price.setText("Price : " + intent.getStringExtra("price"));
        date.setText("Purchase Date : " + intent.getStringExtra("date"));
    }
}
