package project.transportation.publictransportationmanager.dto;

import lombok.*;
import project.transportation.publictransportationmanager.model.Seat;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SeatDTO {
    private Long seatId;
    private boolean oldQuota;
    private boolean ladiesQuota;
    private boolean physicalQuota;
    private boolean armyQuota;
    private String type;
    private String seatName;

    public SeatDTO(Seat s) {
    }
}
