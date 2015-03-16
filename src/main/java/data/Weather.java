package data;

final public class Weather {
    final private String tempC;
    final private String cloudCover;
    final private String humidity;
    final private String pressure;

    public Weather(String tempC, String cloudCover, String humidity, String pressure) {
        this.tempC = tempC;
        this.cloudCover = cloudCover;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public String getTempC() {
        return tempC;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }
}
