package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class HotelManagementService {

//    @Autowired
    HotelManagementRepository hotelManagementRepository = new HotelManagementRepository();
    public boolean addHotel(Hotel hotel) {
        if(hotel.getHotelName()=="" || hotel==null) return false;
        else return hotelManagementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {

        Integer adharno = user.getaadharCardNo();
        hotelManagementRepository.addUser(user);
        return adharno;
    }

    public List<String> getHotelWithMostFacilities() {
        List<Hotel> listofhotels = hotelManagementRepository.getHotelWithMostFacilities();
        List<String> hotels = new ArrayList<>();
        if(listofhotels.size()==0) return hotels;
        for(Hotel hotel:listofhotels)
        {
            List<Facility> facility = hotel.getFacilities();
            if(facility.size()!=0) hotels.add(hotel.getHotelName());
        }

        Collections.sort(hotels);
        return hotels;
    }

    public int bookARoom(Booking booking) {
        Hotel hotel = hotelManagementRepository.bookARoom(booking);
        int availablerooms = hotel.getAvailableRooms();
        if(booking.getNoOfRooms()>availablerooms) return -1;
        int amountpernight = hotel.getPricePerNight();
        int amounttobepaid = booking.getNoOfRooms()*amountpernight;
        UUID uuid = UUID.randomUUID();
        String bookingId = String.valueOf(uuid);
        booking.setBookingId(bookingId);
        booking.setAmountToBePaid(amounttobepaid);
        hotelManagementRepository.addBooking(booking);
        return amounttobepaid;
    }

    public int getBookings(Integer aadharCard) {
        return hotelManagementRepository.getBookings(aadharCard);
    }
}
