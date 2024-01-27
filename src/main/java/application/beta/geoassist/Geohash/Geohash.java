package application.beta.geoassist.Geohash;

public interface Geohash<T> {

    T encodeLatLon(double lat, double lon);
}
