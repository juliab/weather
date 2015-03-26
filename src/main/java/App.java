import data.City;
import data.Weather;
import io.CsvFileReader;
import io.CsvFileWriter;
import org.apache.commons.cli.ParseException;
import service.CityNotFoundException;
import service.WeatherService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        try {
            Args parsedArgs = Args.parseArgs(args);
            Date date = parsedArgs.getDate();
            System.out.println("Acquiring weather data on " + date + ". This may take a while...");
            WeatherService service = new WeatherService();
            List<City> cities = new CsvFileReader().readCsvFile(parsedArgs.getInputFilePath());
            Map<City, Weather> weatherMap = new HashMap<>();
            for (City city : cities) {
                try {
                    weatherMap.put(city, service.requestWeather(city, date));
                } catch (CityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            CsvFileWriter.writeCsvFile(weatherMap, parsedArgs.getOutputFilePath());
        } catch (ParseException | java.text.ParseException e) {
            Args.printHelp();
            System.exit(-1);
        }
    }
}
