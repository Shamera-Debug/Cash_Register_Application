package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView productTypeText;
    TextView totalAmountText;
    TextView quantityText;
    ListView productListView;
    NumberPicker numberPicker;
    Adapter adapter;


    static String[] productType = {"Pants", "Shoes", "Hats"};
    static String[] productPrice = {"20.44", "10.44", "5.9"};
    static String[] productQuantity = {"10", "100", "30"};

    public static String[] getProductType() {
        return productType;
    }
    public static String[] getProductPrice() {
        return productPrice;
    }
    public static String[] getProductQuantity() {
        return productQuantity;
    }
    public static void setProductQuantity(String[] productQuantity) {
        MainActivity.productQuantity = productQuantity;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_restock, null);
        ListView restockListView = view.findViewById(R.id.restockListView);
        restockListView.setAdapter(adapter);

        totalAmountText = findViewById(R.id.totalAmountTextView);
        productTypeText = findViewById(R.id.productTypeTextView);
        quantityText = findViewById(R.id.quantityTextView);
        productListView = findViewById(R.id.productListView);
        numberPicker = findViewById(R.id.quantityNumberPicker);


        adapter = new Adapter(this, productType, productPrice, productQuantity);
        productListView.setAdapter(adapter);



        numberPicker.setMaxValue(100);

        numberPicker.setOnValueChangedListener((numberPicker, o, n) -> {
            int index = listIndex(productTypeText);


            quantityText.setText(Integer.toString(n));

            if (index < 3) {
                totalAmountText.setText(String.format("%.2f", n * Double.parseDouble(productPrice[index])));
            }
        });

        productListView.setOnItemClickListener((adapterView, view1, index, l) -> {

            productTypeText.setText(productType[index]);

            totalAmountText.setText(String.format("%.2f", numberPicker.getValue() * Double.parseDouble(productPrice[index])));
        });

        if(savedInstanceState != null){
            String[] dd = new String[3];
            dd[0] = productQuantity[0];
            dd = productQuantity;
            setProductQuantity(dd);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArray("",productQuantity);
    }

    public void buyButton(View view) {

        if (totalAmountText.getText().toString().equals("Total") || totalAmountText.getText().toString().equals("0.00")) {
            Toast.makeText(MainActivity.this, "All fields are required!!!", Toast.LENGTH_SHORT).show();
        } else {
            if (inStock()) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thank You for your purchase!!!")
                        .setMessage("Your purchase is " + numberPicker.getValue()
                                + " " + productTypeText.getText().toString() + " for " + totalAmountText.getText().toString()).show();
                adapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("product", productTypeText.getText().toString());
                editor.putString("price", totalAmountText.getText().toString());
                editor.putString("quantity", Integer.toString(numberPicker.getValue()));
                editor.apply();
                History.updateHistory(getApplicationContext());
            } else {
                Toast.makeText(MainActivity.this, "Not enough quantity in stock!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void managerButton(View v) {
        Intent intent = new Intent(MainActivity.this, Manage.class);
        startActivity(intent);
    }


    public boolean inStock() {
        boolean inStock = true;
        int index = listIndex(productTypeText);

        int purchasedQuantity = numberPicker.getValue();

        if (purchasedQuantity > Integer.parseInt(productQuantity[index])) {
            inStock = false;
        } else {
            productQuantity[index] = String.valueOf(Integer.parseInt(productQuantity[index]) - purchasedQuantity);
        }

        return inStock;
    }

    public static int listIndex(TextView tv) {
        int index = 3;
        switch (tv.getText().toString()) {
            case "Pants":
                index = 0;
                break;
            case "Shoes":
                index = 1;
                break;
            case "Hats":
                index = 2;
                break;
            default:
        }
        return index;
    }
}