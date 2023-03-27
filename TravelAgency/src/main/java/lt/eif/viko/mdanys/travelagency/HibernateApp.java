package lt.eif.viko.mdanys.travelagency;

import lt.eif.viko.mdanys.travelagency.model.Order;
import lt.eif.viko.mdanys.travelagency.model.Traveler;
import lt.eif.viko.mdanys.travelagency.model.Trip;
import lt.eif.viko.mdanys.travelagency.util.HibernateUtil;
import lt.eif.viko.mdanys.travelagency.util.JaxbUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateApp {
    public static void main(String[] args){

        Trip trip1 = new Trip("EgyptTour", "Pyramids", "Egypt", "2 weeks", 1000);
        Traveler traveler1 = new Traveler("John", "Smith");
        Order order1 = new Order("2023", List.of(traveler1), List.of(trip1));

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(order1);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Order> orders = session.createQuery("from Order", Order.class).list();
            orders.forEach(ordr -> System.out.println());

            System.out.println("---------------------------------");
            orders.forEach(ordr -> JaxbUtil.convertToXML(ordr));

            //server = Server.createTcpServer().start();
            System.in.read();
        }catch (Exception e){
            if(transaction != null){
                System.out.println(e.getMessage());
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            //server.shutdown();
        }
    }
}
