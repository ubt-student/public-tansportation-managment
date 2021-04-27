package project.transportation.publictransportationmanager.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.dto.BookingDTO;
import project.transportation.publictransportationmanager.email.Mail;
import project.transportation.publictransportationmanager.model.Seat;
import project.transportation.publictransportationmanager.model.Useri;
import project.transportation.publictransportationmanager.service.BookingService;
import project.transportation.publictransportationmanager.service.BusService;
import project.transportation.publictransportationmanager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BusService busService;

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    /**
     * get booking by id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public BookingDTO getBooking(@PathVariable(value = "id") Long id) {
        return bookingService.get(id);
    }

    @PostMapping(value = "/mail")
    @ResponseBody
    public String sendMail(@RequestBody Mail mail) {
        return bookingService.sendMail(mail);
    }

    /**
     * Get number of available seats in a particular bus by giving
     * busId,sourceId,destinationId in Get request as Parameters
     *
     * @param busId
     * @param sourceId
     * @param destinationId
     * @return
     */

    @GetMapping("/{busId}/{sourceId}/{destinationId}/{date}")
    public int availableSeats(@PathVariable("busId") Long busId, @PathVariable("sourceId") Long sourceId,
                              @PathVariable("destinationId") Long destinationId, @PathVariable("date") String startDate) {
        LocalDate date = new LocalDate(startDate);
        return busService.availableSeats(busId, sourceId, destinationId, date);
    }

    /**
     * Get Request to get all booking list
     *
     * @return
     */

    @GetMapping
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * @return
     */
    @PostMapping("/editedmail")
    public String sendEditedMail() {
        return bookingService.sendEditedMail();
    }

    /**
     * PostRequest to add single booking for admin
     *
     * @param booking
     * @return
     */


    @PostMapping("/new")
    @ResponseBody
    public String addBooking(@RequestBody BookingDTO booking) {
        return bookingService.saveOrUpdateBooking(booking);
    }

    /**
     * save or update bookings
     *
     * @param id
     * @param booking
     * @return
     */

    @PostMapping(value = "/{id}")
    @ResponseBody
    public String saveAndUpdateBooking(@PathVariable(value = "id") Long id, @RequestBody BookingDTO booking) {
        bookingService.saveOrUpdateBooking(id, booking);
        return "";
    }

    /**
     * postMapping to do a booking. The booking details to be sent in responseBody
     * of PostMapping
     *
     * @param bookingDTO
     * @return
     */

    @PostMapping
    @ResponseBody
    public String newBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.add(bookingDTO);
    }

    /**
     * PostMapping to do booking by sending userId, numberOfSeats , busId, sourceId,
     * destinationId in url . Method checks is the numberOfSeats is available or not
     * in given busId. If the number of seats is available ,booking is done.
     *
     * @param userId
     * @param numberOfSeats
     * @param busId
     * @param sourceId
     * @param destinationId
     * @return
     */

    @PostMapping(value = "/{userId}/{numberOfSeats}/{busId}/{sourceId}/{destinationId}/{localDate}")
    @ResponseBody
    public String add(@PathVariable("userId") Long userId, @PathVariable("numberOfSeats") int numberOfSeats,
                      @PathVariable("busId") Long busId, @PathVariable("sourceId") Long sourceId,
                      @PathVariable("destinationId") Long destinationId, @RequestBody List<Seat> seats,
                      @PathVariable(value = "localDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfJourney) {
        Useri user = new Useri(userService.findUserById(userId));
        return bookingService.add(busId, seats, sourceId, destinationId, user, dateOfJourney);
    }

    /**
     * deletes the given list of bookings explicitly as given in responseBody of
     * delete Mapping
     *
     * @param acc
     * @return
     */

    @DeleteMapping
    @ResponseBody
    public String deleteAll(@RequestBody List<BookingDTO> acc) {
        return bookingService.delete(acc);
    }

    /**
     * Deletes booking by id (Cancellation of ticket). Also adds an entry in
     * cancellation ticket.
     *
     * @param bookingId
     * @return
     */

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteById(@PathVariable("id") Long bookingId) {
        System.out.println("in delete by id booking");
        return bookingService.deleteById(bookingId);
    }

}
