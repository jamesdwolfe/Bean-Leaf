package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BarChartActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_char2);


        BarChart barChart = findViewById(R.id.barchart);

        state st = (state)getApplicationContext();
        DBHandler db = DBHandler.getInstance(this);

        ArrayList<Order> ordersHistory = new ArrayList<>();
        String username = st.getUsername();
        boolean customer = st.isCustomer();

        if(customer){
            Customer c = (Customer)db.getCustomerByName(username);
            ordersHistory = c.getOrderHistory();
        }else{
            Merchant m = (Merchant)db.getMerchantByName(username);
            ordersHistory = m.getOrderHistory();
        }

        //yyyy-MM-dd-hh.mm.ss
        //Last 21 days
        ArrayList<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -21);
        for(int i = 0; i < 21; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dates.add(sdf.format(cal.getTime()));
        }

        int[] count = new int[3];
        count[0] = 0;
        count[1] = 0;
        count[2] = 0;
        //Parse the data by weeks
        for(int i = 0; i < ordersHistory.size(); i++){
            String orderTime = ordersHistory.get(i).getOrderTime().substring(0, 10);
            int index = 0;
            boolean found = false;
            for(int j = 0; j < dates.size(); j++){
                if(dates.get(j).equals(orderTime)){
                    index = j;
                    if(j <= 7){
                        index = 0;
                    }else if(i > 7 && i <= 14){
                        index = 1;
                    }else{
                        index = 2;
                    }
                    found = true;
                    break;
                }
            }
            if(found){
                count[index] += ordersHistory.get(i).getItems().get(0).getCaffeineIntake();
            }
        }

        //Now populate the data
        barChart.getDescription().setEnabled(false);
        ArrayList<BarEntry> values = new ArrayList<>();
        for(int i = 0; i < count.length; i++){
            values.add( new BarEntry(i + 1, count[i]) );
        }
        BarDataSet set = new BarDataSet(values, "Last three weeks!");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);
        BarData data = new BarData(set);
        barChart.setData(data);
        barChart.invalidate();
        barChart.animateY(500);
        barChart.setFitBars(true);
    }

}
