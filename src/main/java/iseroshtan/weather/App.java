package iseroshtan.weather;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import iseroshtan.weather.service.CityNotFoundException;
import iseroshtan.weather.service.WeatherService;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static iseroshtan.weather.Args.*;
import static iseroshtan.weather.io.WeatherData.*;

/**
 * Application that creates csv file with weather information for cities provided by user.
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        try {
            Args parsedArgs = parseArgs(args);
            Date date = parsedArgs.getDate();
            LOGGER.info("Acquiring weather data on {}. This may take a while...", date);
            WeatherService service = new WeatherService();
            List<City> cities = readLocations(parsedArgs.getInputFilePath());
            Map<City, Weather> weatherMap = new HashMap<>();
            for (City city : cities) {
                try {
                    weatherMap.put(city, service.requestWeather(city, date));
                } catch (CityNotFoundException exception) {
                    LOGGER.warn(exception.getMessage());
                }
            }
            write(weatherMap, parsedArgs.getOutputFilePath());
            LOGGER.info("CSV file {} was created successfully", parsedArgs.getOutputFilePath());
        } catch (ParseException | java.text.ParseException exception) {
            printHelp();
            System.exit(-1);
        }
    }
}
