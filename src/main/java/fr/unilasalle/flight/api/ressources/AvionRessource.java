package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Avion;
import fr.unilasalle.flight.api.repositories.AvionRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/Avions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AvionRessource extends GenericResource{
    @Inject
    AvionRepository repository;
    @GET
    public Response getPlanes(){
        var list=repository.listAll();
        return  getOr404(list);
    }

}
