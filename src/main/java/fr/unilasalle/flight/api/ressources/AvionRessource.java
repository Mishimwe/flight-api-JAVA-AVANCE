package fr.unilasalle.flight.api.ressources;
import fr.unilasalle.flight.api.beans.Plane;
import fr.unilasalle.flight.api.repositories.AvionRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/plane")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AvionRessource extends GenericRessource {

    @Inject
    AvionRepository repository;

    @Inject
    Validator validator;

    @GET
    public Response getPlanes() {
        var list = repository.listAll();
        return getOr404(list);
    }
    @GET
    @Path("/operator/{operator}")
    public Response getPlaneByOperator(@PathParam("operator") String operator) {
        var passengers = repository.findByOperator(operator);
        return getOr404(passengers);
    }

    @GET
    @Path("/id/{id}")
    public Response getPlaneById(@PathParam("id") Long id) {
        var plane = repository.findPlaneById(id);
        return getOr404(plane);
    }
    @POST
    @Transactional
    public Response createPlane(Plane plane) {
        var violations = validator.validate(plane);
        if (!violations.isEmpty()) {
            return Response.status(400).entity(new ErrorWrapper(violations)).build();
        }

        try {
            repository.persistAndFlush(plane);
            return Response.ok().entity(plane).build();
        } catch (PersistenceException e) {
            return Response.serverError().entity(new ErrorWrapper("Erreur lors de l'enregistrement")).build();
        }
    }
}
