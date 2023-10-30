package com.example.myasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton btn_Course = findViewById(R.id.btn_Course);
        ImageButton btn_Map = findViewById(R.id.btn_Map);
        ImageButton btn_News = findViewById(R.id.btn_News);
        ImageButton btn_Social = findViewById(R.id.btn_Social);

        btn_Course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
        btn_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, NewsAPI.class);
                finish();
                startActivity(i);
            }
        });
        btn_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                finish();
                startActivity(intent);
            }
        });
        btn_Social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SocialActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}