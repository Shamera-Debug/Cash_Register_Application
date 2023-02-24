package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Restock extends AppCompatActivity {

    String[] quantity = new String[2];
    String[] name = new String[2];
    TextView productTypeText;
    EditText addQuantity;
    ListView listView;
    Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);


        listView = findViewById(R.id.restockListView);
        addQuantity =  findViewById(R.id.addQuantity);
        productTypeText = findViewById(R.id.productTypeTextView);




        quantity = MainActivity.getProductQuantity();
        adapter = new Adapter(this, MainActivity.getProductType(), MainActivity.getProductPrice(), MainActivity.getProductQuantity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, index, l) -> {
            name = MainActivity.getProductType();
            productTypeText.setText(name[index]);
        });
    }

    public void OkayButton(View view) {
        int index = MainActivity.listIndex(productTypeText);


        if (TextUtils.isDigitsOnly(addQuantity.getText().toString()) && !addQuantity.getText().toString().matches("")) {
            if (index != 3) {
                int f1 = Integer.parseInt(quantity[index]);
                int f2 = Integer.parseInt(addQuantity.getText().toString());
                quantity[index] = String.valueOf(f1+f2);
                MainActivity.setProductQuantity(quantity);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(Restock.this, "Choose Product Type!!!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Restock.this, "All Fields Are Required!!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void CancelButton(View view) {
        Intent intent = new Intent(Restock.this, Manage.class);
        startActivity(intent);
    }
}