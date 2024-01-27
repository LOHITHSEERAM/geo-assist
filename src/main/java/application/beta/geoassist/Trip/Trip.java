package application.beta.geoassist.Trip;

import application.beta.geoassist.Location.Location;
import application.beta.geoassist.StrategyInterfaces.DriverMatchingStrategy;
import application.beta.geoassist.StrategyInterfaces.PricingStrategy;
import application.beta.geoassist.pojo.Driver;
import application.beta.geoassist.pojo.Rider;

public class Trip {

    Rider rider;

    Driver driver;

    Location srcLoc;

    Location destLoc;

    int TripId;

    double price;

    PricingStrategy pricingStrategy;

    DriverMatchingStrategy driverMatchingStrategy;

    public Trip(Rider rider, Driver driver, Location srcLoc, Location destLoc, int tripId, double price, PricingStrategy pricingStrategy, DriverMatchingStrategy driverMatchingStrategy) {
        this.rider = rider;
        this.driver = driver;
        this.srcLoc = srcLoc;
        this.destLoc = destLoc;
        TripId = tripId;
        this.price = price;
        this.pricingStrategy = pricingStrategy;
        this.driverMatchingStrategy = driverMatchingStrategy;
    }

    public String displayTripDetails() {
        return "Trip{" +
                "rider=" + rider +
                ", driver=" + driver +
                ", srcLoc=" + srcLoc +
                ", destLoc=" + destLoc +
                ", TripId=" + TripId +
                ", price=" + price +
                ", pricingStrategy=" + pricingStrategy +
                ", driverMatchingStrategy=" + driverMatchingStrategy +
                '}';
    }
}
