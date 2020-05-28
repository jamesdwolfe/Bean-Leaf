package com.example.beanleaf;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTest {
    Item i = new Item(1, "Black Coffee", 2, "Coffee",
            (float) 2.49, 200, "A cup of black coffee", new ArrayList<String>());

    @Test
    public void getId() {
        assertEquals(i.getID(), 1);
    }

    @Test
    public void setId() {
        i.setID(2);
        assertEquals(i.getID(), 2);
    }

    @Test
    public void getName() {
        assertEquals(i.getName(), "Black Coffee");
    }

    @Test
    public void setName() {
        i.setName("Black Tea");
        assertEquals(i.getName(), "Black Tea");
    }

    @Test
    public void getRestaurant() {
        assertEquals(i.getRestaurant(), 2);
    }

    @Test
    public void setRestaurant() {
        i.setRestaurant(3);
        assertEquals(i.getRestaurant(), 3);
    }

    @Test
    public void getCategory() {
        assertEquals(i.getCategory(), "Coffee");
    }

    @Test
    public void setCategory() {
        i.setCategory("Tea");
        assertEquals(i.getCategory(), "Tea");
    }

    @Test
    public void getPrice() {
        assertEquals(i.getPrice(), 2.49, 0.001);
    }

    @Test
    public void setPrice() {
        i.setPrice((float) 1.99);
        assertEquals(i.getPrice(), 1.99, 0.001);
    }

    @Test
    public void getCaffeineIntake() {
        assertEquals(i.getCaffeineIntake(), 200);
    }

    @Test
    public void setCaffeineIntake() {
        i.setCaffeineIntake(150);
        assertEquals(i.getCaffeineIntake(), 150);
    }

    @Test
    public void getDescription() {
        assertEquals(i.getDescription(), "A cup of black coffee");
    }

    @Test
    public void setDescription() {
        i.setDescription("A cup of black tea");
        assertEquals(i.getDescription(), "A cup of black tea");
    }

    @Test
    public void getIngredients() {
        assertEquals(i.getIngredients().size(), 0);
    }

    @Test
    public void setIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tea leaves");
        i.setIngredients(ingredients);
        assertEquals(i.getIngredients().size(), 1);
        assertEquals(i.getIngredients().get(0), "Tea leaves");
    }
}
