package application.beta.geoassist.pojo;

public class Person implements GeoType {


    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    double longitude;
}
