package com.epam.igorpystovit.model.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Orders", schema = "Airport", catalog = "")
public class OrdersEntity {
    private int id;
    private int clientId;
    private int flightId;
    private ClientsEntity clientsByClientId;
    private FlightsEntity flightsByFlightId;

    public OrdersEntity(){}
    public OrdersEntity(int id, int clientId, int flightId) {
        this.id = id;
        this.clientId = clientId;
        this.flightId = flightId;
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
    @Column(name = "client_id")
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "flight_id")
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (flightId != that.flightId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + flightId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id",insertable = false,updatable = false)
    public ClientsEntity getClientsByClientId() {
        return clientsByClientId;
    }

    public void setClientsByClientId(ClientsEntity clientsByClientId) {
        this.clientsByClientId = clientsByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id",insertable = false,updatable = false)
    public FlightsEntity getFlightsByFlightId() {
        return flightsByFlightId;
    }

    public void setFlightsByFlightId(FlightsEntity flightsByFlightId) {
        this.flightsByFlightId = flightsByFlightId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" id = "+id+"\t"+" Client id ="+clientId+"\t"+" Flight id = "+flightId+"\n");
        return sb.toString();
    }
}
