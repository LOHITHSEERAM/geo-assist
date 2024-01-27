package application.beta.geoassist.GeoData;

import application.beta.geoassist.Index.QuadTree;
import application.beta.geoassist.pojo.*;
import application.beta.geoassist.util.Shapes.BBox;
import application.beta.geoassist.util.Shapes.Circle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GeoDataEateries {

    ObjectMapper objectMapper = new ObjectMapper();

    QuadTree quadTree = new QuadTree(3, new BBox(-180, 180, -90, 90), 6);
    Root root;


    public void loadEateries() throws IOException {


        File Eateries = new File("/Users/lohithseeram/Downloads/Banglore_cafe_restaurants.geojson");
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

        ArrayList<Feature> features = root.getFeatures();
        for (int i = 0; i < features.size(); i++) {
            GeoType eatery = new Attractions();
            eatery.setId(features.get(i).id);

            if (features.get(i).getGeometry() instanceof Point) {
                Point pointGeometry = (Point) features.get(i).getGeometry();
                eatery.setLongitude(pointGeometry.getCoordinates().get(1));
                eatery.setLatitude(pointGeometry.getCoordinates().get(0));
            } else if (features.get(i).getGeometry() instanceof Polygon) {
                Polygon polygonGeometry = (Polygon) features.get(i).getGeometry();
                eatery.setLongitude(polygonGeometry.getCoordinates().get(0).get(0).get(1));
                eatery.setLatitude(polygonGeometry.getCoordinates().get(0).get(0).get(0));
            }
            quadTree.insert(eatery);
        }

        GeoType eat = new Attractions();
        eat.setLongitude(12.9984461);
        eat.setLatitude(77.5920015);
        System.out.println(quadTree.getNearByWithoutRange(eat));
        System.out.println(quadTree.getNearByWithRange(eat, new Circle(eat.getLatitude(), eat.getLongitude(), 300)).size());

    }
}
