package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Companies", schema = "Airport")
public class CompaniesEntity {
    private int id;
    private String name;
    private Collection<PlanesCompaniesEntity> planesCompaniesById;

    public CompaniesEntity(){}
    public CompaniesEntity(int id, String name) {
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
    public Collection<PlanesCompaniesEntity> getPlanesCompaniesById() {
        return planesCompaniesById;
    }

    public void setPlanesCompaniesById(Collection<PlanesCompaniesEntity> planesCompaniesById) {
        this.planesCompaniesById = planesCompaniesById;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID = "+id+"\t"+" Company name = "+name+"\n");
        return sb.toString();
    }
}
