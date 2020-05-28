package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class viewMenuItem2 extends AppCompatActivity {
    Item i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu_item2);


        TextView name = (TextView)findViewById(R.id.nameField);
        TextView price = (TextView)findViewById(R.id.priceField);
        TextView caffine = (TextView)findViewById(R.id.coffeeField);

        DBHandler db = DBHandler.getInstance(this);
        String res = getIntent().getStringExtra("restaurant");
        //Restaurant m = (Restaurant)db.getRestaurantByName(res);

        Restaurant m = null;
        ArrayList<Object> list = db.getRestaurants();
        for(int i = 0; i < list.size(); i++){
            m = (Restaurant)list.get(i);
            String nam = m.getName();
            if(nam.equals(res)){
                break;
            }
        }



        i = new Item(0, getIntent().getStringExtra("name"), m.getID()
                , "trash", Float.parseFloat(getIntent().getStringExtra("price")),
                Integer.parseInt(getIntent().getStringExtra("caffeine")),"sdf",null);

        name.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));
        caffine.setText(getIntent().getStringExtra("caffeine"));
    }

    public void addItemtoCardClicked(View v){
        state st = (state) getApplicationContext();
        DBHandler db = DBHandler.getInstance(this);


        String username = st.getUsername();
        String res = getIntent().getStringExtra("restaurant");

        int id = 0;
        if(st.isCustomer()){
            Customer c = (Customer)db.getCustomerByName(username);
            id = c.getID();
        }else{
            Merchant c = (Merchant)db.getMerchantByName(username);
            id = c.getID();
        }


        Restaurant r2 = null;
        ArrayList<Object> list2 = db.getRestaurants();
        for(int i = 0; i < list2.size(); i++){
            r2 = (Restaurant)list2.get(i);
            String nam = r2.getName();
            if(nam.equals(res)){
                break;
            }
        }

        //Restaurant m = (Restaurant)db.getRestaurantByName(res);
        ArrayList<Item> list = new ArrayList<Item>();
        list.add(i);

        /*
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        */
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        String time = formatter.format(today);

        Order o = new Order(0, r2.getID(), id, i.getPrice(), list, time);

        //Now update database
        //Restaurant r = (Restaurant)db.getRestaurantByName(res);
        ArrayList<Order> orders = r2.getCurrentOrders();


        if(orders == null){
            Log.i("is null", "null");
        }
        orders.add(o);
        r2.setCurrentOrders(orders);
        db.updateRestaurant(r2);

        //Now update user current orders
        if(st.isLoggedIn()){
            if(st.isCustomer()){
                Customer c1 = (Customer)db.getCustomerByName(st.getUsername());


                ArrayList<Order> orders2 = c1.getOrderHistory();
                orders2.add(o);
                c1.setOrderHistory(orders2);

                if((c1.getCaffeineIntakeToday() + Integer.parseInt(getIntent().getStringExtra("caffeine")) > 400)){
                    Toast.makeText(getApplicationContext(),"Watch out! You are going over the limit of 400mg of Caffeine!",Toast.LENGTH_LONG).show();

                }
                c1.setCaffeineIntakeToday(c1.getCaffeineIntakeToday() + Integer.parseInt(getIntent().getStringExtra("caffeine")));

                db.updateCustomer(c1);
            }else{
                Merchant m1 = (Merchant)db.getMerchantByName(st.getUsername());



                ArrayList<Order> orders2 = m1.getOrderHistory();
                orders2.add(o);
                m1.setOrderHistory(orders2);
                if((m1.getCaffeineIntakeToday() + Integer.parseInt(getIntent().getStringExtra("caffeine")) > 400)){
                    Toast.makeText(getApplicationContext(),"Watch out! You are going over the limit of 400mg of Caffeine!",Toast.LENGTH_LONG).show();

                }
                m1.setCaffeineIntakeToday(m1.getCaffeineIntakeToday() + Integer.parseInt(getIntent().getStringExtra("caffeine")));

                db.updateMerchant(m1);
            }
        }
        Toast.makeText(getApplicationContext(),"Your order was placed!",Toast.LENGTH_LONG).show();

    }
}
