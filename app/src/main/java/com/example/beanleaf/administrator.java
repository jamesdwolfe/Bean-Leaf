package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class administrator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
    }

    public void viewRequestsClicked(View v){
        Intent i = new Intent(this, addRequests.class);
        i.putExtra("admin",true);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
