public class CityWeather {
    private String cityName;
    private String district;
    private String tempC;
    private String cloudCover;
    private String humidity;
    private String pressure;

    public CityWeather(String cityName, String district) {
        this.cityName = cityName;
        this.district = district;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDistrict() {
        return district;
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
