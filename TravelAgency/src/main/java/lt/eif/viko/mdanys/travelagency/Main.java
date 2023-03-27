package lt.eif.viko.mdanys.travelagency;

import lt.eif.viko.mdanys.travelagency.model.Order;
import lt.eif.viko.mdanys.travelagency.model.Traveler;
import lt.eif.viko.mdanys.travelagency.model.Trip;
import lt.eif.viko.mdanys.travelagency.util.JaxbUtil;
import lt.eif.viko.mdanys.travelagency.util.MessageUtil;
import javax.jms.JMSException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JMSException{

        Trip trip1 = new Trip("TravelXDFast", "Explore Egypt", "Egypt", "1 Week", 500);
        Trip trip2 = new Trip("OMGTrips", "Asia Wonders", "Thailand", "2 Weeks", 1000);
        Traveler traveler1 = new Traveler("John", "Smith");
        Traveler traveler2 = new Traveler("Marie", "Smith");
        Order order1 = new Order("2023-03-01", List.of(traveler1, traveler2), List.of(trip1, trip2));
      //  JaxbUtil.convertToXML(order1);
       MessageUtil.messageSender(String.valueOf(JaxbUtil.convertToXML(order1)));
       // System.out.println(order1);

    }
}