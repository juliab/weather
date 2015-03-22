import data.City;
import data.Weather;
import io.CsvFileReader;
import io.CsvFileWriter;
import service.CityNotFoundException;
import service.WeatherService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        OptionsParser parser = new OptionsParser(args);
        String date = parser.getDate();
        System.out.println("Acquiring weather data on " + date + ". This may take a while...");
        WeatherService service = new WeatherService();
        List<City> cities = new CsvFileReader().readCsvFile(parser.getInputFilePath());
        Map<City, Weather> weatherMap = new HashMap<>();
        for (City city : cities) {
            try {
                weatherMap.put(city, service.requestWeather(city, date));
            } catch (CityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        CsvFileWriter.writeCsvFile(weatherMap, parser.getOutputFilePath());
    }
}
