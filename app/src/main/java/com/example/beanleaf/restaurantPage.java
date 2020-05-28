package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.beanleaf.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class restaurantPage extends AppCompatActivity {
    ArrayList<Item> menu = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);

        String address = getIntent().getStringExtra("address");
        String name = getIntent().getStringExtra("name");

        TextView nameView = (TextView) findViewById(R.id.nameField);
        TextView addressView = (TextView) findViewById(R.id.addressField);

        nameView.setText(name);

        //Get the addresss
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String[] LngLat = address.split(",");
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(LngLat[0]), Double.parseDouble(LngLat[1]), 1);
        }catch(Exception e){

        }
        String addres2s = addresses.get(0).getAddressLine(0);
        addressView.setText(addres2s);

        //Now render the menu in the table
        DBHandler db = DBHandler.getInstance(this);
        Restaurant r = null;
        for(int i = 0; i < db.getRestaurants().size();i++){
            r = (Restaurant) db.getRestaurants().get(i);
            if(r.getName().equals(name)){
                break;
            }
        }
        menu = r.getMenu();

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
        for(int i = 0; i < menu.size(); i++){
            if(menu.get(i).getName().equals(name)){
                index = i;
            }
        }
        Intent i = new Intent(this, viewMenuItem.class);
        i.putExtra("name", name);

        DBHandler db = DBHandler.getInstance(this);
        Restaurant r = null;
        for(int i2 = 0; i2 < db.getRestaurants().size();i2++){
            r = (Restaurant) db.getRestaurants().get(i2);
            if(r.getName().equals(getIntent().getStringExtra("name"))){
                break;
            }
        }
        i.putExtra("address",getIntent().getStringExtra("address") );
        i.putExtra("name2",getIntent().getStringExtra("name"));

        i.putExtra("storeID",Integer.toString(r.getID()));

        String price = Float.toString(menu.get(index).getPrice());
        String caffine = Integer.toString(menu.get(index).getCaffeineIntake());
        i.putExtra("price", price);
        i.putExtra("caffeine", caffine);
        startActivity(i);
    }

    public void addItemClicked(View v){

        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");


        Intent i = new Intent(this, MenuItem.class);
        i.putExtra("name", name);
        i.putExtra("address", address);
        startActivity(i);

    }

    public void showOrderHistoryClicked(View v){
        state st = (state)getApplicationContext();

        Intent i = new Intent(this, orderHistory.class);
        i.putExtra("CHECK", true);
        i.putExtra("TEST", true);
        i.putExtra("res", getIntent().getStringExtra("name"));
        startActivity(i);

    }

}
