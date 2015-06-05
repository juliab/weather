package iseroshtan.weather.service;

import com.google.inject.AbstractModule;

public class WeatherServiceDiTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WeatherService.class).to(TestRestService.class);
    }
}
