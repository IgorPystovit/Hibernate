package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "Flights", schema = "Airport")
public class FlightsEntity {
    private int id;
    private int departureTownId;
    private int arrivalTownId;
    private Date departureDate;
    private Time departureTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private int planeCompanyId;
    private double price;
    private TownsEntity townsByDepartureTownId;
    private TownsEntity townsByArrivalTownId;
    private PlanesCompaniesEntity planesCompaniesByPlaneId;
    private Collection<OrdersEntity> ordersById;

    private DateTimeParser dateTimeParser = new DateTimeParser();

    public FlightsEntity(){}
    public FlightsEntity(int id, int departureTownId, int arrivalTownId,
                         String departureDate, String departureTime, String arrivalDate, String arrivalTime, int planeCompanyId, double price) {
        this.id = id;
        this.departureTownId = departureTownId;
        this.arrivalTownId = arrivalTownId;
        this.departureDate = dateTimeParser.dateParser(departureDate);
        this.departureTime = dateTimeParser.timeParser(departureTime);
        this.arrivalDate = dateTimeParser.dateParser(arrivalDate);
        this.arrivalTime = dateTimeParser.timeParser(arrivalTime);
        this.planeCompanyId = planeCompanyId;
        this.price = price;
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
    public String getDepartureDate() {
        return departureDate.toString();
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = dateTimeParser.dateParser(departureDate);
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "departure_time")
    public String getDepartureTime() {
        return departureTime.toString();
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = dateTimeParser.timeParser(departureTime);
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arrival_date")
    public String getArrivalDate() {
        return arrivalDate.toString();
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = dateTimeParser.dateParser(arrivalDate);
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arrival_time")
    public String getArrivalTime() {
        return arrivalTime.toString();
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = dateTimeParser.timeParser(arrivalTime);
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "plane_id")
    public int getPlaneCompanyId() {
        return planeCompanyId;
    }

    public void setPlaneCompanyId(int planeId) {
        this.planeCompanyId = planeId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        if (planeCompanyId != that.planeCompanyId) return false;
        if (departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null)
            return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (arrivalDate != null ? !arrivalDate.equals(that.arrivalDate) : that.arrivalDate != null) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + departureTownId;
        result = 31 * result + arrivalTownId;
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + planeCompanyId;
        return result;
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

        sb.append("id = "+id+"\t"+" Departure town id = "+departureTownId+"\t"+
                " Arrival town id = "+arrivalTownId+"\t"+" Departure date = "+departureDate+"\t"+" Departure time = "+departureTime+"\t"+
                " Arrival date = "+arrivalDate+"\t"+" Arrival time = "+arrivalTime+"\t"+" Plane id = "+ planeCompanyId +"\t"+" Price = "+price);
        return sb.toString();
    }
}
