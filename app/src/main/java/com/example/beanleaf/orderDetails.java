package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class orderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        populateData();
    }

    public void populateData(){
        state st = (state) getApplicationContext();
        String username = st.getUsername();
        boolean isCustomer = st.isCustomer();
        String timestamp = getIntent().getStringExtra("timestamp");
        DBHandler db = DBHandler.getInstance(this);
        ArrayList<Item>  item = null;

        TextView name2 = (TextView)findViewById(R.id.nameField);

        ArrayList<Order> orders;

        Boolean check = getIntent().getBooleanExtra("CHECK", false);
        if (check && !isCustomer) {
            String name = getIntent().getStringExtra("res");
            //Restaurant r = (Restaurant)db.getRestaurantByName(name);
            Restaurant r2 = null;
            ArrayList<Object> list2 = db.getRestaurants();
            for(int i = 0; i < list2.size(); i++){
                r2 = (Restaurant)list2.get(i);
                String nam = r2.getName();
                if(nam.equals(name)){
                    break;
                }
            }

            orders = r2.getCurrentOrders();
            if(orders.equals(null)){
                Log.i("SOME", "SOME");
            }
        }else{
            name2.setText(username);
            if(st.isCustomer()){
                Customer cs = (Customer)db.getCustomerByName(st.getUsername());
                orders = cs.getOrderHistory();
            }else{
                Merchant m = (Merchant)db.getMerchantByName(st.getUsername());
                orders = m.getOrderHistory();
            }
        }

        int cusID = 0;
        for(int i = 0; i < orders.size(); i++) {
            String s = orders.get(i).getOrderTime();

            if(s.contentEquals(timestamp)){
                item = orders.get(i).getItems();
                cusID = orders.get(i).getCustomer();
                break;
            }

        }



        TextView resturant = (TextView)findViewById(R.id.restaurantField);
        TextView totalprice = (TextView)findViewById(R.id.priceField);
        TextView ordertime = (TextView)findViewById(R.id.timeField);
        TextView items = (TextView)findViewById(R.id.itemsField);

        int i2 = item.get(0).getRestaurant();

        Restaurant r = (Restaurant) db.getRestaurantByID(i2);

        String name = r.getName();

        if(check && !isCustomer){
            Customer c = (Customer)db.getCustomerByID(cusID);
            if(c == null){
                Merchant m = (Merchant)db.getMerchantByID(cusID);
                name2.setText(m.getName());
            }else{
                name2.setText(c.getName());
            }
        }

        resturant.setText(name);
        totalprice.setText(Float.toString(item.get(0).getPrice()));

        ordertime.setText(timestamp);

        String fulllist = "";
        for(int i = 0; i < item.size(); i++){
            fulllist += item.get(i).getName();
        }
        items.setText(fulllist);
    }
}
