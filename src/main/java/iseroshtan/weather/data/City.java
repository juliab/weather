package iseroshtan.weather.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This immutable class represents city.
 *
 * @author Julia Seroshtan
 */

@JsonPropertyOrder({ "name", "area" })
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        City city = (City) object;
        return area.equals(city.area) && name.equals(city.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (area != null ? area.hashCode() : 0);
        return result;
    }
}
