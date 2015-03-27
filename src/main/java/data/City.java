package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final public class City {
    final private String name;
    final private String area;

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
