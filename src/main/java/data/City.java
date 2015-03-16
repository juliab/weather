package data;

public class City {
    private String name;
    private String area;
    private Weather weather;

    public City(String name, String area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
