package application.beta.geoassist.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Polygon extends Geometry {

    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public ArrayList<ArrayList<ArrayList<Double>>> getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(ArrayList<ArrayList<ArrayList<Double>>> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<ArrayList<ArrayList<Double>>> coordinates;
}
