package application.beta.geoassist.pojo;

import java.io.IOException;

public enum GeometryType {
    POINT, POLYGON;

    public String toValue() {
        switch (this) {
            case POINT:
                return "Point";
            case POLYGON:
                return "Polygon";
        }
        return null;
    }

    public static GeometryType forValue(String value) throws IOException {
        if (value.equals("Point")) return POINT;
        if (value.equals("Polygon")) return POLYGON;
        throw new IOException("Cannot deserialize GeometryType");
    }
}

