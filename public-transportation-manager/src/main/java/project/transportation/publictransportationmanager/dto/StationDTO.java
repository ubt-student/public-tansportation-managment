package project.transportation.publictransportationmanager.dto;

import lombok.*;
import project.transportation.publictransportationmanager.model.Station;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StationDTO {
    private Long stationId;
    private String stationName;
    private List<BusDTO> busList;

    public StationDTO() {
    }

    public StationDTO(Station station) {
        this.stationId = station.getStationId();
        this.stationName = station.getStationName();
        if (station.getBusList() != null) {
            this.busList = station.getBusList().stream().map(b -> new BusDTO(b)).collect(Collectors.toList());
        }
    }

    public StationDTO(Long stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }
}
