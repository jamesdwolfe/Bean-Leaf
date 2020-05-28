package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addRequests extends AppCompatActivity {
    ArrayList<requestObj> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_requests);
        final DBHandler db = DBHandler.getInstance(this);


        list = db.getRequests();
        addRows();
    }

    @Override
    public void onBackPressed() {
        finish();
        boolean admin = getIntent().getBooleanExtra("admin", false);
        if(admin){
            Intent intent = new Intent(this, administrator.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, merchantPage.class);
            startActivity(intent);
        }
    }

    public void addRows(){
        TableLayout tl = (TableLayout)findViewById(R.id.table);

        for(int i = 0; i < list.size(); i++) {
            TableRow tr = new TableRow(this);

            tr.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tr.setGravity(-1);
            tr.setGravity(Gravity.CENTER);

            tr.setLayoutParams(new TableRow.LayoutParams());

            TextView b = new TextView(this);

            b.setText(list.get(i).getName());
            b.setTextSize(30);
            b.setGravity(-1);
            b.setGravity(Gravity.CENTER);
            b.setTag(list.get(i).getName());

            boolean admin = getIntent().getBooleanExtra("admin", false);

            if(admin){
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDetails(v);
                    }
                });
            }



            b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(b);
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void showDetails(View v ){
        TextView textview = (TextView) v.findViewWithTag(v.getTag());
        String name = textview.getText().toString();

        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(name)){
                index = i;
                break;
            }
        }
        String address = list.get(index).getAddress();
        String timestamp = list.get(index).getTimestamp();
        byte[] image = list.get(index).getImage();


        Intent i = new Intent(this,request.class);
        i.putExtra("address", address);
        i.putExtra("timestamp", timestamp);
        i.putExtra("name", name);
        i.putExtra("image", image);
        String username = list.get(index).getUsername();
        i.putExtra("username", username);

        startActivity(i);
    }

}
