package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class signIn extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


    }
    //Check if the user info exists
    public void signIn(View v){
        final DBHandler db = DBHandler.getInstance(this);
        TextView name = findViewById(R.id.emailField);
        TextView pass = findViewById(R.id.PasswordField);
        RadioButton btn = findViewById(R.id.radioButton);

        boolean isCustomer = !btn.isChecked();
        boolean success = false;
        boolean temp = false;
        if(name.getText().toString().equals("Root1234") && pass.getText().toString().equals("Root1234")){
            Intent intent = new Intent(this, administrator.class);
            temp = true;
            startActivity(intent);
        }

        if(isCustomer){
            Customer s = (Customer)db.getCustomerByName(name.getText().toString());

            if(s == null){
                success = false;
            }else{
                String username = s.getName();
                String password = s.getPassword();

                if(username.equals(name.getText().toString()) && password.equals(pass.getText().toString())){
                    success = true;
                }
            }
        }else{
            Merchant m = (Merchant)db.getMerchantByName(name.getText().toString());
            if(m == null){
                success = false;
            }else{
                String username = m.getName();
                String password = m.getPassword();

                if(username.equals(name.getText().toString()) && password.equals(pass.getText().toString())){
                    success = true;
                }
            }
        }

        if(success){
            if(isCustomer){
                Intent intent = new Intent(this, MapsActivity.class);
                state st = (state) getApplicationContext();
                st.setLoggedIn(true);
                st.setCustomer(true);
                st.setUsername((String)name.getText().toString());
                /*
                intent.putExtra("logedIn", "true");
                intent.putExtra("customerLoggedIn", true);
                intent.putExtra("username", (String)name.getText().toString());

                 */

                startActivity(intent);
            }else{
                Intent intent = new Intent(this, MapsActivity.class);
                state st = (state) getApplicationContext();
                st.setLoggedIn(true);
                st.setCustomer(false);
                st.setUsername((String)name.getText().toString());
                /*
                intent.putExtra("logedIn", true);
                intent.putExtra("customerLoggedIn", false);
                intent.putExtra("username", (String)name.getText().toString());

                 */
                startActivity(intent);
            }
        }else{
            if(!temp){
                Toast.makeText(getApplicationContext(),"Invalid Information, please try again!",Toast.LENGTH_LONG).show();
            }
        }

        /*
        boolean exists = db.checkIfUserExists2(true, email.getText().toString(), pass.getText().toString());
        if(exists){
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("logedIn",true);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Information is incorrect, please try again!", Toast.LENGTH_LONG).show();
        }8
         */

    }
}
