package com.driver.services;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HotelManagementService {

    @Autowired
    HotelManagementRepository hotelManagementRepository;
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
}
