package com.example.beanleaf;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper implements Serializable {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static final String CUSTOMER_TABLE_NAME = "customer";
    private static final String MERCHANT_TABLE_NAME = "merchant";
    private static final String ADMIN_TABLE_NAME = "admin";
    private static final String RESTAURANT_TABLE_NAME = "restaurant";
    private static final String ORDER_TABLE_NAME = "orders";
    private static final String ITEM_TABLE_NAME = "item";
    private static final String KEY_ROWID = "ID";

    private static DBHandler sInstance;

    public static synchronized DBHandler getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    //initialize the database
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void sweepDatabases(){
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME);
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + MERCHANT_TABLE_NAME);
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE_NAME);
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE_NAME);
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " + CUSTOMER_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL UNIQUE, Gender VARCHAR, Email VARCHAR, Password VARCHAR, PhoneNumber VARCHAR, CaffeineIntake INTEGER, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8), Orders BLOB)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + MERCHANT_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL UNIQUE, Gender VARCHAR, Email VARCHAR, Password VARCHAR, PhoneNumber VARCHAR, CaffeineIntake INTEGER, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8), Orders BLOB, Restaurants BLOB)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + ADMIN_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL UNIQUE, Gender VARCHAR, Email VARCHAR, Password VARCHAR, PhoneNumber VARCHAR, CaffeineIntake INTEGER, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8), Orders BLOB, Restaurants BLOB)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + RESTAURANT_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL UNIQUE, Menu BLOB, CurrentOrders BLOB, Owner INTEGER, OpeningTime TEXT, ClosingTime TEXT, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8))";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Restaurant INTEGER, Customer INTEGER, TotalPrice FLOAT, Items BLOB, OrderTime TEXT)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + ITEM_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL UNIQUE, Restaurant INTEGER, Category VARCHAR, Price FLOAT, CaffeineIntake INTEGER, Description VARCHAR, Ingredients BLOB)";
        db.execSQL(createQuery);
        createQuery =  "CREATE TABLE IF NOT EXISTS merchantRequests(Id INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR, Name VARCHAR, Address VARCHAR, Image BLOB, Timestamp VERCHAR)";
        db.execSQL(createQuery);
    }

    //Creates the tables
    public void createTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        String createQuery =  "CREATE TABLE IF NOT EXISTS merchantRequests(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Address VARCHAR, Image BLOB, Timestamp VERCHAR)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + CUSTOMER_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL UNIQUE, Gender VARCHAR, Email VARCHAR, Password VARCHAR, PhoneNumber VARCHAR, CaffeineIntake INTEGER, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8), Orders BLOB)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + MERCHANT_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Email VARCHAR, Password VARCHAR, PhoneNumber VARCHAR, CaffeineIntake INTEGER, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8), Orders BLOB, Restaurants BLOB)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + ADMIN_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Email VARCHAR, Password VARCHAR, PhoneNumber VARCHAR)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + RESTAURANT_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Menu BLOB, CurrentOrders BLOB, Owner INTEGER, OpeningTime TEXT, ClosingTime TEXT, LocationLat DECIMAL(10,8), LocationLng DECIMAL(11,8))";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Restaurant INTEGER, Customer INTEGER, TotalPrice FLOAT, Items BLOB, OrderTime TEXT)";
        db.execSQL(createQuery);
        createQuery = "CREATE TABLE IF NOT EXISTS " + ITEM_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Restaurant INTEGER, Category VARCHAR, Name VARCHAR, Price FLOAT, CaffeineIntake INTEGER, Description VARCHAR, Ingredients BLOB)";
        db.execSQL(createQuery);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MERCHANT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS merchantRequests");
        onCreate(db);
    }


    public boolean checkIfUserExists2(boolean isCustomer, String email, String password){
        if(isCustomer) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = null;
            String sql ="SELECT * FROM "+CUSTOMER_TABLE_NAME+" WHERE Email = '"+email+"' and Password ='" + password + "';";
            cursor= db.rawQuery(sql,null);
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
        }
        return true;
    }

    public ArrayList<requestObj> getRequests(){

        ArrayList<requestObj> list = new ArrayList<requestObj>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM merchantRequests;";
        cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){

            String username = cursor.getString(cursor.getColumnIndex("Username"));

            int Id = cursor.getInt(cursor.getColumnIndexOrThrow("Id"));
            String name =  cursor.getString(cursor.getColumnIndex("Name"));
            String address =  cursor.getString(cursor.getColumnIndex("Address"));
            String timestamp =  cursor.getString(cursor.getColumnIndex("Timestamp"));
            byte[] image = cursor.getBlob(cursor.getColumnIndex("Image"));

            requestObj r = new requestObj(Id,username, name, address, timestamp, image);
            list.add(r);
            cursor.moveToNext();
        }
        return list;

    }

    //Checks if the user exsits or Not
    public boolean checkIfUserExists(boolean isCustomer, String email){
        if(isCustomer) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = null;
            String sql ="SELECT * FROM "+CUSTOMER_TABLE_NAME+" WHERE Email = '"+email+"';";
            cursor= db.rawQuery(sql,null);
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
            /*
            String query = "select * from Customer where Email=\""+ email + "\"";
            Cursor cs = db.rawQuery(query, null);
            if(cs == null){
                return false;
            }else{
                return true;
            }
             */
        }
        return true;
    }

    public boolean insertData(String name, String email, String password, String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        if(checkIfUserExists(true, email)){
            return false;
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put("Name", name);
            contentValues.put("Email", email);
            contentValues.put("Password", password);
            contentValues.put("PhoneNumber", phoneNumber);
            long result = db.insert(CUSTOMER_TABLE_NAME, null, contentValues);
            if(result == -1)
                return false;
            return true;
        }
    }

    public boolean insertRequest(String username, String name, String address, byte[] image, String timestamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("Name", name);
        contentValues.put("Address", address);
        contentValues.put("Image", image);
        contentValues.put("Timestamp", timestamp);
        long result = db.insert("merchantRequests", null, contentValues);
        if(result == -1)
            return false;
        return true;
    }


    //******************Serialization functions***********************************
    public byte[] toBytes(Object data) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            byte[] employeeAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
            return employeeAsBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object toObject(byte[] data) {
        if(data == null){
            return null;
        }
        try {
            ByteArrayInputStream baip = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Object dataobj = (Object) ois.readObject();
            return dataobj ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //******************Insertion functions***********************************
    public boolean insertCustomer(String Name, String Gender, String Email, String Password, String PhoneNumber, int CaffeineIntake, double LocationLat, double LocationLng, Object Orders){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Gender", Gender);
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("CaffeineIntake", CaffeineIntake);
        contentValues.put("LocationLat", LocationLat);
        contentValues.put("LocationLng", LocationLng);
        contentValues.put("Orders", toBytes(Orders));
        long result = db.insert(CUSTOMER_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    public boolean insertMerchant(String Name, String Gender, String Email, String Password, String PhoneNumber, int CaffeineIntake, double LocationLat, double LocationLng, Object Orders, Object Restaurants){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Gender", Gender);
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("CaffeineIntake", CaffeineIntake);
        contentValues.put("LocationLat", LocationLat);
        contentValues.put("LocationLng", LocationLng);
        contentValues.put("Orders", toBytes(Orders));
        contentValues.put("Restaurants", toBytes(Restaurants));
        long result = db.insert(MERCHANT_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    public boolean insertAdmin(String Name, String Gender, String Email, String Password, String PhoneNumber, int CaffeineIntake, double LocationLat, double LocationLng, Object Orders, Object Restaurants){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Gender", Gender);
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);
        contentValues.put("PhoneNumber", PhoneNumber);
        contentValues.put("CaffeineIntake", CaffeineIntake);
        contentValues.put("LocationLat", LocationLat);
        contentValues.put("LocationLng", LocationLng);
        contentValues.put("Orders", toBytes(Orders));
        contentValues.put("Restaurants", toBytes(Restaurants));
        long result = db.insert(ADMIN_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    public boolean insertRestaurant(String Name, Object Menu, Object CurrentOrders, int Owner, String OpeningTime, String ClosingTime, double LocationLat, double LocationLng){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Menu", toBytes(Menu));
        contentValues.put("CurrentOrders", toBytes(CurrentOrders));
        contentValues.put("Owner", Owner);
        contentValues.put("OpeningTime", OpeningTime);
        contentValues.put("ClosingTime", ClosingTime);
        contentValues.put("LocationLat", LocationLat);
        contentValues.put("LocationLng", LocationLng);
        long result = db.insert(RESTAURANT_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    public boolean insertOrder(int Restaurant, int Customer, float TotalPrice, Object Items, String OrderTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Restaurant", Restaurant);
        contentValues.put("Customer", Customer);
        contentValues.put("TotalPrice", TotalPrice);
        contentValues.put("Items", toBytes(Items));
        contentValues.put("OrderTime", OrderTime);
        long result = db.insert(ORDER_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    public boolean insertItem(String Name,int Restaurant, String Category, float Price, int CaffeineIntake, String Description, Object Ingredients){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Restaurant", Restaurant);
        contentValues.put("Category", Category);
        contentValues.put("Price", Price);
        contentValues.put("CaffeineIntake", CaffeineIntake);
        contentValues.put("Description", Description);
        contentValues.put("Ingredients", toBytes(Ingredients));
        long result = db.insert(ITEM_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    //******************Delete functions***********************************
    public boolean deleteCustomer(int id){ return this.getWritableDatabase().delete(CUSTOMER_TABLE_NAME, KEY_ROWID + "=" + id, null) > 0; }
    public boolean deleteMerchant(int id){ return this.getWritableDatabase().delete(MERCHANT_TABLE_NAME, KEY_ROWID + "=" + id, null) > 0; }
    public boolean deleteAdmin(int id){ return this.getWritableDatabase().delete(ADMIN_TABLE_NAME, KEY_ROWID + "=" + id, null) > 0; }
    public boolean deleteRestaurant(int id){ return this.getWritableDatabase().delete(RESTAURANT_TABLE_NAME, KEY_ROWID + "=" + id, null) > 0; }
    public boolean deleteOrder(int id){ return this.getWritableDatabase().delete(ORDER_TABLE_NAME, KEY_ROWID + "=" + id, null) > 0; }
    public boolean deleteItem(int id){ return this.getWritableDatabase().delete(ITEM_TABLE_NAME, KEY_ROWID + "=" + id, null) > 0; }
    public boolean deleteMerchantRequest(int id){ return this.getWritableDatabase().delete("merchantRequests", "Id="+id, null) > 0; }


    //******************Get functions***********************************
    public Object getCustomerByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "ID="+id;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                CUSTOMER_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String objGender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String objEmail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            String objPassword = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String objPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            ArrayList<Order> objOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Orders")));
            Customer obj = new Customer(objID,objName,objGender,objEmail,objPassword,objPhoneNumber,objLocationLat,objLocationLng,objCaffeineIntake,objOrders);
            return obj;
        } else {
            return null;
        }
    }
    public boolean customerExsits(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM customer WHERE Name ='" + Name + "';";
        cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Customer getCustomerByName(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "Name = ?";
        String[] selectionArgs = { Name };
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                CUSTOMER_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String objGender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String objEmail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            String objPassword = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String objPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            ArrayList<Order> objOrders = (ArrayList)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Orders")));
            Customer obj = new Customer(objID,objName,objGender,objEmail,objPassword,objPhoneNumber,objLocationLat,objLocationLng,objCaffeineIntake,objOrders);
            return obj;
        } else {
            return null;
        }
    }
    //CAn you write a method that returns an arrayList of all resturants on the maps assoisated with a given usernname(merchant)
    public Merchant getMerchantByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "ID="+id;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                MERCHANT_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String objGender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String objEmail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            String objPassword = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String objPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            ArrayList<Order> objOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Orders")));
            ArrayList<Restaurant> objRestaurants = (ArrayList<Restaurant>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Restaurants")));
            Merchant obj = new Merchant(objID,objName,objGender,objEmail,objPassword,objPhoneNumber,objLocationLat,objLocationLng,objCaffeineIntake,objOrders, objRestaurants);
            return obj;
        } else {
            return null;
        }
    }
    public boolean merchantExsits(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM " + MERCHANT_TABLE_NAME +" WHERE Name ='" + Name + "';";
        cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return true;
        }
        return false;
    }
    public Merchant getMerchantByName(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "Name = ?";
        String[] selectionArgs = { Name };
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                MERCHANT_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));

            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));

            String objGender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String objEmail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));

            String objPassword = cursor.getString(cursor.getColumnIndexOrThrow("Password"));

            String objPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            ArrayList<Order> objOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Orders")));

            ArrayList<Restaurant> objRestaurants = (ArrayList<Restaurant>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Restaurants")));
            Merchant obj = new Merchant(objID,objName,objGender,objEmail,objPassword,objPhoneNumber,objLocationLat,objLocationLng,objCaffeineIntake,objOrders, objRestaurants);
            return obj;
        } else {
            return null;
        }
    }

    public Object getAdminByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "ID="+id;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                ADMIN_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String objGender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String objEmail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            String objPassword = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String objPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            ArrayList<Order> objOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Orders")));
            ArrayList<Restaurant> objRestaurants = (ArrayList<Restaurant>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Restaurants")));
            Merchant obj = new Merchant(objID,objName,objGender,objEmail,objPassword,objPhoneNumber,objLocationLat,objLocationLng,objCaffeineIntake,objOrders, objRestaurants);
            return obj;
        } else {
            return null;
        }
    }

    public Object getAdminByName(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "Name = ?";
        String[] selectionArgs = { Name };
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                ADMIN_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String objGender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String objEmail = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            String objPassword = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String objPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            ArrayList<Order> objOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Orders")));
            ArrayList<Restaurant> objRestaurants = (ArrayList<Restaurant>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Restaurants")));
            Merchant obj = new Merchant(objID,objName,objGender,objEmail,objPassword,objPhoneNumber,objLocationLat,objLocationLng,objCaffeineIntake,objOrders, objRestaurants);
            return obj;
        } else {
            return null;
        }
    }

    public Object getRestaurantByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "ID="+id;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                RESTAURANT_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            ArrayList<Item> objMenu = (ArrayList<Item>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Menu")));
            ArrayList<Order> objCurrentOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("CurrentOrders")));
            int objOwner = cursor.getInt(cursor.getColumnIndexOrThrow("Owner"));
            String objOpeningTime = cursor.getString(cursor.getColumnIndexOrThrow("OpeningTime"));
            String objClosingTime = cursor.getString(cursor.getColumnIndexOrThrow("ClosingTime"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            Restaurant obj = new Restaurant(objID,objName,objMenu,objCurrentOrders,objOwner,objOpeningTime,objClosingTime,objLocationLat,objLocationLng);
            return obj;
        } else {
            return null;
        }
    }


//    public Object getRestaurantsByMerchantID(int merchantID){

    public ArrayList<Restaurant> getRestaurantsByMerchantID(int merchantID){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "Owner="+merchantID;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                RESTAURANT_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        ArrayList<Restaurant> restaurants =  new ArrayList<Restaurant>();
        while(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            ArrayList<Item> objMenu = (ArrayList<Item>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Menu")));
            ArrayList<Order> objCurrentOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("CurrentOrders")));
            int objOwner = cursor.getInt(cursor.getColumnIndexOrThrow("Owner"));
            String objOpeningTime = cursor.getString(cursor.getColumnIndexOrThrow("OpeningTime"));
            String objClosingTime = cursor.getString(cursor.getColumnIndexOrThrow("ClosingTime"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            Restaurant obj = new Restaurant(objID,objName,objMenu,objCurrentOrders,objOwner,objOpeningTime,objClosingTime,objLocationLat,objLocationLng);
            restaurants.add(obj);
        }
        return restaurants;
    }


    public Object getRestaurantByName(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "Name = ?";
        String[] selectionArgs = { Name };
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                RESTAURANT_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            ArrayList<Item> objMenu = (ArrayList<Item>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Menu")));
            ArrayList<Order> objCurrentOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("CurrentOrders")));
            int objOwner = cursor.getInt(cursor.getColumnIndexOrThrow("Owner"));
            String objOpeningTime = cursor.getString(cursor.getColumnIndexOrThrow("OpeningTime"));
            String objClosingTime = cursor.getString(cursor.getColumnIndexOrThrow("ClosingTime"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            Restaurant obj = new Restaurant(objID,objName,objMenu,objCurrentOrders,objOwner,objOpeningTime,objClosingTime,objLocationLat,objLocationLng);
            return obj;
        } else {
            return null;
        }
    }


    public ArrayList<Object> getRestaurants(){
        ArrayList<Object> list = new ArrayList<Object>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql = "SELECT * FROM " + RESTAURANT_TABLE_NAME + ";";
        cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        for(int i =0; i < cursor.getCount(); i++){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            ArrayList<Item> objMenu = (ArrayList<Item>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Menu")));
            ArrayList<Order> objCurrentOrders = (ArrayList<Order>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("CurrentOrders")));
            int objOwner = cursor.getInt(cursor.getColumnIndexOrThrow("Owner"));
            String objOpeningTime = cursor.getString(cursor.getColumnIndexOrThrow("OpeningTime"));
            String objClosingTime = cursor.getString(cursor.getColumnIndexOrThrow("ClosingTime"));
            double objLocationLat = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLat"));
            double objLocationLng = cursor.getDouble(cursor.getColumnIndexOrThrow("LocationLng"));
            Restaurant obj = new Restaurant(objID,objName,objMenu,objCurrentOrders,objOwner,objOpeningTime,objClosingTime,objLocationLat,objLocationLng);
            list.add(obj);
            cursor.moveToNext();
        }
        return list;
    }

    public Object getItemByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "ID="+id;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                ITEM_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            int objRestaurant = cursor.getInt(cursor.getColumnIndexOrThrow("Restaurant"));
            String objCategory = cursor.getString(cursor.getColumnIndexOrThrow("Category"));
            float objPrice = cursor.getFloat(cursor.getColumnIndexOrThrow("Price"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            String objDescription = cursor.getString(cursor.getColumnIndexOrThrow("Description"));
            ArrayList<String> objIngredients = (ArrayList<String>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Ingredients")));
            Item obj = new Item(objID,objName,objRestaurant,objCategory,objPrice,objCaffeineIntake,objDescription,objIngredients);
            return obj;
        } else {
            return null;
        }
    }

    public Object getItemByName(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "Name = ?";
        String[] selectionArgs = { Name };
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                ITEM_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String objName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            int objRestaurant = cursor.getInt(cursor.getColumnIndexOrThrow("Restaurant"));
            String objCategory = cursor.getString(cursor.getColumnIndexOrThrow("Category"));
            float objPrice = cursor.getFloat(cursor.getColumnIndexOrThrow("Price"));
            int objCaffeineIntake = cursor.getInt(cursor.getColumnIndexOrThrow("CaffeineIntake"));
            String objDescription = cursor.getString(cursor.getColumnIndexOrThrow("Description"));
            ArrayList<String> objIngredients = (ArrayList<String>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Ingredients")));
            Item obj = new Item(objID,objName,objRestaurant,objCategory,objPrice,objCaffeineIntake,objDescription,objIngredients);
            return obj;
        } else {
            return null;
        }
    }

    public Object getOrderByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "ID="+id;
        String sortOrder = "ID ASC";
        Cursor cursor = db.query(
                ORDER_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToNext()){
            int objID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            int objRestaurant = cursor.getInt(cursor.getColumnIndexOrThrow("Restaurant"));
            int objCustomer = cursor.getInt(cursor.getColumnIndexOrThrow("Customer"));
            float objTotalPrice = cursor.getFloat(cursor.getColumnIndexOrThrow("TotalPrice"));
            ArrayList<Item> objItems = (ArrayList<Item>)toObject(cursor.getBlob(cursor.getColumnIndexOrThrow("Items")));
            String objOrderTime = cursor.getString(cursor.getColumnIndexOrThrow("OrderTime"));
            Order obj = new Order(objID,objRestaurant,objCustomer,objTotalPrice,objItems,objOrderTime);
            return obj;
        } else {
            return null;
        }
    }

    //******************Update functions***********************************
    public void updateCustomer(Customer obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",obj.getID());
        contentValues.put("Name",obj.getName());
        contentValues.put("Gender",obj.getGender());
        contentValues.put("Email",obj.getEmail());
        contentValues.put("Password",obj.getPassword());
        contentValues.put("PhoneNumber",obj.getPhoneNunber());
        contentValues.put("CaffeineIntake",obj.getCaffeineIntakeToday());
        contentValues.put("LocationLat",obj.getLocationLat());
        contentValues.put("LocationLng",obj.getLocationLng());
        contentValues.put("Orders",toBytes(obj.getOrderHistory()));
        String whereClause = "ID="+obj.getID();
        db.update(
                CUSTOMER_TABLE_NAME,
                contentValues,
                whereClause,
                null
        );
    }
    public void updateMerchant(Merchant obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",obj.getID());
        contentValues.put("Name",obj.getName());
        contentValues.put("Gender",obj.getGender());
        contentValues.put("Email",obj.getEmail());
        contentValues.put("Password",obj.getPassword());
        contentValues.put("PhoneNumber",obj.getPhoneNunber());
        contentValues.put("CaffeineIntake",obj.getCaffeineIntakeToday());
        contentValues.put("LocationLat",obj.getLocationLat());
        contentValues.put("LocationLng",obj.getLocationLng());
        contentValues.put("Orders",toBytes(obj.getOrderHistory()));
        contentValues.put("Restaurants",toBytes(obj.getRestaurants()));
        String whereClause = "ID="+obj.getID();
        db.update(
                MERCHANT_TABLE_NAME,
                contentValues,
                whereClause,
                null
        );
    }
    public void updateAdmin(Merchant obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",obj.getID());
        contentValues.put("Name",obj.getName());
        contentValues.put("Gender",obj.getGender());
        contentValues.put("Email",obj.getEmail());
        contentValues.put("Password",obj.getPassword());
        contentValues.put("PhoneNumber",obj.getPhoneNunber());
        contentValues.put("CaffeineIntake",obj.getCaffeineIntakeToday());
        contentValues.put("LocationLat",obj.getLocationLat());
        contentValues.put("LocationLng",obj.getLocationLng());
        contentValues.put("Orders",toBytes(obj.getOrderHistory()));
        contentValues.put("Restaurants",toBytes(obj.getRestaurants()));
        String whereClause = "ID="+obj.getID();
        db.update(
                ADMIN_TABLE_NAME,
                contentValues,
                whereClause,
                null
        );
    }
    public void updateRestaurant(Restaurant obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",obj.getID());
        contentValues.put("Name",obj.getName());
        contentValues.put("Menu",toBytes(obj.getMenu()));
        contentValues.put("CurrentOrders",toBytes(obj.getCurrentOrders()));
        contentValues.put("Owner",obj.getOwner());
        contentValues.put("OpeningTime",obj.getOpeningTime());
        contentValues.put("ClosingTime",obj.getClosingTime());
        contentValues.put("LocationLat",obj.getLocationLat());
        contentValues.put("LocationLng",obj.getLocationLng());
        String whereClause = "ID="+obj.getID();
        db.update(
                RESTAURANT_TABLE_NAME,
                contentValues,
                whereClause,
                null
        );
    }
    public void updateOrder(Order obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",obj.getID());
        contentValues.put("Restaurant",obj.getRestaurant());
        contentValues.put("Customer",obj.getCustomer());
        contentValues.put("TotalPrice",obj.getTotalPrice());
        contentValues.put("Items",toBytes(obj.getItems()));
        contentValues.put("OrderTime",obj.getOrderTime());
        String whereClause = "ID="+obj.getID();
        db.update(
                ORDER_TABLE_NAME,
                contentValues,
                whereClause,
                null
        );
    }
    public void updateItem(Item obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",obj.getID());
        contentValues.put("Name",obj.getName());
        contentValues.put("Restaurant",obj.getRestaurant());
        contentValues.put("Category",obj.getCategory());
        contentValues.put("Price",obj.getPrice());
        contentValues.put("CaffeineIntake",obj.getCaffeineIntake());
        contentValues.put("Description",obj.getDescription());
        contentValues.put("Ingredients",toBytes(obj.getIngredients()));
        String whereClause = "ID="+obj.getID();
        db.update(
                ITEM_TABLE_NAME,
                contentValues,
                whereClause,
                null
        );
    }
    //Id INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR, Name VARCHAR, Address VARCHAR, Image BLOB, Timestamp VERCHAR
    public void updateRequest(requestObj obj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",obj.getId());
        contentValues.put("Username",obj.getUsername());
        contentValues.put("Name",obj.getName());
        contentValues.put("Address",obj.getAddress());
        contentValues.put("Image",toBytes(obj.getImage()));
        contentValues.put("Timestamp",obj.getTimestamp());
        String whereClause = "Id="+obj.getId();
        db.update(
                "merchantRequests",
                contentValues,
                whereClause,
                null
        );
    }
}

