package application.beta.geoassist.GeoData;

import application.beta.geoassist.pojo.CustomDeserializer;
import application.beta.geoassist.pojo.Geometry;
import application.beta.geoassist.pojo.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;

public class GeoDataRoads {

    ObjectMapper objectMapper = new ObjectMapper();

    Root root;

    public void loadEateries() throws IOException {

        File Eateries = new File("/Users/lohithseeram/Downloads/Banglore_roads.geojson");
        {
            try {
                SimpleModule module = new SimpleModule();
                module.addDeserializer(Geometry.class, new CustomDeserializer());
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(module);
                root = mapper.readValue(Eateries, Root.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
