package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Passenger;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;
import jakarta.transaction.Transactional;
import java.util.List;

@Model
public class PassengerRepository implements PanacheRepositoryBase<Passenger, Long> {

    public List<Passenger> findAll(String operatorParameter) {
        return find("operator", operatorParameter).list();
    }

    public List<Passenger> findtouspassenger() {
        return listAll();
    }

    @Transactional
    public void updatePassengerInformation(Long passengerId, String firstname, String surname, String emailAddress) {
        Passenger existingPassenger = findById(passengerId);

        if (existingPassenger != null) {
            existingPassenger.setFirstname(firstname);
            existingPassenger.setSurname(surname);
            existingPassenger.setEmailAddress(emailAddress);
            persistAndFlush(existingPassenger);
        }
    }

    public Passenger findPassengerById(Long passengerId) {
        return findById(passengerId);
    }

    public List<Passenger> findPassengersBySurname(String surname) {
        return list("surname", surname);
    }
    public List<Passenger> findPassengersById(String id) {
        return list("id", id);
    }
}
