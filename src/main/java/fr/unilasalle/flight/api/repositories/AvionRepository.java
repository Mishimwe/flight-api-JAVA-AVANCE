package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Avion;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

@Model
public class AvionRepository implements PanacheRepositoryBase<Avion,Long> {
    public List<Avion> findByOperator(String operatorParameter){
        return find();
    }
}
