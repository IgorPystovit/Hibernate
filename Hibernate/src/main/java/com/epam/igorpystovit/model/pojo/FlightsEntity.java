package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "Flights", schema = "Airport", catalog = "")
public class FlightsEntity {
    private int id;
    private Integer companyId;
    private int departureTownId;
    private int arrivalTownId;
    private Date departureDate;
    private Time departureTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private int planeId;
    private BigDecimal price;
    private CompaniesEntity companiesByCompanyId;
    private TownsEntity townsByDepartureTownId;
    private TownsEntity townsByArrivalTownId;
    private PlanesCompaniesEntity planesCompaniesByPlaneId;
    private Collection<OrdersEntity> ordersById;

    private DateTimeParser dateTimeParser = new DateTimeParser();

    public FlightsEntity(){}
    public FlightsEntity(int id, Integer companyId, int departureTownId, int arrivalTownId,
                         String departureDate, String departureTime, String arrivalDate, String arrivalTime, int planeId, long price) {
        this.id = id;
        this.companyId = companyId;
        this.departureTownId = departureTownId;
        this.arrivalTownId = arrivalTownId;
        this.departureDate = dateTimeParser.dateParser(departureDate);
        this.departureTime = dateTimeParser.timeParser(departureTime);
        this.arrivalDate = dateTimeParser.dateParser(arrivalDate);
        this.arrivalTime = dateTimeParser.timeParser(arrivalTime);
        this.planeId = planeId;
        this.price = new BigDecimal(price);
        System.out.println(departureDate+" "+arrivalDate+" "+departureTime+" "+arrivalTime);
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
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "departure_town_id")
    public int getDepartureTownId() {
        return departureTownId;
    }

    public void setDepartureTownId(int departureTownId) {
        this.departureTownId = departureTownId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arrival_town_id")
    public int getArrivalTownId() {
        return arrivalTownId;
    }

    public void setArrivalTownId(int arrivalTownId) {
        this.arrivalTownId = arrivalTownId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "departure_date")
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "departure_time")
    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arrival_date")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arrival_time")
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
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
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightsEntity that = (FlightsEntity) o;

        if (id != that.id) return false;
        if (departureTownId != that.departureTownId) return false;
        if (arrivalTownId != that.arrivalTownId) return false;
        if (planeId != that.planeId) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null)
            return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (arrivalDate != null ? !arrivalDate.equals(that.arrivalDate) : that.arrivalDate != null) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + departureTownId;
        result = 31 * result + arrivalTownId;
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + planeId;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id",insertable = false,updatable = false)
    public CompaniesEntity getCompaniesByCompanyId() {
        return companiesByCompanyId;
    }

    public void setCompaniesByCompanyId(CompaniesEntity companiesByCompanyId) {
        this.companiesByCompanyId = companiesByCompanyId;
    }

    @ManyToOne
    @JoinColumn(name = "departure_town_id", referencedColumnName = "id",insertable = false,updatable = false)
    public TownsEntity getTownsByDepartureTownId() {
        return townsByDepartureTownId;
    }

    public void setTownsByDepartureTownId(TownsEntity townsByDepartureTownId) {
        this.townsByDepartureTownId = townsByDepartureTownId;
    }

    @ManyToOne
    @JoinColumn(name = "arrival_town_id", referencedColumnName = "id",insertable = false,updatable = false)
    public TownsEntity getTownsByArrivalTownId() {
        return townsByArrivalTownId;
    }

    public void setTownsByArrivalTownId(TownsEntity townsByArrivalTownId) {
        this.townsByArrivalTownId = townsByArrivalTownId;
    }

    @ManyToOne
    @JoinColumn(name = "plane_id", referencedColumnName = "id",insertable = false,updatable = false)
    public PlanesCompaniesEntity getPlanesCompaniesByPlaneId() {
        return planesCompaniesByPlaneId;
    }

    public void setPlanesCompaniesByPlaneId(PlanesCompaniesEntity planesCompaniesByPlaneId) {
        this.planesCompaniesByPlaneId = planesCompaniesByPlaneId;
    }

    @OneToMany(mappedBy = "flightsByFlightId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id = "+id+"\t"+" Company id = "+companyId+"\t"+" Departure town id = "+departureTownId+"\t"+
                " Arrival town id = "+arrivalTownId+"\t"+" Departure date = "+departureDate+"\t"+" Departure time = "+departureTime+"\t"+
                " Arrival date = "+arrivalDate+"\t"+" Arrival time = "+arrivalTime+"\t"+" Plane id = "+ planeId +"\t"+" Price = "+price);
        return sb.toString();
    }
}
