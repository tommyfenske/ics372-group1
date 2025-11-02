package ordersystem;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.codehaus.jackson.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Saver {
    ObjectMapper mapper = new ObjectMapper();

    List<Item> testList = new ArrayList<>();
    String s;

    //Order order = new Order("togo", testList);

    public void save(Object object) {
        try {

            // Getting organisation object as a json string
            String jsonStr = mapper.writeValueAsString(object);
            s = jsonStr;
            // Displaying JSON String on console
            System.out.println(jsonStr);
        }

        // Catch block to handle exceptions
        catch (IOException e) {

            // Display exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }

    public void load() {
        try {

            // Getting organisation object as a json string
            //mapper.disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES);
            mapper.registerModule(new JavaTimeModule());
            Order o = mapper.readValue(s, Order.class);

            // Displaying JSON String on console
            System.out.println(o.toString());
        }

        // Catch block to handle exceptions
        catch (IOException e) {

            // Display exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }
}
