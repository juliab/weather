import data.City;
import data.Weather;
import io.WeatherData;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CityNotFoundException;
import service.WeatherService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Application that creates csv file with weather information for cities provided by user.
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        try {
            Args parsedArgs = Args.parseArgs(args);
            Date date = parsedArgs.getDate();
            LOGGER.info("Acquiring weather data on {}. This may take a while...", date);
            WeatherService service = new WeatherService();
            List<City> cities = WeatherData.readLocations(parsedArgs.getInputFilePath());
            Map<City, Weather> weatherMap = new HashMap<>();
            for (City city : cities) {
                try {
                    weatherMap.put(city, service.requestWeather(city, date));
                } catch (CityNotFoundException exception) {
                    LOGGER.warn(exception.getMessage());
                }
            }
            WeatherData.write(weatherMap, parsedArgs.getOutputFilePath());
            LOGGER.info("CSV file {} was created successfully", parsedArgs.getOutputFilePath());
        } catch (ParseException | java.text.ParseException exception) {
            Args.printHelp();
            System.exit(-1);
        }
    }
}
