package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Manage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
    }

    public void historyButton(View v) {
        Intent intent = new Intent(Manage.this, History.class);
        startActivity(intent);
    }

    public void restockButton(View v) {
        Intent intent = new Intent(Manage.this, Restock.class);
        startActivity(intent);
    }
}