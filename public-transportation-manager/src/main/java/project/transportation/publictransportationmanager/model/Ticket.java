package project.transportation.publictransportationmanager.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tickedId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "ticket_passenger")
    private List<Useri> passengers;
    private int fare;
    private String source;
    private String destination;
    private String busName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "busId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bus busId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Transaction trans;

    public Ticket() {

    }

    public Ticket(Long tickedId, int fare, String source, String destination) {
        this.tickedId = tickedId;
        this.fare = fare;
        this.source = source;
        this.destination = destination;
    }

    public Ticket(Long tickedId, String busName, Bus busId, Transaction trans) {
        this.tickedId = tickedId;
        this.busName = busName;
        this.busId = busId;
        this.trans = trans;
    }

    public Long getTickedId() {
        return tickedId;
    }

    public void setTickedId(Long tickedId) {
        this.tickedId = tickedId;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
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

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public Bus getBusId() {
        return busId;
    }

    public void setBusId(Bus busId) {
        this.busId = busId;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((busId == null) ? 0 : busId.hashCode());
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
        Ticket other = (Ticket) obj;
        if (busId == null) {
            if (other.busId != null)
                return false;
        } else if (!busId.equals(other.busId))
            return false;
        return true;
    }
}
