package com.example.beanleaf;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class DBHandlerTest {
    final DBHandler db = DBHandler.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());

    @Before
    public void setUp() throws Exception {
        //Sweep and create databases for each test
        db.sweepDatabases();
        db.createTables();
    }

    /**************************Serialization Tests*********************************/
    @Test
    public void SerializationTest() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(69);
        byte[] bytes = db.toBytes(a);
        a = (ArrayList<Integer>)db.toObject(bytes);
        assertEquals(69,(int)a.get(0));
    }

    /**************************Insert Tests*********************************/
    @Test
    public void insertRequestTest(){
        db.insertRequest("username","name",null,null,"0");
        ArrayList<requestObj> obj = (ArrayList<requestObj>)db.getRequests();
        assertEquals("username",obj.get(0).getUsername());
    }

    @Test
    public void insertCustomerTest(){
        db.insertCustomer("Name","Gender","Email","Password","867-5309",100,100,100,null);
        Customer obj = (Customer)db.getCustomerByID(1);
        assertEquals(1,obj.getID());
    }

    @Test
    public void insertMerchantTest(){
        db.insertMerchant("Name","Gender","Email","Password","867-5309",100,100,100,null,null);
        Merchant obj = (Merchant)db.getMerchantByID(1);
        assertEquals(1,obj.getID());
    }

    @Test
    public void insertAdminTest(){
        db.insertAdmin("Name","Gender","Email","Password","867-5309",100,100,100,null,null);
        Merchant obj = (Merchant)db.getAdminByID(1);
        assertEquals(1,obj.getID());
    }

    @Test
    public void insertRestaurantTest(){
        db.insertRestaurant("Name",null,null,1,"8am","8pm",100,100);
        Restaurant obj = (Restaurant)db.getRestaurantByID(1);
        assertEquals(1,obj.getID());
    }

    @Test
    public void insertOrderTest(){
        db.insertOrder(1,1,(float)69.69, null, "8am");
        Order obj = (Order)db.getOrderByID(1);
        assertEquals(1,obj.getID());
    }

    @Test
    public void insertItemTest(){
        db.insertItem("Name",1,"Category",(float)69.69,100, "Description", null);
        Item obj = (Item)db.getItemByID(1);
        assertEquals(1,obj.getID());
    }

    /**************************Update Tests*********************************/
    @Test
    public void updateRequestTest(){
        db.insertRequest("username","name",null,null,"0");
        ArrayList<requestObj> obj = (ArrayList<requestObj>)db.getRequests();
        obj.get(0).setName("NewName");
        db.updateRequest(obj.get(0));
        obj = (ArrayList<requestObj>)db.getRequests();
        assertEquals("NewName",obj.get(0).getName());
    }

    @Test
    public void updateCustomerTest(){
        db.insertCustomer("Name","Gender","Email","Password","867-5309",100,100,100,null);
        Customer obj = (Customer)db.getCustomerByID(1);
        obj.setName("NewName");
        db.updateCustomer(obj);
        obj = (Customer)db.getCustomerByID(1);
        assertEquals("NewName",obj.getName());
    }

    @Test
    public void updateMerchantTest() {
        db.insertMerchant("Name", "Gender", "Email", "Password", "867-5309", 100, 100, 100, null, null);
        Merchant obj = (Merchant) db.getMerchantByID(1);
        obj.setName("NewName");
        db.updateMerchant(obj);
        obj = (Merchant) db.getMerchantByID(1);
        assertEquals("NewName", obj.getName());
    }

    @Test
    public void updateAdminTest(){
        db.insertAdmin("Name", "Gender", "Email", "Password", "867-5309", 100, 100, 100, null, null);
        Merchant obj = (Merchant) db.getAdminByID(1);
        obj.setName("NewName");
        db.updateAdmin(obj);
        obj = (Merchant) db.getAdminByID(1);
        assertEquals("NewName", obj.getName());
    }

    @Test
    public void updateRestaurantTest(){
        db.insertRestaurant("Name",null,null,1,"8am","8pm",100,100);
        Restaurant obj = (Restaurant)db.getRestaurantByID(1);
        obj.setName("NewName");
        db.updateRestaurant(obj);
        obj = (Restaurant) db.getRestaurantByID(1);
        assertEquals("NewName", obj.getName());
    }

    @Test
    public void updateOrderTest(){
        db.insertOrder(1,1,(float)69.69, null, "8am");
        Order obj = (Order)db.getOrderByID(1);
        obj.setOrderTime("9am");
        db.updateOrder(obj);
        obj = (Order) db.getOrderByID(1);
        assertEquals("9am", obj.getOrderTime());
    }

    @Test
    public void updateItemTest(){
        db.insertItem("Name",1,"Category",(float)69.69,100, "Description", null);
        Item obj = (Item)db.getItemByID(1);
        obj.setName("NewName");
        db.updateItem(obj);
        obj = (Item) db.getItemByID(1);
        assertEquals("NewName", obj.getName());
    }

    /**************************Delete Tests*********************************/
    @Test
    public void deleteCustomerTest(){
        db.insertCustomer("Name","Gender","Email","Password","867-5309",100,100,100,null);
        db.deleteCustomer(1);
        Object obj = db.getCustomerByID(1);
        assertEquals(null,obj);
    }

    @Test
    public void deleteMerchantTest(){
        db.insertMerchant("Name","Gender","Email","Password","867-5309",100,100,100,null,null);
        db.deleteMerchant(1);
        Object obj = db.getMerchantByID(1);
        assertEquals(null,obj);
    }

    @Test
    public void deleteAdminTest(){
        db.insertAdmin("Name","Gender","Email","Password","867-5309",100,100,100,null,null);
        db.deleteAdmin(1);
        Object obj = db.getAdminByID(1);
        assertEquals(null,obj);
    }

    @Test
    public void deleteRestaurantTest(){
        db.insertRestaurant("Name",null,null,1,"8am","8pm",100,100);
        db.deleteRestaurant(1);
        Object obj = db.getRestaurantByID(1);
        assertEquals(null,obj);
    }

    @Test
    public void deleteOrderTest(){
        db.insertOrder(1,1,(float)69.69, null, "8am");
        db.deleteOrder(1);
        Object obj = db.getOrderByID(1);
        assertEquals(null,obj);
    }

    @Test
    public void deleteItemTest(){
        db.insertItem("Name",1,"Category",(float)69.69,100, "Description", null);
        db.deleteItem(1);
        Object obj = db.getItemByID(1);
        assertEquals(null,obj);
    }

}
