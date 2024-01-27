package application.beta.geoassist.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Point extends Geometry {
    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    String type;

    @JsonProperty("coordinates")
    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Double> coordinates;
}
