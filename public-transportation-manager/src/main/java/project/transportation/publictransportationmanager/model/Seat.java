package project.transportation.publictransportationmanager.model;

import lombok.NoArgsConstructor;
import project.transportation.publictransportationmanager.dto.SeatDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;


    private boolean oldQuota;


    private boolean ladiesQuota;

    private boolean physicalQuota;

    private boolean armyQuota;

    private String type; // single lower ,single upper, double lower, double upper ,sitting

    private String seatName;

    public Seat(boolean oldQuota, boolean ladiesQuota, boolean physicalQuota, boolean armyQuota) {
        this.oldQuota = oldQuota;
        this.ladiesQuota = ladiesQuota;
        this.physicalQuota = physicalQuota;
        this.armyQuota = armyQuota;
    }

    public Seat(boolean oldQuota, boolean ladiesQuota, boolean physicalQuota, boolean armyQuota, String type) {
        this(oldQuota, ladiesQuota, physicalQuota, armyQuota);
        this.type = type;
    }

    public Seat(SeatDTO seatDTO) {

    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public boolean isOldQuota() {
        return oldQuota;
    }

    public void setOldQuota(boolean oldQuota) {
        this.oldQuota = oldQuota;
    }

    public boolean isLadiesQuota() {
        return ladiesQuota;
    }

    public void setLadiesQuota(boolean ladiesQuota) {
        this.ladiesQuota = ladiesQuota;
    }

    public boolean isPhysicalQuota() {
        return physicalQuota;
    }

    public void setPhysicalQuota(boolean physicalQuota) {
        this.physicalQuota = physicalQuota;
    }

    public boolean isArmyQuota() {
        return armyQuota;
    }

    public void setArmyQuota(boolean armyQuota) {
        this.armyQuota = armyQuota;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seatId == null) ? 0 : seatId.hashCode());
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
        Seat other = (Seat) obj;
        if (seatId == null) {
            if (other.seatId != null)
                return false;
        } else if (!seatId.equals(other.seatId))
            return false;
        return true;
    }
}
