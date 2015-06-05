package iseroshtan.weather.service;

import com.google.inject.AbstractModule;

public class WeatherServiceDiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WeatherService.class).to(WorldWeatherOnlineRest.class);
    }
}
