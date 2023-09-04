package com.driver.repository;

import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class HotelManagementRepository {

    HashMap<String , Hotel> hoteldb = new HashMap<>();

    HashMap<Integer , User> userdb = new HashMap<>();

    public boolean addHotel(Hotel hotel) {
        if(hoteldb.containsKey(hotel.getHotelName())) return false;
        hoteldb.put(hotel.getHotelName(), hotel);
        return true;
    }

    public void addUser(User user) {

        userdb.put(user.getaadharCardNo() , user);
    }

    public List<Hotel> getHotelWithMostFacilities() {
        return new ArrayList<>(hoteldb.values());
    }
}
