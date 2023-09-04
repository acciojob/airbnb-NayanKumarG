package com.driver.repository;

import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HotelManagementRepository {

    HashMap<String , Hotel> hoteldb = new HashMap<>();

    public boolean addHotel(Hotel hotel) {
        if(hoteldb.containsKey(hotel.getHotelName())) return false;
        hoteldb.put(hotel.getHotelName(), hotel);
        return true;
    }
}
