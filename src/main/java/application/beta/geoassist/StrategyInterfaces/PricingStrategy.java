package application.beta.geoassist.StrategyInterfaces;


import application.beta.geoassist.Trip.TripMetaData;

public interface PricingStrategy {

    double calculatePrice(TripMetaData metadata);

}
