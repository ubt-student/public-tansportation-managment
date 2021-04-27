package project.transportation.publictransportationmanager.model;


import org.joda.time.LocalDate;
import project.transportation.publictransportationmanager.dto.CancellationDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cancellationId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Booking booking;
    private int refund;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "seat_cancellation",
            joinColumns = {@JoinColumn(name = "cancellationId")}, inverseJoinColumns = {@JoinColumn(name = "seatId")})
    private List<Seat> seatsCancelled;
    private LocalDate dateOfCancellation;
    @ManyToOne
    private Useri user;

    public Cancellation() {

    }

    public Cancellation(Long cancellationId, Booking booking, int refund, List<Seat> seatsCancelled, LocalDate dateOfCancellation) {
        this.cancellationId = cancellationId;
        if (booking != null)
            this.booking = booking;
        this.refund = refund;
        this.seatsCancelled = seatsCancelled;
        this.dateOfCancellation = dateOfCancellation;
    }

    public Cancellation(CancellationDTO cancellationDTO) {
    }

    public Long getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(Long cancellationId) {
        this.cancellationId = cancellationId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getRefund() {
        return refund;
    }

    public void setRefund(int refund) {
        this.refund = refund;
    }

    public List<Seat> getSeatsCancelled() {
        return seatsCancelled;
    }

    public void setSeatsCancelled(List<Seat> seatsCancelled) {
        this.seatsCancelled = seatsCancelled;
    }

    public LocalDate getDateOfCancellation() {
        return dateOfCancellation;
    }

    public void setDateOfCancellation(LocalDate dateOfCancellation) {
        this.dateOfCancellation = dateOfCancellation;
    }

    public Useri getUser() {
        return user;
    }

    public void setUser(Useri user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cancellation other = (Cancellation) obj;
        if (cancellationId == null) {
            if (other.cancellationId != null)
                return false;
        } else if (!cancellationId.equals(other.cancellationId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cancellation [cancellationId=" + cancellationId + ", booking=" + booking + ", refund=" + refund
                + ", seatsCancelled=" + seatsCancelled + ", dateOfCancellation=" + dateOfCancellation + "]";
    }
}
