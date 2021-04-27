package project.transportation.publictransportationmanager.dto;


import lombok.*;
import org.joda.time.LocalDate;
import project.transportation.publictransportationmanager.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BookingDTO {
    private Long bookingId;
    private BusDTO busDTO;
    private List<SeatDTO> seatDTO;
    private StationDTO from;
    private StationDTO destination;
    private int fare;
    private LocalDate dateOfJourney;
    private int numberOfSeats;
    private LocalDate dateOfBooking;

    public BookingDTO(Booking booking) {
        Bus bus = booking.getBus();
        Useri user = booking.getUser();
        List<Seat> seat = booking.getSeat();
        Station from = booking.getFrom();
        Station destination = booking.getDestination();
        this.dateOfBooking = booking.getDateOfBooking();
        this.bookingId = booking.getBookingId();
        if (bus != null)
            this.busDTO = new BusDTO(bus);

        if (seat != null)
            this.seatDTO = seat.stream().map(s -> new SeatDTO(s)).collect(Collectors.toList());
        if (from != null)
            this.from = new StationDTO(from);
        if (destination != null)
            this.destination = new StationDTO(destination);
        this.fare = booking.getFare();
        this.dateOfJourney = booking.getDateOfJourney();
        if (this.seatDTO != null)
            this.numberOfSeats = booking.getSeat().size();
    }
}
