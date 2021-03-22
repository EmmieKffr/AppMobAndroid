package com.example.dutsup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Interets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interets);
    }


    public void openFormations(View view){
        startActivity(new Intent(getApplicationContext(), Formations.class));
        finish();
    }
}