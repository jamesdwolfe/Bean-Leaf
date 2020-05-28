package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class merchantPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        TextView view = (TextView)findViewById(R.id.coffeeField2);
        state st = (state)getApplicationContext();
        String name = st.getUsername();
        DBHandler db = DBHandler.getInstance(this);
        Merchant c = (Merchant)db.getMerchantByName(name);
        view.setEnabled(false);
        view.setText(Integer.toString(c.getCaffeineIntakeToday()));
        clearCaffine();


    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void clearCaffine(){
        DBHandler db = DBHandler.getInstance(this);
        state st = (state)getApplicationContext();
        String name = st.getUsername();
        TextView view = (TextView)findViewById(R.id.coffeeField2);

        Merchant c = (Merchant)db.getMerchantByName(name);
        ArrayList<Order> orders = c.getOrderHistory();

        try {
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
            String todayTime = formatter.format(today);
            Date todayDate = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss").parse(todayTime);
            DateTime todayDate2 = new DateTime(todayDate);
            int caff = c.getCaffeineIntakeToday();



            for (int i = orders.size() - 1; i >= 0; i--) {

                String timestamp = orders.get(i).getOrderTime();
                Date date2 = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss").parse(timestamp);

                long secs = (todayDate.getTime() - date2.getTime()) / 1000;
                int hours = (int)secs / 3600;

                if((i == (orders.size() - 1)) && (hours >= 24)){
                    c.setCaffeineIntakeToday(0);
                    db.updateMerchant(c);
                    view.setText(Integer.toString(c.getCaffeineIntakeToday()));
                    break;
                } else if((i != (orders.size() - 1)) && (hours >= 24)){
                    if(caff >= 0){
                        caff -= orders.get(i).getItems().get(0).getCaffeineIntake();
                    }else if(caff < 0){
                        caff = 0;
                        break;
                    }
                }

            }
            c.setCaffeineIntakeToday(caff);
            db.updateMerchant(c);
            view.setText(Integer.toString(c.getCaffeineIntakeToday()));
        }catch(Exception e){

        }
    }


    public void viewAddRequestsClicked(View v){
        Intent i = new Intent(this, addRequests.class);
        i.putExtra("admin",false);
        startActivity(i);

    }

    public void addClicked(View v){
        Intent i = new Intent(this, addResturant.class);
        startActivity(i);
    }

    public void viewRestaurantClicked(View v){
        Intent i = new Intent(this,viewRestaurants.class);
        startActivity(i);
    }

    public void singOutClicked(View v){
        state st = (state) getApplicationContext();
        st.setLoggedIn(false);
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
    }

    public void  viewOrderHistory(View v){
        Intent i = new Intent(this, orderHistory.class);
        i.putExtra("TEST", false);
        startActivity(i);
    }

    public void viewProfileClicked(View v){
        Intent i = new Intent(this, accountInfo.class);
        startActivity(i);
    }

    public void viewChartsClicked(View v){
        Intent i = new Intent(this, viewCharts.class);
        startActivity(i);
    }
}