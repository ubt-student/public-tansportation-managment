package project.transportation.publictransportationmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.dto.RouteDTO;
import project.transportation.publictransportationmanager.exception.UnprocessableEntityException;
import project.transportation.publictransportationmanager.model.Bus;
import project.transportation.publictransportationmanager.model.Route;
import project.transportation.publictransportationmanager.repo.BusRepository;
import project.transportation.publictransportationmanager.repo.RouteRepository;
import project.transportation.publictransportationmanager.repo.StationRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private StationRepository stationrepository;

    public String add(RouteDTO routeDTO) {
        System.out.println("in Route add");
        // for (RouteDTO a : acc) {
        validateRoute(routeDTO);
        Route route = new Route(routeDTO);
        if (route != null) {
            routeRepository.save(route);
        }
        return "save completed";
    }


    public List<RouteDTO> getAll() {
        System.out.println("Route get all");
        List<Route> routeList = routeRepository.findAll();
        List<RouteDTO> dtoList = new LinkedList<RouteDTO>();

        for (Route dto : routeList) {
            dtoList.add(new RouteDTO(dto));
        }
        return dtoList;
    }

    public RouteDTO get(Long id) {
        System.out.println("Route get");
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            return new RouteDTO(route.get());
        }
        return null;
    }

    public String delete(Long id) {
        System.out.println("Route delete");
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            Route rt = route.get();
            List<Bus> bus = busRepository.findAll();
            if (bus != null) {
                for (Bus bs : bus) {
                    if (bs.getRoute().equals(rt))
                        bs.setRoute(null);
                }
                busRepository.saveAll(bus);
            }
            routeRepository.deleteById(id);
            return "Successful deletion";
        }
        return "No entry";
    }

    public String delete(List<RouteDTO> acc) {
        System.out.println("Route delete all");
        List<Route> routeList = new LinkedList<Route>();
        for (RouteDTO route : acc) {
            if (route.getRouteId() == null) {
                return "Please enter a valid route Id";
            }
            routeList.add(new Route(route));
        }
        routeRepository.deleteAll(routeList);
        return "Multiple deletion successful";
    }

    private void validateRoute(RouteDTO routeDTO) {
        if (routeDTO.getDistance() == null ||
                !(routeDTO.getDistance().size() == routeDTO.getStops().size() || routeDTO.getDistance().size() == routeDTO.getStops().size() - 1)) {
            logger.error("Please enter valid route distance list.");
            throw new UnprocessableEntityException("Please enter valid distance list.");
        }
        if (routeDTO.getStops() == null) {
            logger.error("Please enter valid route distance list.");
            throw new UnprocessableEntityException("Please enter valid distance list.");
        }
    }
}
