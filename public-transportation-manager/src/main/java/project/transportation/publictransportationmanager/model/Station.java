package project.transportation.publictransportationmanager.model;


import org.springframework.lang.Nullable;
import project.transportation.publictransportationmanager.dto.StationDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stationId;
    private String stationName;
    @OneToMany(fetch = FetchType.LAZY)
    @Nullable
    private List<Bus> busList;

    public Station(Long stationId, String stationName, @Nullable List<Bus> busList) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.busList = busList;
    }

    public Station() {

    }

    public Station(StationDTO stationDTO) {

    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Nullable
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(@Nullable List<Bus> busList) {
        this.busList = busList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return stationId.equals(station.stationId) && Objects.equals(stationName, station.stationName) && Objects.equals(busList, station.busList);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", busList=" + busList +
                '}';
    }
}
