package org.pbm.booking.service;

import lombok.RequiredArgsConstructor;
import org.pbm.booking.domain.model.Route;
import org.pbm.booking.domain.model.Tickets;
import org.pbm.booking.repository.RouteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    @Transactional
    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @Transactional
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }
}
