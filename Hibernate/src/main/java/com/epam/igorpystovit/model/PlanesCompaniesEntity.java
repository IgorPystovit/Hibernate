package com.epam.igorpystovit.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Planes_Companies", schema = "Airport", catalog = "")
public class PlanesCompaniesEntity {
    private int id;
    private int companyId;
    private int planeId;
    private Integer availableSeats;
    private Collection<FlightsEntity> flightsById;
    private CompaniesEntity companiesByCompanyId;
    private PlanesEntity planesByPlaneId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "company_id",insertable = false,updatable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "plane_id",insertable = false,updatable = false)
    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "available_seats",insertable = false,updatable = false)
    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanesCompaniesEntity that = (PlanesCompaniesEntity) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (planeId != that.planeId) return false;
        if (availableSeats != null ? !availableSeats.equals(that.availableSeats) : that.availableSeats != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + companyId;
        result = 31 * result + planeId;
        result = 31 * result + (availableSeats != null ? availableSeats.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "planesCompaniesByPlaneId")
    public Collection<FlightsEntity> getFlightsById() {
        return flightsById;
    }

    public void setFlightsById(Collection<FlightsEntity> flightsById) {
        this.flightsById = flightsById;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    public CompaniesEntity getCompaniesByCompanyId() {
        return companiesByCompanyId;
    }

    public void setCompaniesByCompanyId(CompaniesEntity companiesByCompanyId) {
        this.companiesByCompanyId = companiesByCompanyId;
    }

    @ManyToOne
    @JoinColumn(name = "plane_id", referencedColumnName = "id", nullable = false)
    public PlanesEntity getPlanesByPlaneId() {
        return planesByPlaneId;
    }

    public void setPlanesByPlaneId(PlanesEntity planesByPlaneId) {
        this.planesByPlaneId = planesByPlaneId;
    }
}
