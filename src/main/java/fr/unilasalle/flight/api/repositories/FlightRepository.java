package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Flight;

import fr.unilasalle.flight.api.beans.Plane;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class FlightRepository implements PanacheRepositoryBase<Flight, Long> {

    public List<Flight> findByDestination(String destination) {
        return find("destination", destination).list();
    }

    public Flight findflightstous(Long id) {
        return findByIdOptional(id).orElse(null);
    }
    public void deleteFlight(Flight flight) {
        delete(flight);
    }
    public Flight findFlightById(Long id) {
        return findByIdOptional(id).orElse(null);
    }
}
