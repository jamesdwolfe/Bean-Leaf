package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.beanleaf.Customer;
import com.example.beanleaf.DBHandler;
import com.example.beanleaf.Merchant;
import com.example.beanleaf.R;
import com.example.beanleaf.customerPage;
import com.example.beanleaf.merchantPage;
import com.example.beanleaf.state;

public class accountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        TextView usernameView = (TextView) findViewById(R.id.usernameField);
        usernameView.setEnabled(false);

        RadioButton male = (RadioButton)findViewById((R.id.maleBtn));
        RadioButton female = (RadioButton)findViewById((R.id.femaleBtn));

        TextView passView = (TextView) findViewById(R.id.passField);
        TextView emailView = (TextView) findViewById(R.id.emailField);
        TextView phoneView = (TextView) findViewById(R.id.phoneField);

        state st = (state) getApplicationContext();
        DBHandler db = DBHandler.getInstance(this);
        String name;
        String username = st.getUsername();
        String password;
        String email;
        String phone;
        String gender;

        boolean customerLogedIn = st.isCustomer;
        if(customerLogedIn){
            Customer c = (Customer)db.getCustomerByName(username);
            name = c.getName();
            email = c.getEmail();
            password = c.getPassword();
            phone = c.getPhoneNunber();
            if(c.getGender().equals("Male")){
                gender = "Male";
            }else{
                gender = "Female";
            }
        }else{
            Merchant m = (Merchant)db.getMerchantByName(username);
            name = m.getName();
            email = m.getEmail();
            password = m.getPassword();
            phone = m.getPhoneNunber();
            if(m.getGender().equals("Male")){
                gender = "Male";
            }else{
                gender = "Female";
            }
        }
        usernameView.setText(username);
        passView.setText(password);
        emailView.setText(email);
        phoneView.setText(phone);
        if(gender.equals("Male")){
            male.setChecked(true);
        }else{
            female.setChecked(true);
        }
    }
    public void editClicked(View v){
        TextView usernameView = (TextView) findViewById(R.id.usernameField);
        TextView passView = (TextView) findViewById(R.id.passField);
        TextView emailView = (TextView) findViewById(R.id.emailField);
        TextView phoneView = (TextView) findViewById(R.id.phoneField);


        RadioButton male = (RadioButton)findViewById((R.id.maleBtn));
        RadioButton female = (RadioButton)findViewById((R.id.femaleBtn));

        String password = passView.getText().toString();
        String email = emailView.getText().toString();
        String phone = phoneView.getText().toString();

        DBHandler db = DBHandler.getInstance(this);

        state st = (state) getApplicationContext();
        boolean customerLogedIn = st.isCustomer;
        if(customerLogedIn){
            Customer c = (Customer)db.getCustomerByName(usernameView.getText().toString());
            c.setEmail(email);
            c.setPassword(password);
            c.setPhoneNunber(phone);
            if(male.isChecked()){
                c.setGender("Male");
            }else{
                c.setGender("Female");
            }
            db.updateCustomer(c);

            Intent i = new Intent(this, customerPage.class);
            startActivity(i);
        }else{
            Merchant m = (Merchant)db.getMerchantByName(usernameView.getText().toString());
            m.setEmail(email);
            m.setPassword(password);
            m.setPhoneNunber(phone);
            if(male.isChecked()){
                m.setGender("Male");
            }else{
                m.setGender("Female");
            }
            db.updateMerchant(m);
            Intent i = new Intent(this, merchantPage.class);
            startActivity(i);
        }

    }
}
