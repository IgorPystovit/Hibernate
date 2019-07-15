package com.epam.igorpystovit.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Companies", schema = "Airport", catalog = "")
public class CompaniesEntity {
    private int id;
    private String name;
    private Collection<FlightsEntity> flightsById;
    private Collection<PlanesCompaniesEntity> planesCompaniesById;

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

        CompaniesEntity that = (CompaniesEntity) o;

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

    @OneToMany(mappedBy = "companiesByCompanyId")
    public Collection<FlightsEntity> getFlightsById() {
        return flightsById;
    }

    public void setFlightsById(Collection<FlightsEntity> flightsById) {
        this.flightsById = flightsById;
    }

    @OneToMany(mappedBy = "companiesByCompanyId")
    public Collection<PlanesCompaniesEntity> getPlanesCompaniesById() {
        return planesCompaniesById;
    }

    public void setPlanesCompaniesById(Collection<PlanesCompaniesEntity> planesCompaniesById) {
        this.planesCompaniesById = planesCompaniesById;
    }
}
