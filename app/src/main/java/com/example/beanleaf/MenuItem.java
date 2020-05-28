package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.beanleaf.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
    }


    //Add the item to the menu
    public void addItemClicked(View v){
        String restaurant = getIntent().getStringExtra("name");
        DBHandler db = DBHandler.getInstance(this);
        TextView name = (TextView)findViewById(R.id.nameField);
        TextView price = (TextView)findViewById(R.id.priceField);
        TextView caffine = (TextView)findViewById(R.id.caffeineField);

        Log.i("LOG", restaurant);
        Restaurant r = null;
        ArrayList<Object> list = db.getRestaurants();
        for(int i = 0; i < list.size(); i++){
            r = (Restaurant)list.get(i);
            Log.i("TEST" + i, r.getName());
            String nam = r.getName();
            if(nam.equals(restaurant)){
                break;
            }
        }


        if(r == null){
            Log.i("NUL", "NULL");
        }

        int ID = r.getID();
        ArrayList<Item> menu = r.getMenu();
        Item i = new Item(1, name.getText().toString(), ID,"Trash", Float.parseFloat(price.getText().toString()), Integer.parseInt(caffine.getText().toString()), "asdasd", null);
        menu.add(i);
        r.setMenu(menu);
        db.updateRestaurant(r);

        Restaurant r2 = (Restaurant)db.getRestaurantByName(restaurant);
        String address = getIntent().getStringExtra("address");
        Intent intent = new Intent(this,restaurantPage.class);
        intent.putExtra("address", address);
        intent.putExtra("name", restaurant);

        startActivity(intent);
    }

}
