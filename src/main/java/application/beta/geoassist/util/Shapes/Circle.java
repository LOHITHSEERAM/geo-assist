/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package application.beta.geoassist.util.Shapes;

import application.beta.geoassist.util.DistanceCalc;
import application.beta.geoassist.util.DistanceCalcEarth;
import application.beta.geoassist.util.NumHelper;

/**
 * @author Peter Karich
 */
public class Circle implements Shape {
    public final double radiusInMeter;
    private final double lat;
    private final double lon;
    public final double normedDist;
    public final BBox bbox;
    public DistanceCalc calc;

    public Circle(double lat, double lon, double radiusInMeter) {
        this(lat, lon, radiusInMeter, DistanceCalcEarth.DIST_EARTH);
    }

    public Circle(double lat, double lon, double radiusInMeter, DistanceCalc calc) {
        this.calc = calc;
        this.lat = lat;
        this.lon = lon;
        this.radiusInMeter = radiusInMeter;
        this.normedDist = calc.calcNormalizedDist(radiusInMeter);
        bbox = calc.createBBox(lat, lon, radiusInMeter);
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean contains(double lat1, double lon1) {
        return normDist(lat1, lon1) <= normedDist;
    }

    @Override
    public BBox getBounds() {
        return bbox;
    }

    private double normDist(double lat1, double lon1) {
        return calc.calcNormalizedDist(lat, lon, lat1, lon1);
    }


    public boolean intersects(BBox b) {
        // test top intersects
        if (lat > b.maxLat) {
            if (lon < b.minLon) {
                return normDist(b.maxLat, b.minLon) <= normedDist;
            }
            if (lon > b.maxLon) {
                return normDist(b.maxLat, b.maxLon) <= normedDist;
            }
            return b.maxLat - bbox.minLat > 0;
        }

        // test bottom intersects
        if (lat < b.minLat) {
            if (lon < b.minLon) {
                return normDist(b.minLat, b.minLon) <= normedDist;
            }
            if (lon > b.maxLon) {
                return normDist(b.minLat, b.maxLon) <= normedDist;
            }
            return bbox.maxLat - b.minLat > 0;
        }

        // test middle intersects
        if (lon < b.minLon) {
            return bbox.maxLon - b.minLon > 0;
        }
        if (lon > b.maxLon) {
            return b.maxLon - bbox.minLon > 0;
        }
        return true;
    }

    public boolean contains(BBox b) {
        if (bbox.contains(b)) {
            return contains(b.maxLat, b.minLon) && contains(b.minLat, b.minLon)
                    && contains(b.maxLat, b.maxLon) && contains(b.minLat, b.maxLon);
        }

        return false;
    }


    public boolean contains(Circle c) {
        double res = radiusInMeter - c.radiusInMeter;
        if (res < 0) {
            return false;
        }

        return calc.calcDist(lat, lon, c.lat, c.lon) <= res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        Circle b = (Circle) obj;
        // equals within a very small range

        return NumHelper.equalsEps(lat, b.lat) && NumHelper.equalsEps(lon, b.lon) && NumHelper.equalsEps(radiusInMeter, b.radiusInMeter);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.lon) ^ (Double.doubleToLongBits(this.lon) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.radiusInMeter) ^ (Double.doubleToLongBits(this.radiusInMeter) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return lat + "," + lon + ", radius:" + radiusInMeter;
    }
}
