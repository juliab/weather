package data;

final public class City {
    final private String name;
    final private String area;

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
}
