package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class regsiter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
    }

    public void completeForm(View v){

        boolean noEmptyFields = true;
        RadioButton bt1 = findViewById(R.id.radioBtn2);
        RadioButton bt2 = findViewById(R.id.radioBtn2);

        TextView name = findViewById(R.id.nameField);
        TextView email = findViewById(R.id.emailField);
        TextView password = findViewById(R.id.passField);
        TextView number = findViewById(R.id.numberField);

        RadioButton male = findViewById(R.id.radioBtn2);

        if(name.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_LONG).show();
            noEmptyFields = false;
        }else if(email.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(),"Please enter an email",Toast.LENGTH_LONG).show();
            noEmptyFields = false;
        }else if(password.getText().toString().length() == 0 ){
            Toast.makeText(getApplicationContext(),"Please enter a password",Toast.LENGTH_LONG).show();
            noEmptyFields = false;
        }else if(number.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_LONG).show();
            noEmptyFields = false;
        }

        boolean isCustomer = bt1.isChecked();

        //If no filed is empty
        if(noEmptyFields){
            final DBHandler db = DBHandler.getInstance(this);
            boolean results = false;
            String gender = "Male";
            if(male.isChecked()){
                gender = "Male";
            }else{
                gender = "Female";
            }
            boolean exists = false;
            //If is a customer or not
            if(isCustomer){

                //results = db.insertData((String)name.getText().toString(), (String)email.getText().toString(), (String)password.getText().toString(), (String)number.getText().toString());
                if(db.customerExsits((String)name.getText().toString())){
                    exists = true;
                }
                if(exists){
                    Toast.makeText(getApplicationContext(), "Account already exists!", Toast.LENGTH_LONG).show();
                }else{
                    results = db.insertCustomer((String)name.getText().toString(), gender, (String)email.getText().toString(),
                            (String)password.getText().toString(), (String)number.getText().toString(),0, 0, 0, new ArrayList<Order>());
                }
            }else{
                //results = db.insertData((String)name.getText().toString(), (String)email.getText().toString(), (String)password.getText().toString(), (String)number.getText().toString());
                if(db.merchantExsits((String)name.getText().toString())){
                    exists = true;
                }
                if(exists){
                    Toast.makeText(getApplicationContext(), "Account already exists!", Toast.LENGTH_LONG).show();
                }else{
                    ArrayList<Order> r = new ArrayList<Order>();


                    results = db.insertMerchant((String)name.getText().toString(), gender, (String)email.getText().toString(),
                            (String)password.getText().toString(), (String)number.getText().toString(),0, 0, 0, r, null);

                    Merchant r2 = (Merchant)db.getMerchantByName((String)name.getText().toString());


                }

            }

            if(results == true){

                Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_LONG).show();


                //If customer then route to customer page otherwise route to merchant register page 2
                if(isCustomer){
                    Intent intent = new Intent(this, MapsActivity.class);
                    state st = (state) getApplicationContext();
                    st.setLoggedIn(true);
                    st.setUsername((String)name.getText().toString());
                    st.setCustomer(true);
                    intent.putExtra("logedIn", true);
                    intent.putExtra("customerLoggedIn", true);
                    intent.putExtra("username", (String)name.getText().toString());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this, MapsActivity.class);
                    state st = (state) getApplicationContext();

                    st.setLoggedIn(true);
                    st.setUsername((String)name.getText().toString());
                    st.setCustomer(false);
                    intent.putExtra("logedIn", true);
                    intent.putExtra("customerLoggedIn", false);
                    intent.putExtra("username", (String)name.getText().toString());
                    startActivity(intent);
                }


            }
        }

    }


}
