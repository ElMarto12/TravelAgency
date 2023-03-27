package lt.eif.viko.mdanys.travelagency.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Holds information about Travel Agency Orders.
 *
 * @author Martynas Danys
 * @since 1.0
 */
@XmlRootElement
@XmlType(propOrder = {"id", "orderDate", "travelers", "trips"})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "AgencyOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String orderDate;

    @XmlElementWrapper(name="travelers")
    @XmlElement(name="traveler")
    @ManyToMany(targetEntity = Traveler.class, cascade = CascadeType.ALL)
    private List<Traveler> travelers;

    @XmlElementWrapper(name="trips")
    @XmlElement(name="trip")
    @OneToMany(targetEntity = Trip.class, cascade = CascadeType.ALL)
    private List<Trip> trips;

    public Order(){}

    public Order(int id, String orderDate, List<Traveler> travelers, List<Trip> trips) {
        this.id = id;
        this.orderDate = orderDate;
        this.travelers = travelers;
        this.trips = trips;
    }

    public Order(String orderDate, List<Traveler> travelers, List<Trip> trips) {
        this.orderDate = orderDate;
        this.travelers = travelers;
        this.trips = trips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Traveler> getTravelers() {
        return travelers;
    }

    public void setTravelers(List<Traveler> travelers) {
        this.travelers = travelers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return String.format("Order:\n\t Date = %s\n\t" +
                        "Travelers: \n\t%s" +
                        "Trips:\n\t\t%s",
                this.orderDate,
                this.travelers,
                this.trips,
                constructTravelerString(),
                constructTripString());
    }

   private String constructTravelerString(){
        String resultTraveler = "";
        for(Traveler traveler : this.travelers){
            resultTraveler += traveler.toString();
        }
        return resultTraveler;
    }
    private String constructTripString(){
        String resultTrip = "";
        for(Trip trip : this.trips){
            resultTrip += trip.toString();
        }
        return resultTrip;
    }
}
