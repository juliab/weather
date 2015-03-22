package data;

final public class Weather {

    final private String temperatureC; // Average day temperature in C
    final private String humidity; // Average day humidity, %
    final private String windSpeed; // Average day windSpeed kph
    final private String pressure; // Average day pressure in mBar

    public Weather(String temperatureC, String humidity, String windSpeed,
                   String pressure) {
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public String getTemperatureC() {
        return temperatureC;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }
}
