package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Towns", schema = "Airport", catalog = "")
public class TownsEntity {
    private int id;
    private String name;
    private Collection<FlightsEntity> flightsById_departure;
    private Collection<FlightsEntity> flightsById_arrival;

    public TownsEntity(){}

    public TownsEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TownsEntity that = (TownsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "townsByDepartureTownId")
    public Collection<FlightsEntity> getFlightsById_departure() {
        return flightsById_departure;
    }

    public void setFlightsById_departure(Collection<FlightsEntity> flightsById_departure) {
        this.flightsById_departure = flightsById_departure;
    }

    @OneToMany(mappedBy = "townsByArrivalTownId")
    public Collection<FlightsEntity> getFlightsById_arrival() {
        return flightsById_arrival;
    }

    public void setFlightsById_arrival(Collection<FlightsEntity> flightsById_arrival) {
        this.flightsById_arrival = flightsById_arrival;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = "+id+"\t"+" town name = "+name+"\n");
        return sb.toString();
    }
}
