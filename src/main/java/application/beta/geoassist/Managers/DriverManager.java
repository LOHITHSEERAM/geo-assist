package application.beta.geoassist.Managers;


import application.beta.geoassist.pojo.Driver;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final DriverManager DriverManagerInstance = null;

    public static DriverManager getDriverManagerInstance() {
        if (DriverManagerInstance == null)
            return new DriverManager();
        else
            return DriverManagerInstance;
    }

    private DriverManager() {
    }

    Map<Integer, Driver> drivers = new HashMap<>();

    void addDriver(Integer DriverId, Driver driver) {
        drivers.put(DriverId, driver);
    }

    Driver getDriver(Integer RiderId) {
        return drivers.get(RiderId);
    }
}
