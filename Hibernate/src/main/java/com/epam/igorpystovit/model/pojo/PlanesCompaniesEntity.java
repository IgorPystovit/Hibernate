package com.epam.igorpystovit.model.pojo;

import com.epam.igorpystovit.model.dao.implementations.CompaniesDAOImpl;
import com.epam.igorpystovit.model.dao.implementations.PlanesCompaniesDAOImpl;
import com.epam.igorpystovit.model.dao.implementations.PlanesDAOImpl;
import com.epam.igorpystovit.model.pojo.sessionmanager.SessionManager;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

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

    public PlanesCompaniesEntity(){}
    public PlanesCompaniesEntity(int id, int companyId, int planeId, Integer availableSeats) {
        this.id = id;
        this.companyId = companyId;
        this.planeId = planeId;
        this.availableSeats = availableSeats;
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
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "plane_id")
    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "available_seats")
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
    @JoinColumn(name = "company_id", referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    public CompaniesEntity getCompaniesByCompanyId() {
        return companiesByCompanyId;
    }

    public void setCompaniesByCompanyId(CompaniesEntity companiesByCompanyId) {
        this.companiesByCompanyId = companiesByCompanyId;
    }

    @ManyToOne
    @JoinColumn(name = "plane_id", referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    public PlanesEntity getPlanesByPlaneId() {
        return planesByPlaneId;
    }

    public void setPlanesByPlaneId(PlanesEntity planesByPlaneId) {
        this.planesByPlaneId = planesByPlaneId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" id = "+id+"\t"+" Company id = "+companyId+"\t"+" Plane id = "+
                planeId +"\t"+" Available seats = "+availableSeats+"\n");
        return sb.toString();
    }
}
