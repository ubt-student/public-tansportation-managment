package project.transportation.publictransportationmanager.dto;

import lombok.*;
import project.transportation.publictransportationmanager.model.Route;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RouteDTO {
    private Long routeId;

    private String source;

    private String destination;

    private List<StationDTO> stops;

    private LinkedList<Long> distance;

    public RouteDTO() {
    }

    public RouteDTO(Route dto) {
    }
}
