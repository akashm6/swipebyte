package com.bitestream.project;

import com.bitestream.project.entity.Restaurant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantTest {

    @Test
    void testRestaurantConstructorAndGetters() {
        List<String> cuisines = new ArrayList<String>();
        cuisines.add("Italian");
        cuisines.add("Indian");

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Pizza Place");
        restaurant.setAddress("123 Main St");
        assertEquals("Pizza Place", restaurant.getName());
        assertEquals("123 Main St", restaurant.getAddress());
    }

    @Test
    void testSetters() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Burger Joint");
        assertEquals("Burger Joint", restaurant.getName());
    }
}
