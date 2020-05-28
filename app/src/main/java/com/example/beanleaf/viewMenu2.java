package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class viewMenu2 extends AppCompatActivity {

    ArrayList<Item> menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);
        menu = new ArrayList<Item>();
        addMenuItems();

    }

    public void addMenuItems(){

        String restaurant = getIntent().getStringExtra("restaurant");
        DBHandler db = DBHandler.getInstance(this);

        Restaurant r = null;
        ArrayList<Object> list = db.getRestaurants();
        for(int i = 0; i < list.size(); i++){
            r = (Restaurant)list.get(i);
            String nam = r.getName();
            if(nam.equals(restaurant)){
                break;
            }
        }
        state st = (state)getApplicationContext();

        menu = r.getMenu();

        //Now render the menu in the table
        TableLayout tl = (TableLayout)findViewById(R.id.table);

        for(int i = 0; i < menu.size(); i++) {
            TableRow tr = new TableRow(this);
            tr.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tr.setGravity(-1);
            tr.setGravity(Gravity.CENTER);

            tr.setLayoutParams(new TableRow.LayoutParams());

            TextView b = new TextView(this);

            b.setText(menu.get(i).getName());
            b.setTextSize(30);
            b.setGravity(-1);
            b.setGravity(Gravity.CENTER);
            b.setTag(menu.get(i).getName());

            if(st.isLoggedIn()){
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

    public void showDetails(View v){
        TextView textview = (TextView) v.findViewWithTag(v.getTag());
        String name = textview.getText().toString();
        int index = 0;
        for(int i = 0; i < menu.size(); i++){
            if(menu.get(i).getName().equals(name)){
                index = i;
            }
        }
        String res = getIntent().getStringExtra("restaurant");
        Intent i = new Intent(this, viewMenuItem2.class);
        i.putExtra("restaurant",res);
        i.putExtra("name", name);
        String price = Float.toString(menu.get(index).getPrice());
        String caffine = Integer.toString(menu.get(index).getCaffeineIntake());
        i.putExtra("price", price);
        i.putExtra("caffeine", caffine);
        startActivity(i);

    }
}
