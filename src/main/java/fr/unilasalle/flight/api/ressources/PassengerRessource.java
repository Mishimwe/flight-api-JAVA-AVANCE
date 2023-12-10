package fr.unilasalle.flight.api.ressources;
import fr.unilasalle.flight.api.beans.Passenger;
import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.beans.Reservation;
import fr.unilasalle.flight.api.repositories.AvionRepository;
import fr.unilasalle.flight.api.repositories.PassengerRepository;
import fr.unilasalle.flight.api.repositories.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.persistence.PersistenceException;


@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PassengerRessource extends GenericRessource {

    @Inject
    PassengerRepository passengerRepository;
    @Inject
    ReservationRepository reservationRepository;

    @Inject
    Validator validator;

    @GET
    public Response getPassengers() {
        var list = passengerRepository.listAll();
        return getOr404(list);
    }

    @POST
    @Transactional
    public Response createPassenger(Passenger passenger) {
        var violations = validator.validate(passenger);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }

        try {
            passengerRepository.persistAndFlush(passenger);
            return Response.ok().entity(passenger).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper("Erreur lors de l'enregistrement")).build();
        }
    }


    @PUT
    @Path("/{passengerId}")
    @Transactional
    public Response updatePassenger(
            @PathParam("passengerId") Long passengerId,
            @Valid Passenger updatedPassenger
    ) {
        var violations = validator.validate(updatedPassenger);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }

        try {
            passengerRepository.updatePassengerInformation(
                    passengerId,
                    updatedPassenger.getFirstname(),
                    updatedPassenger.getSurname(),
                    updatedPassenger.getEmail_address()
            );

            return Response.ok().build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper("Erreur lors de la mise à jour du passager")).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getPassenger(@PathParam("id") Long id) {
        Passenger passenger = Passenger.findById(id);

        if (passenger != null) {
            return Response.ok().entity(passenger).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @GET
    @Path("/surname/{surname}")
    public Response getPassengerBySurname(@PathParam("surname") String surname) {
        var passengers = passengerRepository.findPassengersBySurname(surname);
        return getOr404(passengers);
    }
}
