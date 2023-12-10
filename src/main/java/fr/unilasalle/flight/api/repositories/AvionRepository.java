package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Plane;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class AvionRepository implements PanacheRepositoryBase<Plane,Long> {
    public List<Plane> findByOperator(String operatorParameter){
        return find("operator",operatorParameter).list();
    }
    public Plane findPlaneById(Long id) {

        return findById(id);
    }

    public List<Plane> findPlaneByImmat(String immatriculation){
        return find("immatriculation",immatriculation).list();
    }


    public List<Plane> findtous() {
        return listAll();
    }
}
