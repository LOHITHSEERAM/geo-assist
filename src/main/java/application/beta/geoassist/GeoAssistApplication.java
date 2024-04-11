package application.beta.geoassist;

import application.beta.geoassist.GeoData.GeoDataEateries;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GeoAssistApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GeoAssistApplication.class, args);
        GeoDataEateries gde = new GeoDataEateries();

        try {
            gde.loadEateries();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
