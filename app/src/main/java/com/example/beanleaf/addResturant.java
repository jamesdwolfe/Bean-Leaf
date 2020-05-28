package com.example.beanleaf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.maps.model.GeocodingResult;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class addResturant extends AppCompatActivity {
    Button uploadBtn;
    ImageView img;
    final int REQUEST_CODE_GALLERY = 999;
    EditText nameField, addressField, zipField, stateField;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resturant);

        db = DBHandler.getInstance(this);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);
        img = (ImageView) findViewById(R.id.imageView);

        nameField = (EditText) findViewById(R.id.nameField);
        addressField = (EditText) findViewById(R.id.addressFiled);
        zipField = (EditText) findViewById(R.id.zipField);
        stateField = (EditText) findViewById(R.id.stateField);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void uploadClicked(View v){
        ActivityCompat.requestPermissions(
                addResturant.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                999
        );
    }
    //convert an img to an array
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    //Add the request to the list
    public void submitClicked(View v) {
        /*
        String username = getIntent().getStringExtra("username");
        Log.i("username", username);

         */
        GeoApiHelper geoApiHelper = new GeoApiHelper(this);
        GeocodingResult exists = geoApiHelper.geocodingApiRequest((String) addressField.getText().toString().trim() + "," + zipField.getText().toString().trim() + "," + stateField.getText().toString().trim());
        if (exists == null) {
            Toast.makeText(getApplicationContext(), "The Address you entered is not valid! Try Again!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                state st = (state) getApplicationContext();
                String username = st.getUsername();

                //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new  java.util.Date());

                ArrayList<requestObj> list = db.getRequests();


/*
Hek version
                boolean result = db.insertRequest(username, nameField.getText().toString(), addressField.getText().toString().trim() + "," + zipField.getText().toString().trim() + "," + stateField.getText().toString().trim(),
                        imageViewToByte(img), "2016");
                Toast.makeText(getApplicationContext(), "Your request was added! Once reviewed it'll show up under 'view restaurants'", Toast.LENGTH_SHORT).show();
*/


            boolean result = db.insertRequest(username, nameField.getText().toString(), addressField.getText().toString().trim() + "," + zipField.getText().toString().trim() + "," + stateField.getText().toString().trim(),
                    imageViewToByte(img),"2016");




            Toast.makeText(getApplicationContext(), "Your request was added! Once reviewed it'll show up under 'view restaurants'", Toast.LENGTH_SHORT).show();

                nameField.setText("");
                addressField.setText("");
                zipField.setText("");
                stateField.setText("");
                img.setImageResource(R.mipmap.ic_launcher);

                Intent intent = new Intent(addResturant.this, merchantPage.class);
                startActivity(intent);
            /*
            Intent intent = new Intent(addResturant.this, MainActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("isLogedIn", true);
            intent.putExtra("customerLoggedIn",false);
            startActivity(intent);

             */

        }
        catch (Exception e){
            state st = (state) getApplicationContext();
            String username = st.getUsername();

            //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new  java.util.Date());

            ArrayList<requestObj> list = db.getRequests();
            String str = "PANKAJ";
            byte[] byteArr = str.getBytes();

            boolean result = db.insertRequest(username, nameField.getText().toString(), addressField.getText().toString().trim() + "," + zipField.getText().toString().trim() + "," + stateField.getText().toString().trim(),
                    byteArr,"2016");

            Toast.makeText(getApplicationContext(), "Your request was added! Once reviewed it'll show up under 'view restaurants'", Toast.LENGTH_SHORT).show();

            nameField.setText("");
            addressField.setText("");
            zipField.setText("");
            stateField.setText("");
            img.setImageResource(R.mipmap.ic_launcher);

            Intent intent = new Intent(addResturant.this, merchantPage.class);
            startActivity(intent);
            e.printStackTrace();
        }

        }
    }



}
