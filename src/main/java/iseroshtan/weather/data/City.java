package iseroshtan.weather.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class City {
    private final String name;
    private final String area;

    @JsonCreator
    public City(@JsonProperty("name") String name, @JsonProperty("area") String area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }
}
