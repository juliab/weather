package data;

public final class Weather {
    private final String temperatureC; // Average day temperature in C
    private final String humidity; // Average day humidity, %
    private final String windSpeed; // Average day windSpeed kph
    private final String pressure; // Average day pressure in mBar

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
