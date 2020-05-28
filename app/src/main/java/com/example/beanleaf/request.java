package com.example.beanleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.maps.model.GeocodingResult;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);




        TextView name = (TextView)findViewById(R.id.Name);
        TextView address = (TextView)findViewById(R.id.address);
        TextView timestamp = (TextView)findViewById(R.id.timestamp);

        name.setText((String)getIntent().getStringExtra("name"));
        address.setText((String)getIntent().getStringExtra("address"));
        timestamp.setText((String)getIntent().getStringExtra("timestamp"));

        DBHandler db = DBHandler.getInstance(this);
        Merchant m = (Merchant)db.getMerchantByName((String)getIntent().getStringExtra("name"));



        byte[] image = getIntent().getByteArrayExtra("image");

        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);

        ImageView imageV = (ImageView)findViewById(R.id.proof);
        imageV.setImageBitmap(bmp);

    }

    public void acceptClicked(View v){
        DBHandler db = DBHandler.getInstance(this);
        ArrayList<Order> list = new ArrayList<Order>();
        Merchant m = (Merchant)db.getMerchantByName(getIntent().getStringExtra("username"));
        int id = m.getID();

        GeoApiHelper geoApiHelper = new GeoApiHelper(this);
        GeocodingResult result = geoApiHelper.geocodingApiRequest((String)getIntent().getStringExtra("address"));
        if(result == null){
            //the address is not valid
            Toast.makeText(getApplicationContext(), "The Address you entered is not valid! Try Again!", Toast.LENGTH_SHORT).show();
        }else {
            double lat = result.geometry.location.lat;
            double lon = result.geometry.location.lng;
            db.insertRestaurant((String) getIntent().getStringExtra("name"), new ArrayList<Item>(), new ArrayList<Order>(), id, "0", "0", lat, lon);
            Log.i("TEST", (String) getIntent().getStringExtra("name"));

            Intent i = new Intent(this, addRequests.class);

            ArrayList<requestObj> requestObjArray = db.getRequests();
            int index = 0;
            String address = (String) getIntent().getStringExtra("address");
            for (int k = 0; k < requestObjArray.size(); k++) {
                if (requestObjArray.get(k).getAddress().equals(address)) {
                    index = requestObjArray.get(k).getId();
                }
            }
            db.deleteMerchantRequest(index);


            startActivity(i);
        }
    }

    public void denyClicked(View v){
        Intent i = new Intent(this, addRequests.class);

        i.putExtra("admin",true);
        DBHandler db = DBHandler.getInstance(this);
        ArrayList<requestObj> requestObjArray = db.getRequests();
        int index = 0;
        String address = (String)getIntent().getStringExtra("address");
        for(int k = 0; k < requestObjArray.size(); k++){
            if(requestObjArray.get(k).getAddress().equals(address)){
                index = requestObjArray.get(k).getId();
            }
        }
        db.deleteMerchantRequest(index);
        startActivity(i);


    }
}
