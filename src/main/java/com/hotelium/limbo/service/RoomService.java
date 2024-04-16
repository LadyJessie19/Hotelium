package com.hotelium.limbo.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.dto.response.RoomResponseDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.RoomMapper;
import com.hotelium.limbo.model.Booking;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.repository.BookingRepository;
import com.hotelium.limbo.repository.HotelRepository;
import com.hotelium.limbo.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomService extends GenericService<Room, Long, RoomRequestDTO, RoomResponseDTO> {
    public RoomService(RoomRepository repository, RoomMapper mapper) {
        super(repository, mapper, Room.class, RoomRequestDTO.class, RoomResponseDTO.class);
    }

    @Autowired
    private RoomRepository repository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomMapper mapper;

    public Room createRoom(Long hotelId, RoomRequestDTO room) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        if (hotel.isPresent()) {
            room.setHotel(hotel.get());
            Room newRoom = mapper.dtoToEntity(room, Room.class);
            return repository.save(newRoom);
        } else {
            throw new EntityNotFoundException("Hotel not found");
        }
    }

    public void deleteRoom(Long id) {
        Optional<Room> room = repository.findById(id);
        if (room.isPresent()) {
            Set<Booking> bookings = room.get().getBookings();
            for (Booking booking : bookings) {
                booking.getRooms().remove(room.get());
                bookingRepository.save(booking);
            }
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Room not found with id " + id);
        }
    }

    public boolean isRoomAvailable(Long roomId, Date checkIn, Date checkOut) {
        int activeBookings = repository.countActiveBookingsForRoom(roomId, checkIn, checkOut);
        return activeBookings == 0;
    }
}
