package project.transportation.publictransportationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.dto.SeatDTO;
import project.transportation.publictransportationmanager.model.Seat;
import project.transportation.publictransportationmanager.repo.SeatRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public SeatDTO get(Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (!seat.isPresent())
            return null;
        return new SeatDTO(seat.get());
    }

    public List<SeatDTO> getAll() {
        System.out.println("In seat get all 2");
        return (seatRepository.findAll().stream().map(s -> new SeatDTO(s)).collect(Collectors.toList()) != null)
                ? (seatRepository.findAll().stream().map(s -> new SeatDTO(s)).collect(Collectors.toList()))
                : null;
    }

    public String addOrUpdateSeat(Long id, SeatDTO seat) {
        SeatDTO seatdto = new SeatDTO(seatRepository.findById(id).get());
        if (seatdto == null)
            seatdto.setSeatId(id);
        seatdto.setArmyQuota(seat.isArmyQuota());
        seatdto.setLadiesQuota(seat.isLadiesQuota());
        seatdto.setOldQuota(seat.isOldQuota());
        seatdto.setPhysicalQuota(seat.isPhysicalQuota());
        seatdto.setType(seat.getType());
        seatRepository.save(new Seat(seatdto));
        return "";
    }

    public String add(SeatDTO seat) {

        if (seat != null) {
            seatRepository.save(new Seat(seat));
            return "Saved Successfully";
        }
        return "Seat invalid";
    }


    public String delete(Long id) {
        if (!seatRepository.findById(id).isPresent())
            return "No Seat on given id";
        seatRepository.delete(seatRepository.findById(id).get());
        return "Seat Successfully deleted";
    }

    public String delete(List<SeatDTO> seatdto) {
        if (seatdto.isEmpty())
            return "List is Empty";
        seatRepository.deleteAll(seatdto.stream().map(s -> new Seat(s)).collect(Collectors.toList()));
        return "Multiple Seats deleted ";
    }


}
