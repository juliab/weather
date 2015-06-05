package iseroshtan.weather.service;

import com.google.inject.AbstractModule;

/**
 * This is a subclass of Guice module that defines mapping of WeatherService interface to concrete implementation
 * which will be used to inject dependencies where needed.
 *
 * @author Julia Seroshtan
 */

public class WeatherServiceDiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WeatherService.class).to(WorldWeatherOnlineRest.class);
    }
}
