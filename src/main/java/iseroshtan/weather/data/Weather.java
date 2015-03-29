package iseroshtan.weather.data;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Weather weather = (Weather) object;
        return temperatureC.equals(weather.temperatureC) && humidity.equals(weather.humidity)
                && windSpeed.equals(weather.windSpeed) && pressure.equals(weather.pressure);
    }

    @Override
    public int hashCode() {
        int result = temperatureC != null ? temperatureC.hashCode() : 0;
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (windSpeed != null ? windSpeed.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        return result;
    }
}
