package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Towns", schema = "Airport", catalog = "")
public class TownsEntity {
    private int id;
    private String name;
    private Collection<FlightsEntity> flightsById;
    private Collection<FlightsEntity> flightsById_0;

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
    public Collection<FlightsEntity> getFlightsById() {
        return flightsById;
    }

    public void setFlightsById(Collection<FlightsEntity> flightsById) {
        this.flightsById = flightsById;
    }

    @OneToMany(mappedBy = "townsByArrivalTownId")
    public Collection<FlightsEntity> getFlightsById_0() {
        return flightsById_0;
    }

    public void setFlightsById_0(Collection<FlightsEntity> flightsById_0) {
        this.flightsById_0 = flightsById_0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = "+id+"\t"+" town name = "+name+"\n");
        return sb.toString();
    }
}
