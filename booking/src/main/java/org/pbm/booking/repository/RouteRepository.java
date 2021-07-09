package org.pbm.booking.repository;

import org.pbm.booking.domain.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RouteRepository extends JpaRepository<Route, Long> {
}
