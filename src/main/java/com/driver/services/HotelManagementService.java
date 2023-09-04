package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.coyote.http11.Constants.a;

@Service
public class HotelManagementService {

//    @Autowired
    HotelManagementRepository hotelManagementRepository = new HotelManagementRepository();
    public boolean addHotel(Hotel hotel) {
        if(hotel.getHotelName().equals(null) || hotel==null) return false;
        else return hotelManagementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {

        Integer adharno = user.getaadharCardNo();
        hotelManagementRepository.addUser(user);
        return adharno;
    }

    public String getHotelWithMostFacilities() {
        List<Hotel> listofhotels = hotelManagementRepository.getHotelWithMostFacilities();
        List<String> hotels = new ArrayList<>();
        List<Hotel> checklist = new ArrayList<>();
        if(listofhotels.size()==0) return "";
        for(Hotel hotel:listofhotels)
        {
            List<Facility> facility = hotel.getFacilities();
            if(facility.size()!=0)
            {
                checklist.add(hotel);
//                hotels.add(hotel.getHotelName());
            }
        }
        if(checklist.size()==0) return "";
        Collections.sort(checklist , new Comparator<Hotel>() {
            @Override
            public int compare(Hotel a, Hotel b) {
                return Integer.compare(a.getFacilities().size(), b.getFacilities().size());
            }
        });

        String listofhotelsname = "";
        int count = 1;
        listofhotelsname+=checklist.get(0).getHotelName();
        for(int i=1 ; i<checklist.size() ; i++)
        {
            if(checklist.get(i).getFacilities().size()==checklist.get(i-1).getFacilities().size())
            {
                listofhotelsname+=checklist.get(i).getHotelName();
            }
            else break;
        }
//        Collections.sort(hotels);
//        return hotels;
        return listofhotelsname;
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

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelManagementRepository.getHotel(hotelName);
        List<Facility> available = hotel.getFacilities();
        newFacilities.removeAll(Collections.singleton(available.retainAll(newFacilities)));
        for(Facility facility:newFacilities)
        {
            available.add(facility);
        }
        hotel.setFacilities(available);
        return hotel;

    }
}
