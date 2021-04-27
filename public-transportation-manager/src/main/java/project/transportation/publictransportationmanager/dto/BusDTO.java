package project.transportation.publictransportationmanager.dto;

import lombok.*;
import org.joda.time.DateTime;
import project.transportation.publictransportationmanager.model.Bus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BusDTO {
    private Long busId;

    private String plateName;

    private int seatsBooked;

    private int totalSeats;

    private int busType;

    private DateTime dailyStartTime;

    private DateTime dailyStopTime;

    private List<Integer> fare;

    private RouteDTO route;

    private List<SeatDTO> seat;

    public BusDTO(Bus s) {
    }
}
