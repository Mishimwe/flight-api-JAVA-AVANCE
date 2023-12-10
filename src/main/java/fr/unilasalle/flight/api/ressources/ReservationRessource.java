package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Reservation;
import fr.unilasalle.flight.api.repositories.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Validator;

import java.util.List;


@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationRessource extends GenericRessource {
    @Inject
    ReservationRepository reservationRepository;

    @Inject
    Validator validator;

    @POST
    @Transactional
    public Response addReservation(@Valid Reservation reservation) {
        var violations = validator.validate(reservation);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new GenericRessource.ErrorWrapper(violations)).build();
        }

        try {
            reservationRepository.persistAndFlush(reservation);
            return Response.status(201).entity(reservation).build(); // 201 Created
        } catch (PersistenceException e) {
            return Response.serverError().entity(new GenericRessource.ErrorWrapper("Error during reservation creation")).build();
        }
    }
    @GET
    public Response getreservations() {
        var list = reservationRepository.listAll();
        return getOr404(list);
    }

    @GET
    @Path("/{id}")
    public Response getPassengerById(@PathParam("id") Long id) {
        var passengers = reservationRepository.findById(id);
        return getOr404(passengers);
    }

    @DELETE
    @Path("/{reservationId}")
    @Transactional
    public Response deleteReservation(@PathParam("reservationId") Long reservationId) {
        Reservation existingReservation = reservationRepository.findById(reservationId);

        if (existingReservation == null) {
            return Response.status(404).entity(new GenericRessource.ErrorWrapper("Reservation not found")).build();
        }

        try {
            reservationRepository.delete(existingReservation);
            return Response.ok().build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new GenericRessource.ErrorWrapper("Error during reservation deletion")).build();
        }
    }
}
