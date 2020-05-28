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

public class viewRestaurants extends AppCompatActivity {
    ArrayList<Restaurant> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurants);
        list = new ArrayList<Restaurant>();
        renderRestaurants();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, merchantPage.class);
        startActivity(intent);
    }

    public void renderRestaurants(){
        state st = (state) getApplicationContext();
        String owner = st.getUsername();

        DBHandler db = DBHandler.getInstance(this);
        Log.i("sd", owner);
        Merchant m = (Merchant)db.getMerchantByName(owner);
        int id = m.getID();
        ArrayList<Object> restaurants = db.getRestaurants();
        Restaurant r2 = (Restaurant)restaurants.get(0);
        Log.i("test", r2.getName());


        for(int i = 0; i < restaurants.size(); i++){
            Restaurant r = (Restaurant)restaurants.get(i);
            if(r.getOwner() == id){
                list.add(r);
            }
        }

        //Now render the menu in the table
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


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDetails(v);
                }
            });
            b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(b);
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    public void showDetails(View v){
        TextView textview = (TextView) v.findViewWithTag(v.getTag());
        String name = textview.getText().toString();
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equals(name)){
                index = i;
            }
        }

        String address = Double.toString(list.get(index).getLocationLat()) + "," + Double.toString(list.get(index).getLocationLng());


        Intent i = new Intent(this, restaurantPage.class);
        i.putExtra("name", name);
        i.putExtra("address", address);

        startActivity(i);




    }

}
