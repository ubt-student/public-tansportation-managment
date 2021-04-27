package project.transportation.publictransportationmanager.service;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.dto.BookingDTO;
import project.transportation.publictransportationmanager.dto.BusDTO;
import project.transportation.publictransportationmanager.email.EmailSender;
import project.transportation.publictransportationmanager.email.EmailService;
import project.transportation.publictransportationmanager.email.Mail;
import project.transportation.publictransportationmanager.exception.UnprocessableEntityException;
import project.transportation.publictransportationmanager.model.*;
import project.transportation.publictransportationmanager.repo.BookingRepository;
import project.transportation.publictransportationmanager.repo.BusRepository;
import project.transportation.publictransportationmanager.repo.StationRepository;
import project.transportation.publictransportationmanager.repo.UserRepository;
import project.transportation.publictransportationmanager.security.AuthenticationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BusService busService;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private EmailService mailclient;
    @Autowired
    private EmailSender mailbuilder;

    public String sendMail(Mail mail) {
        if (mail == null)
            mailclient.send("devasheesh.roy@wittybrains.com", "1st mail");
        else
            mailclient.send(mail.getTo(), mail.getContent());
        return "Mail sent";
    }

    public String sendEditedMail() {
        return "";
    }

    public BookingDTO get(Long id) {
        if (bookingRepository.findById(id) == null) {
            return null;
        } else {
            return new BookingDTO(bookingRepository.findById(id).get());
        }
    }

    public String add(BookingDTO bookingDTO) {
        LocalDate date;
        List<Seat> seats;
        int numberOfSeats;
        Bus bus;
        Station source;
        Station destination;
        validateAddBooking(bookingDTO);

        Useri user = authenticationService.getAuthenticatedUser();
        System.out.println("user authenticated");
        if (user == null) return "invalid user";


        if (!bookingDTO.getSeatDTO().isEmpty()) {

            seats = bookingDTO.getSeatDTO().stream().map(Seat::new).collect(Collectors.toList());
        } else {
            return "Mention the seats for booking";
        }
        if (bookingDTO.getNumberOfSeats() > 0) {
            numberOfSeats = bookingDTO.getNumberOfSeats();
        }
        if (bookingDTO.getBusDTO().equals(null)) {
            return "Fill bus";
        } else {
            bus = busRepository.findById(bookingDTO.getBusDTO().getBusId()).get();

        }
        if (bookingDTO.getFrom().equals(null)) {
            return "Fill source station";
        } else {
            source = stationRepository.findById(bookingDTO.getFrom().getStationId()).get();
        }
        if (bookingDTO.getDestination().equals(null)) {
            return "Fill destination station";
        } else {
            destination = stationRepository.findById(bookingDTO.getDestination().getStationId()).get();
        }

        if (bookingDTO.getDateOfJourney().equals(null)) {
            return "Fill date of journey";
        } else {
            date = bookingDTO.getDateOfJourney();
        }

        return add(bus.getBusId(), seats, source.getStationId(), destination.getStationId(), user, date);

    }

    public String add(Long busId, List<Seat> seats, Long sourceId, Long destinationId, Useri user,
                      LocalDate date) {
        int numberOfSeats = seats.size();
        Bus bus = busRepository.findById(busId).get();
        int available = busService.availableSeats(busId, sourceId, destinationId, date);
        if (available > numberOfSeats) {
            List<Seat> totalSeat = bus.getSeat();
            if (totalSeat.isEmpty())
                return "no seats in db";
            List<Seat> seatsBooked = busService.bookedSeats(busId, sourceId, destinationId, date);
            List<Seat> newSeatBooking = new ArrayList<>();
            int i = 0;
            for (Seat seat : seats) {
                if (!totalSeat.contains(seat)) {
                    return "Invalid Seat";
                }
            }

            for (Seat seat : seatsBooked) {
                if (seats.contains(seat)) {
                    return "Seats already occupied , SeatId : " + seat.getSeatId();
                }
            }

            System.out.println("before distance");
            /*
             * for (Seat seat : totalSeat) { if (!seatsBooked.isEmpty() && seatsBooked !=
             * null) { if (!seatsBooked.contains(seat)) { newSeatBooking.add(seat); i++; if
             * (i == numberOfSeats) break; } } else { newSeatBooking.add(seat); i++; if (i
             * == numberOfSeats) break; } }
             */

            LocalDate dateOfBooking = LocalDate.now();
            int distance = distanceBetween(busId, sourceId, destinationId);
            Booking booking = new Booking(bus, stationRepository.findById(sourceId).get(),
                    stationRepository.findById(destinationId).get(), seats, date, user, dateOfBooking);
            bookingRepository.save(booking);
            Long id = bus.getBusId();

            int x = bus.getSeatsBooked() + numberOfSeats;
            bus.setSeatsBooked(x);
            busRepository.save(bus);

            return "Booking Confirmed " + booking.getBookingId() + " From: " + booking.getFrom().getStationName()
                    + " To: " + booking.getDestination().getStationName();
        }
        return "Bus Seat unavailable";
    }

    private int distanceBetween(Long busId, Long sourceId, Long destinationId) {
        validateDistanceBetween(busId, sourceId, destinationId);
        Bus bus = busRepository.findById(busId).get();
        if (bus == null)
            return 0;
        Station source = stationRepository.findById(sourceId).get();
        if (source == null)
            return 0;
        Station destination = stationRepository.findById(destinationId).get();
        if (destination == null)
            return 0;
        int initialIndex = 0, i = 0, destinationIndex = 0;
        Long initialDistance = null, finalDistance = null;
        List<Station> stationList = bus.getRoute().getStops();
        List<Long> distance = bus.getRoute().getDistance();
        for (Station stn : stationList) {
            Long stationId = stn.getStationId();
            if (stationId == sourceId) {
                {
                    initialIndex = i;
                    initialDistance = distance.get(i);
                }
            }
            if (stationId == destinationId) {
                destinationIndex = i;
                finalDistance = distance.get(i);
            }
            i++;
        }
        int travelDistance = (int) (finalDistance - initialDistance);
        return travelDistance;
    }


    public List<Seat> getSeats(Long busId, int numberOfSeats, Integer sourceId, Integer destinationId) {
        return null;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream().map(m -> new BookingDTO(m)).collect(Collectors.toList());
    }

    public String saveOrUpdateBooking(BookingDTO booking) {
        System.out.println("new booking");
        Booking book = new Booking(booking);
        bookingRepository.save(book);
        return "Successful booking";
    }

    public String saveOrUpdateBooking(Long id, BookingDTO booking) // Remove ID
    {
        System.out.println("new/update single booking");
        id = booking.getBookingId();
        if (id == null) {
            bookingRepository.save(new Booking(booking));
            return "Booking added";
        } else {
            Booking bk = new Booking(booking);
            Booking book = bookingRepository.findById(id).get();
            book.setBus(bk.getBus());
            book.setDateOfJourney(bk.getDateOfJourney());
            book.setDestination(bk.getDestination());
            book.setFare(bk.getFare());
            book.setFrom(bk.getFrom());
            book.setSeat(bk.getSeat());
            book.setUser(bk.getUser());
            bookingRepository.save(book);
            return "booking updated";
        }
    }

    public String delete(List<BookingDTO> acc) {
        List<Booking> booking = acc.stream().map(s -> new Booking(s)).collect(Collectors.toList());
        bookingRepository.deleteAll(booking);
        return "Multiple Successful deletion";
    }

    public String deleteById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        if (booking == null)
            return "Booking id does not exists";
        int size = booking.getSeat().size();
        Bus bus = booking.getBus();

        bus.setSeatsBooked(bus.getSeatsBooked() - size);
        busService.saveAndUpdateBus(new BusDTO(bus));
        bookingRepository.deleteById(bookingId);

        return "Successful deletion";
    }

    public void validateAddBooking(BookingDTO bookingDTO) {
        logger.info("To validate Booking details");
        if (bookingDTO.getBusDTO().getBusId() <= 0) {
            logger.error("Please enter valid booking Id.");
            throw new UnprocessableEntityException("Please enter valid booking Id.");
        }
        if (bookingDTO.getDateOfJourney() == null || bookingDTO.getDateOfJourney().isBefore(LocalDate.now())) {
            logger.error("Please enter valid date.");
            throw new UnprocessableEntityException("Please enter valid date.");
        }
        if (bookingDTO.getSeatDTO() == null || bookingDTO.getSeatDTO().isEmpty()) {
            logger.error("Please enter valid seats.");
            throw new UnprocessableEntityException("Please enter valid seats.");
        }
        if (bookingDTO.getFrom() == null) {
            logger.error("Please enter valid source.");
            throw new UnprocessableEntityException("Please enter valid source.");
        }
        if (bookingDTO.getDestination() == null) {
            logger.error("Please enter valid destination.");
            throw new UnprocessableEntityException("Please enter valid destination.");
        }
        logger.info("Validation of Booking success");
    }

    private void validateDistanceBetween(Long busId, Long sourceId, Long destinationId) {
        if (busId < 1) {
            logger.error("Please enter valid bus Id");
            throw new UnprocessableEntityException("Please enter valid bus Id.");
        }
        if (sourceId < 1) {
            logger.error("Please enter valid source Id.");
            throw new UnprocessableEntityException("Please enter valid source Id.");
        }
        if (destinationId < 1) {
            logger.error("Please enter valid destination Id.");
            throw new UnprocessableEntityException("Please enter valid destination Id.");
        }
    }
}
