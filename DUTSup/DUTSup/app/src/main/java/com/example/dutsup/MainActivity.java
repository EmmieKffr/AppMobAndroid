package com.example.dutsup;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button buttonGo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnregistrement();
            }
        });
    }

    public void openEnregistrement(){
        Intent intent = new Intent(this, Enregistrement.class);
        startActivity(intent);
    }
}