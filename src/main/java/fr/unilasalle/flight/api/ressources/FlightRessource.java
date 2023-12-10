package fr.unilasalle.flight.api.ressources;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import fr.unilasalle.flight.api.beans.Flight;
import fr.unilasalle.flight.api.repositories.FlightRepository;

import jakarta.validation.Valid;



@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightRessource extends GenericRessource {

    @Inject
    FlightRepository flightRepository;

    @Inject
    Validator validator;

    @GET
    public Response getflights() {
        var list = flightRepository.listAll();
        return getOr404(list);
    }

    @GET
    @Path("/id/{id}")
    public Response getflightById(@PathParam("id") Long id) {
        var plane = flightRepository.findflightstous(id);
        return getOr404(plane);
    }
    @GET
    @Path("/destination/{destination}")
    public Response getflightByDestination(@PathParam("destination") String destination) {
        var plane = flightRepository.findByDestination(destination);
        return getOr404(plane);
    }
    @POST
    @Transactional
    public Response addFlight(@Valid Flight flight) {
        var violations = validator.validate(flight);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }

        try {
            flightRepository.persistAndFlush(flight);
            return Response.status(201).entity(flight).build(); // 201 Created
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper("Error during flight creation")).build();
        }
    }

    @DELETE
    @Path("/id/{id}")
    @Transactional
    public Response deleteFlightById(@PathParam("id") Long id) {
        var flight = flightRepository.findById(id);

        if (flight == null) {
            return Response.status(404).entity(new ErrorWrapper("Flight not found")).build();
        }

        try {
            flightRepository.delete(flight);
            return Response.ok().entity("Flight and associated reservations deleted successfully").build();
        } catch (PersistenceException e) {
            // Log the exception details
            e.printStackTrace();  // You can replace this with your logging framework
            return Response.serverError().entity(new ErrorWrapper("Error deleting the Flight")).build();
        }
    }

}
