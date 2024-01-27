package application.beta.geoassist.StrategyInterfaces;


import application.beta.geoassist.Trip.TripMetaData;
import application.beta.geoassist.pojo.Driver;

public interface DriverMatchingStrategy {

    Driver matchDriver(TripMetaData metadata);
}
