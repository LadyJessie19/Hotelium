package com.hotelium.limbo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.BookingRequestDTO;
import com.hotelium.limbo.dto.response.BookingResponseDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.model.Booking;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.model.User;
import com.hotelium.limbo.repository.BookingRepository;
import com.hotelium.limbo.repository.RoomRepository;
import com.hotelium.limbo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import com.hotelium.limbo.mapper.BookingMapper;

@Service
public class BookingService extends GenericService<Booking, Long, BookingRequestDTO, BookingResponseDTO> {

    public BookingService(BookingRepository repository, BookingMapper mapper) {
        super(repository, mapper, Booking.class, BookingRequestDTO.class, BookingResponseDTO.class);
    }

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    private void validateBookingDTO(BookingRequestDTO bookingDTO) {
        if (bookingDTO == null) {
            throw new IllegalArgumentException("BookingDTO cannot be null");
        }

        if (bookingDTO.getCheckIn() == null || bookingDTO.getCheckOut() == null) {
            throw new IllegalArgumentException("Check-in and check-out dates are required");
        }

        if (bookingDTO.getCheckIn().after(bookingDTO.getCheckOut())) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        if (bookingDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        if (bookingDTO.getRoomIds() == null || bookingDTO.getRoomIds().isEmpty()) {
            throw new IllegalArgumentException("At least one room must be specified");
        }
    }

    public Booking createBookingTwo(String userId, BookingRequestDTO bookingDto) {

        this.validateBookingDTO(bookingDto);

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Booking booking = new Booking();
            booking.setUser(user.get());
            booking.setCreditCard(bookingDto.getCreditCard());
            booking.setCheckIn(bookingDto.getCheckIn());
            booking.setCheckOut(bookingDto.getCheckOut());

            List<Room> rooms = new ArrayList<>();
            for (Long roomId : bookingDto.getRoomIds()) {
                int activeBookings = roomRepository.countActiveBookingsForRoom(roomId, bookingDto.getCheckIn(),
                        bookingDto.getCheckOut());
                if (activeBookings > 0) {
                    throw new RuntimeException("Room " + roomId + " is not available for the selected dates");
                }
                Room room = roomRepository.findById(roomId)
                        .orElseThrow(() -> new EntityNotFoundException("Room not found with id " + roomId));
                rooms.add(room);
            }
            booking.setRooms(rooms);

            return bookingRepository.saveAndFlush(booking);

        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public void deleteBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            User user = booking.get().getUser();
            user.getBookings().remove(booking.get());
            userRepository.save(user);

            List<Room> rooms = booking.get().getRooms();
            for (Room room : rooms) {
                room.getBookings().remove(booking.get());
                roomRepository.save(room);
            }
            bookingRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Booking not found with id " + id);
        }
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id " + id));

        if (booking == null) {
            throw new EntityNotFoundException("Booking not found with id " + id);
        }

        if (booking.getIsCanceled()) {
            throw new RuntimeException("Booking is already canceled.");
        }

        if (new Date().after(booking.getDeadline())) {
            throw new IllegalStateException("Cannot cancel booking after the deadline!");
        }

        booking.setIsCanceled(true);
        bookingRepository.save(booking);
    }

    public List<Booking> findBookingsByUserAndStatus(Long userId, Boolean isCanceled) {
        if (isCanceled == null) {
            return bookingRepository.findByUserId(userId);
        } else {
            return bookingRepository.findByUserIdAndIsCanceled(userId, isCanceled);
        }
    }
}
