package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Reservation;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class ReservationRepository implements PanacheRepositoryBase<Reservation, Long> {

    public List<Reservation> findtous() {
        return listAll();
    }

    public List<Reservation> findByFlightId(Long flightId) {
        return find("flight.id", flightId).list();
    }

    // Add any other methods you may need...

}
