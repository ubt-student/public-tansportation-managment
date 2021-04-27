package project.transportation.publictransportationmanager.model;


import lombok.NoArgsConstructor;
import project.transportation.publictransportationmanager.dto.RouteDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long routeId;
    private String source;
    private String destination;
    private LinkedList<Long> distance;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "Route_stations")
    private List<Station> stops = new ArrayList<>();

    public Route(Long routeId, String source, String destination, List<Station> stops,
                 LinkedList<Long> distance) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.distance = distance;
    }

    public Route(RouteDTO routeDto) {
        this.routeId = routeDto.getRouteId();

        this.source = routeDto.getSource();

        this.destination = routeDto.getDestination();
        this.distance = routeDto.getDistance();
        if (routeDto.getStops() != null) {
            this.stops = routeDto.getStops().stream().map(s -> new Station(s)).collect(Collectors.toList());
        }
    }

    public void addStationInList(Station addStation) {
        stops.add(addStation);
    }

    public void addAfterStation(Station station1, Station addStation) {
        int index = this.stops.indexOf(station1);
        this.stops.add(index, addStation);
    }

    public int getTotalDistance(Station start, Station end) {
        int startIndex = stops.indexOf(start);
        int endIndex = stops.indexOf(end);
        int totalDistance = 0;
        int i;
        for (i = startIndex; i <= endIndex; i++) {
            totalDistance += distance.get(i);
        }
        return totalDistance;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LinkedList<Long> getDistance() {
        return distance;
    }

    public void setDistance(LinkedList<Long> distance) {
        this.distance = distance;
    }

    public List<Station> getStops() {
        return stops;
    }

    public void setStops(List<Station> stops) {
        this.stops = stops;
    }
}

