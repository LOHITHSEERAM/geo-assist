package application.beta.geoassist.Managers;


import application.beta.geoassist.pojo.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderManager {

    private RiderManager() {
    }

    private static RiderManager RiderManagerInstance;

    public static RiderManager getRiderManagerInstance() {
        if (RiderManagerInstance == null)
            return new RiderManager();
        else
            return RiderManagerInstance;
    }

    Map<Integer, Rider> riders = new HashMap<>();

    void addRider(Integer RiderId, Rider rider) {
        riders.put(RiderId, rider);
    }

    Rider getRider(Integer RiderId) {
        return riders.get(RiderId);
    }


}
