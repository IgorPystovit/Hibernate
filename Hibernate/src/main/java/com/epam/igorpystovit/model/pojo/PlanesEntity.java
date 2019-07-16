package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Planes", schema = "Airport", catalog = "")
public class PlanesEntity {
    private int id;
    private String planeName;
    private int capacity;
    private String planeType;
    private Collection<PlanesCompaniesEntity> planesCompaniesById;

    public PlanesEntity(){}

    public PlanesEntity(int id, String planeName, int capacity, String planeType) {
        this.id = id;
        this.planeName = planeName;
        this.capacity = capacity;
        this.planeType = planeType;
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
    @Column(name = "plane_name")
    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "plane_type")
    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanesEntity that = (PlanesEntity) o;

        if (id != that.id) return false;
        if (capacity != that.capacity) return false;
        if (planeName != null ? !planeName.equals(that.planeName) : that.planeName != null) return false;
        if (planeType != null ? !planeType.equals(that.planeType) : that.planeType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (planeName != null ? planeName.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (planeType != null ? planeType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "planesByPlaneId")
    public Collection<PlanesCompaniesEntity> getPlanesCompaniesById() {
        return planesCompaniesById;
    }

    public void setPlanesCompaniesById(Collection<PlanesCompaniesEntity> planesCompaniesById) {
        this.planesCompaniesById = planesCompaniesById;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = "+id+"\t"+"name = "+planeName+"\t"+"capacity = "+capacity+"\t"+"plane type = "+planeType.toString().toLowerCase()+"\n");
        return sb.toString();
    }
}
