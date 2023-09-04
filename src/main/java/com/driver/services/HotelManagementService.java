package com.driver.services;

import com.driver.model.Hotel;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelManagementService {

    @Autowired
    HotelManagementRepository hotelManagementRepository;
    public boolean addHotel(Hotel hotel) {
        if(hotel.getHotelName()=="" || hotel==null) return false;
        else return hotelManagementRepository.addHotel(hotel);
    }
}
