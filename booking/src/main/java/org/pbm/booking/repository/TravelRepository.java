package org.pbm.booking.repository;

import org.pbm.booking.domain.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM travel ORDER BY random() LIMIT 5 ")
    List<Travel> getRandomTravels();

    @Query("SELECT t FROM Travel t WHERE t.route.municipalityIdFrom = :municipalityIdFrom AND t.route.municipalityIdTo = :municipalityIdTo")
    List<Travel> getTravelsByRouteMunicipalityIdFromAndMunicipalityIdTo(Long municipalityIdFrom, Long municipalityIdTo);
}
