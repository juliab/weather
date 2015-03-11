import java.util.List;

public class App {

    public static void main(String[] args) {
        OptionsParser parser = new OptionsParser(args);
        String date = parser.getDate();
        System.out.println("Acquiring weather data on " + date + ". This may take a while...");
        WeatherService service = new WeatherService(date);
        List<City> cities = new CsvFileReader().readCsvFile(parser.getInputFilePath());
        for (City city : cities) {
            service.requestWeather(city);
        }
        service.closeSession();
        CsvFileWriter.writeCsvFile(cities, parser.getOutputFilePath());
    }
}
