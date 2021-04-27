package project.transportation.publictransportationmanager.model;


import org.joda.time.LocalDate;
import project.transportation.publictransportationmanager.dto.BookingDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId", nullable = true)
    private Useri user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seat_booking", joinColumns = {@JoinColumn(name = "bookingId")}, inverseJoinColumns = {
            @JoinColumn(name = "seatId")
    })
    private List<Seat> seat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "Source_booking")
    @JoinColumn(name = "stationId", nullable = true)
    private Station from;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "Destination_booking")
    @JoinColumn(name = "stationId", nullable = true)
    private Station destination;

    private int fare;
    private LocalDate dateOfJourney;
    private LocalDate dateOfBooking;

    public Booking() {

    }

    public Booking(Long bookingId, Bus busId, Useri user, List<Seat> seat, Station from, Station destination,
                   int fare, LocalDate dateOfJourney, LocalDate dateOfBooking) {
        this.bookingId = bookingId;
        this.bus = busId;
        this.user = user;
        this.seat = seat;
        this.from = from;
        this.destination = destination;
        this.fare = fare;
        this.dateOfJourney = dateOfJourney;
        this.dateOfBooking = dateOfBooking;
    }

    public Booking(Bus bus, Station from, Station destination, List<Seat> seat, LocalDate dateOfJourney, Useri user,
                   LocalDate dateOfBooking) {
        this.user = user;
        this.bus = bus;
        this.from = from;
        this.dateOfBooking = dateOfBooking;
        this.destination = destination;
        this.dateOfJourney = dateOfJourney;
        if (!seat.isEmpty()) {
            this.seat = seat;
        }

    }

    public Booking(BookingDTO booking) {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Useri getUser() {
        return user;
    }

    public void setUser(Useri user) {
        this.user = user;
    }

    public List<Seat> getSeat() {
        return seat;
    }

    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public LocalDate getDateOfJourney() {
        return dateOfJourney;
    }

    public void setDateOfJourney(LocalDate dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bus=" + bus +
                ", user=" + user +
                ", seat=" + seat +
                ", from=" + from +
                ", destination=" + destination +
                ", fare=" + fare +
                ", dateOfJourney=" + dateOfJourney +
                ", dateOfBooking=" + dateOfBooking +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (bookingId == null) {
            if (other.bookingId != null)
                return false;
        } else if (!bookingId.equals(other.bookingId))
            return false;
        return true;
    }
}

