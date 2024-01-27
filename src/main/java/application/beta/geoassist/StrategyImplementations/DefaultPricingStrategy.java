package application.beta.geoassist.StrategyImplementations;


import application.beta.geoassist.StrategyInterfaces.PricingStrategy;
import application.beta.geoassist.Trip.TripMetaData;

public class DefaultPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(TripMetaData metadata) {
        return 0;
    }
}
