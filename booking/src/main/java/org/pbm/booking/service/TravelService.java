package org.pbm.booking.service;

import lombok.RequiredArgsConstructor;
import org.pbm.booking.domain.model.Travel;
import org.pbm.booking.repository.TravelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    @Transactional
    public List<Travel> getTravels() {
        return travelRepository.getRandomTravels();
    }

    @Transactional
    public List<Travel> getTravelsByMunicipalityId(Long municipalityIdFrom, Long municipalityIdTo){
        return travelRepository.getTravelsByRouteMunicipalityIdFromAndMunicipalityIdTo(municipalityIdFrom, municipalityIdTo);
    }

    @Transactional
    public void registerTravel(Travel travel){
        travelRepository.save(travel);
    }

}
