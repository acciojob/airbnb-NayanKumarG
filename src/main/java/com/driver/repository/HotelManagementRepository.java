package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class HotelManagementRepository {

    HashMap<String , Hotel> hoteldb = new HashMap<>();

    HashMap<Integer , User> userdb = new HashMap<>();

    HashMap<String , Booking> bookingdb = new HashMap<>();

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

    public Hotel bookARoom(Booking booking) {

        String hotelname = booking.getHotelName();
        return hoteldb.getOrDefault(hotelname,null);
    }

    public void addBooking(Booking booking) {
        bookingdb.put(booking.getBookingId() , booking);
    }

    public int getBookings(Integer aadharCard) {
        int count = 0;
        for(String key:bookingdb.keySet())
        {
            if(bookingdb.get(key).getBookingAadharCard()==aadharCard)
            {
                count++;
            }
        }
        return count;
    }

    public Hotel getHotel(String hotelName) {
        return hoteldb.get(hotelName);
    }
}
