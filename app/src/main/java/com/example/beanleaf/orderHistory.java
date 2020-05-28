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

public class orderHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        createRows();
    }

    public void createRows(){
        DBHandler db = DBHandler.getInstance(this);
        state st = (state) getApplicationContext();
        ArrayList<Order> orders = null;
        Boolean check = getIntent().getBooleanExtra("CHECK",false);

        Log.i("test1", st.getUsername());

        if(check){
            Log.i("test1", "1");

            String name = getIntent().getStringExtra("res");

            Restaurant r2 = null;
            ArrayList<Object> list2 = db.getRestaurants();
            for(int i = 0; i < list2.size(); i++){
                r2 = (Restaurant)list2.get(i);
                String nam = r2.getName();
                if(nam.equals(name)){
                    break;
                }
            }

            //Restaurant r = (Restaurant)db.getRestaurantByName(name);
            orders = r2.getCurrentOrders();

        }else{

            if(st.isCustomer()){
                Log.i("test1", "2");

                Customer cs = (Customer)db.getCustomerByName(st.getUsername());
                orders = cs.getOrderHistory();
            }else{
                Log.i("test1", "3");

                Merchant m = (Merchant)db.getMerchantByName(st.getUsername());
                orders = m.getOrderHistory();
                if(orders == null){
                    orders = new ArrayList<Order>();
                    m.setOrderHistory(orders);
                    orders = m.getOrderHistory();
                    if(orders == null){
                        Log.i("TEST", "LOGIC1");
                    }{
                        Log.i("TEST", "EM1");
                    }


                    db.updateMerchant(m);

                    Merchant m2 = (Merchant)db.getMerchantByName(st.getUsername());
                    orders = m2.getOrderHistory();
                    if(orders == null){
                        Log.i("TEST", "LOGIC");
                    }{
                        Log.i("TEST", "EM");
                    }


                }

            }
        }




        TableLayout tl = (TableLayout)findViewById(R.id.table);

        for(int i = 0; i < orders.size(); i++) {
            TableRow tr = new TableRow(this);

            tr.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tr.setGravity(-1);
            tr.setGravity(Gravity.CENTER);

            tr.setLayoutParams(new TableRow.LayoutParams());

            TextView b = new TextView(this);

            b.setText(orders.get(i).getOrderTime());
            b.setTextSize(30);
            b.setGravity(-1);
            b.setGravity(Gravity.CENTER);
            b.setTag(orders.get(i).getOrderTime());


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
        String timestamp = textview.getText().toString();
        Intent i = new Intent(this, orderDetails.class);
        i.putExtra("timestamp", timestamp);

        boolean test = getIntent().getBooleanExtra("TEST", false);
        i.putExtra("CHECK", true);
        if(test == false){
            i.putExtra("CHECK", false);

        }

        i.putExtra("res", getIntent().getStringExtra("res"));
        startActivity(i);

    }

}
