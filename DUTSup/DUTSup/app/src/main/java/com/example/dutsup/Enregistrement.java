package com.example.dutsup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enregistrement extends AppCompatActivity {

    private Button buttonLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        /*buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormations();
            }
        });*/
    }

    public void openInteret(View view){
        startActivity(new Intent(getApplicationContext(), Interets.class));
        finish();
    }
}