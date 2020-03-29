package utility;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.village.Village;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameState {
    public static void save(Village village) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            // Java object to JSON file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("okay.json"), village.getBuildings());

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(village);

            //System.out.println("save" + jsonInString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Village load() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            // JSON file to Java object
            Village village = mapper.readValue(new File("okay.json"), Village.class);

            // pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(village);

            System.out.println("load" + prettyStaff1);

            return village;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
