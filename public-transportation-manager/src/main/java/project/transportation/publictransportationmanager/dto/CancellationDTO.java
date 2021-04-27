package project.transportation.publictransportationmanager.dto;

import lombok.*;
import org.joda.time.LocalDate;
import project.transportation.publictransportationmanager.model.Booking;
import project.transportation.publictransportationmanager.model.Cancellation;
import project.transportation.publictransportationmanager.model.Seat;
import project.transportation.publictransportationmanager.model.Useri;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CancellationDTO {
    private Long cancellationId;
    private BookingDTO bookingDTO;
    private int refund;
    private List<SeatDTO> seatsCancelled;
    private LocalDate dateOfCancellation;
    private UserDTO userDTO;

    public CancellationDTO(Cancellation cancellation) {
        Booking booking = cancellation.getBooking();
        List<Seat> seatsCancelled = cancellation.getSeatsCancelled();
        Useri user = cancellation.getUser();
        this.cancellationId = cancellation.getCancellationId();
        this.dateOfCancellation = cancellation.getDateOfCancellation();
        this.refund = cancellation.getRefund();
        if (booking != null)
            this.bookingDTO = new BookingDTO(booking);
        if (seatsCancelled != null)
            this.seatsCancelled = seatsCancelled.stream().map(SeatDTO::new).collect(Collectors.toList());
        if (user != null)
            this.userDTO = new UserDTO(user);
    }
}
