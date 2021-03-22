package com.example.dutsup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Formations extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.ecole2, R.drawable.ecole2, R.drawable.ecole3, R.drawable.ecole4, R.drawable.ecole5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);

       recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.formations);
        s2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}