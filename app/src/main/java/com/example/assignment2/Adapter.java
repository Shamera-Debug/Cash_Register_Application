package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class Adapter extends ArrayAdapter<String> {
    String[] productType;
    String[] productPrice;
    String[] productQuantity;
    Context context;

    Adapter(Context context, String[] productType, String[] productPrice, String[] productQuantity) {
        super(context, R.layout.product_list, R.id.productName, productType);
        this.context = context;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dd = layoutInflater.inflate(R.layout.product_list, parent, false);
        TextView name = dd.findViewById(R.id.productName);
        TextView price = dd.findViewById(R.id.productPrice);
        TextView quantity = dd.findViewById(R.id.productQuantity);
        name.setText(productType[position]);
        price.setText(productPrice[position]);
        quantity.setText(productQuantity[position]);
        return dd;
    }
}
