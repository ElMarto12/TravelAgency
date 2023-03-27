package lt.eif.viko.mdanys.travelagency.util;

import lt.eif.viko.mdanys.travelagency.model.Order;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class JaxbUtil {

    public static StringWriter convertToXML(Order order){

        StringWriter stringXML = new StringWriter();

        try{
            JAXBContext context = JAXBContext.newInstance(Order.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            // OutputStream os = new FileOutputStream("generated.xml");

            //Output as file orders.xml
            marshaller.marshal(order, new File("orders.xml"));

            //Output as String value
            marshaller.marshal(order, stringXML);

        }catch (/*FileNotFoundExeption | */ JAXBException e){
            throw new RuntimeException(e);
        }
        return stringXML;
    }
}
