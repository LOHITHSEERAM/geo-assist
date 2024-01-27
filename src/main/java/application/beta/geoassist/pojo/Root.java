package application.beta.geoassist.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Root {
    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    @JsonProperty("generator")
    public String getGenerator() {
        return this.generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    String generator;

    @JsonProperty("copyright")
    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    String copyright;

    @JsonProperty("timestamp")
    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    Date timestamp;

    @JsonProperty("features")
    public ArrayList<Feature> getFeatures() {
        return this.features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    ArrayList<Feature> features;
}
