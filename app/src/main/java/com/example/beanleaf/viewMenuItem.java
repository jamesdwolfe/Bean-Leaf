package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;
import java.sql.Timestamp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class viewMenuItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu_item);
        TextView name = (TextView)findViewById(R.id.nameField);
        TextView price = (TextView)findViewById(R.id.priceField);
        TextView caffine = (TextView)findViewById(R.id.coffeeField);



        name.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));
        caffine.setText(getIntent().getStringExtra("caffeine"));



    }

    public void editInfoClicked(View v){
        DBHandler db = DBHandler.getInstance(this);
        Integer id = Integer.parseInt(getIntent().getStringExtra("storeID"));
        Object i = db.getRestaurantByID(id);
        Restaurant r = (Restaurant)i;
        ArrayList<Item> menu = r.getMenu();

        TextView name = (TextView)findViewById(R.id.nameField);
        TextView price = (TextView)findViewById(R.id.priceField);
        TextView caffine = (TextView)findViewById(R.id.coffeeField);

        Item item = null;
        int index = 0;
        boolean exists = false;

        for(int c = 0; c < menu.size(); c++){
            if(menu.get(c).getName().equals(name.getText().toString())){
                exists = true;
                break;
            }
            if(menu.get(c).getName().equals(getIntent().getStringExtra("name"))){
                item = menu.get(c);
                index = c;
            }
        }

        //check if the item exists or not
        if(!exists){
            //Now change the menu
            item.setName(name.getText().toString());
            item.setPrice(Float.parseFloat(price.getText().toString()));
            item.setCaffeineIntake(Integer.parseInt(caffine.getText().toString()));
            menu.remove(menu.get(index));
            menu.add(item);
            r.setMenu(menu);
            db.updateRestaurant(r);
            Intent intent = new Intent(this, restaurantPage.class);
            intent.putExtra("address",getIntent().getStringExtra("address") );
            intent.putExtra("name",getIntent().getStringExtra("name2"));
            startActivity(intent);
        }else{
            //item exists already
            Toast.makeText(getApplicationContext(), "The item already exists in the menu! Try a differnet name", Toast.LENGTH_SHORT).show();
        }

    }


}
