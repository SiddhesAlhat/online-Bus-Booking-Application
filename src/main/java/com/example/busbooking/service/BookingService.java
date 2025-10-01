package com.example.busbooking.service;

import com.example.busbooking.dto.request.BookingRequest;
import com.example.busbooking.dto.response.BookingResponse;
import com.example.busbooking.dto.response.TicketResponse;
import com.example.busbooking.enums.BookingStatus;
import com.example.busbooking.enums.PaymentStatus;
import com.example.busbooking.enums.SeatStatus;
import com.example.busbooking.exception.BadRequestException;
import com.example.busbooking.exception.ResourceNotFoundException;
import com.example.busbooking.model.*;
import com.example.busbooking.repository.*;
import com.example.busbooking.util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private BusRepository busRepository;
    
    @Autowired
    private SeatRepository seatRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private QRCodeGenerator qrCodeGenerator;
    
    @Transactional
    public BookingResponse createBooking(BookingRequest request, String userPhone) {
        User user = userRepository.findByPhone(userPhone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
        
        Seat seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
        
        // Validate seat availability
        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            throw new BadRequestException("Seat is not available");
        }
        
        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setSeat(seat);
        booking.setTravelDate(request.getTravelDate());
        booking.setFare(bus.getFare());
        booking.setStatus(BookingStatus.PENDING);
        
        booking = bookingRepository.save(booking);
        
        // Update seat status
        seat.setStatus(SeatStatus.BOOKED);
        seatRepository.save(seat);
        
        // Update bus available seats
        bus.setAvailableSeats(bus.getAvailableSeats() - 1);
        busRepository.save(bus);
        
        // Create payment
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(bus.getFare());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus(PaymentStatus.SUCCESS); // Simulated payment success
        paymentRepository.save(payment);
        
        // Update booking status
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        
        // Generate ticket
        Ticket ticket = new Ticket();
        ticket.setBooking(booking);
        ticket.setTicketNumber("TKT" + System.currentTimeMillis());
        
        try {
            String qrData = String.format("Ticket: %s, Bus: %s, Seat: %s, Date: %s",
                    ticket.getTicketNumber(), bus.getBusNumber(), seat.getSeatNumber(), request.getTravelDate());
            ticket.setQrCode(qrCodeGenerator.generateQRCode(qrData));
        } catch (Exception e) {
            // QR code generation failed, but booking is still valid
            ticket.setQrCode(null);
        }
        
        ticketRepository.save(ticket);
        
        // Prepare response
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setBusNumber(bus.getBusNumber());
        response.setSource(bus.getRoute().getSource());
        response.setDestination(bus.getRoute().getDestination());
        response.setSeatNumber(seat.getSeatNumber());
        response.setTravelDate(booking.getTravelDate());
        response.setFare(booking.getFare());
        response.setStatus(booking.getStatus());
        response.setBookingTime(booking.getBookingTime());
        response.setTicketNumber(ticket.getTicketNumber());
        
        return response;
    }
    
    public List<BookingResponse> getUserBookings(String userPhone) {
        User user = userRepository.findByPhone(userPhone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        List<Booking> bookings = bookingRepository.findByUserOrderByBookingTimeDesc(user);
        List<BookingResponse> responses = new ArrayList<>();
        
        for (Booking booking : bookings) {
            BookingResponse response = new BookingResponse();
            response.setId(booking.getId());
            response.setBusNumber(booking.getBus().getBusNumber());
            response.setSource(booking.getBus().getRoute().getSource());
            response.setDestination(booking.getBus().getRoute().getDestination());
            response.setSeatNumber(booking.getSeat() != null ? booking.getSeat().getSeatNumber() : null);
            response.setTravelDate(booking.getTravelDate());
            response.setFare(booking.getFare());
            response.setStatus(booking.getStatus());
            response.setBookingTime(booking.getBookingTime());
            
            if (booking.getTicket() != null) {
                response.setTicketNumber(booking.getTicket().getTicketNumber());
            }
            
            responses.add(response);
        }
        
        return responses;
    }
    
    public TicketResponse getTicket(Long bookingId, String userPhone) {
        User user = userRepository.findByPhone(userPhone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (!booking.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Unauthorized access to booking");
        }
        
        Ticket ticket = booking.getTicket();
        if (ticket == null) {
            throw new ResourceNotFoundException("Ticket not found");
        }
        
        TicketResponse response = new TicketResponse();
        response.setTicketNumber(ticket.getTicketNumber());
        response.setPassengerName(user.getName());
        response.setBusNumber(booking.getBus().getBusNumber());
        response.setSource(booking.getBus().getRoute().getSource());
        response.setDestination(booking.getBus().getRoute().getDestination());
        response.setSeatNumber(booking.getSeat() != null ? booking.getSeat().getSeatNumber() : null);
        response.setTravelDate(booking.getTravelDate());
        response.setDepartureTime(booking.getBus().getDepartureTime());
        response.setFare(booking.getFare());
        response.setQrCode(ticket.getQrCode());
        
        return response;
    }
    
    @Transactional
    public void cancelBooking(Long bookingId, String userPhone) {
        User user = userRepository.findByPhone(userPhone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (!booking.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Unauthorized access to booking");
        }
        
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BadRequestException("Booking is already cancelled");
        }
        
        // Update booking status
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        
        // Release seat
        if (booking.getSeat() != null) {
            Seat seat = booking.getSeat();
            seat.setStatus(SeatStatus.AVAILABLE);
            seatRepository.save(seat);
            
            // Update bus available seats
            Bus bus = booking.getBus();
            bus.setAvailableSeats(bus.getAvailableSeats() + 1);
            busRepository.save(bus);
        }
    }
}
