import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws IOException {

        // Read date from user input or use today if no date given
        System.out.print("Please enter the date to return the weather for \nin format yyyy-MM-dd " +
                "(default date is today): ");

        String date = readDateFromUserInput();
        if (date.equals("")) {
            Date today = new Date();
            date = dateFormat.format(today);
        }

        System.out.println("Acquiring weather data on " + date + ". This may take a while...");

        String inputFileName = "citiesOfUkraineList.csv";
        WeatherService service = new WeatherService(date);
        List<CityWeather> cities = new CsvFileReader().readCsvFile(inputFileName);
        for (CityWeather city : cities) {
            service.requestWeather(city);
        }
        service.closeSession();
        String outputFileName = "output_" + date + ".csv";
        CsvFileWriter.writeCsvFile(cities, outputFileName);
    }

    public static String readDateFromUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dateFormat.setLenient(false);
        String input;
        while(true) {
            try {
                input = br.readLine();
                if (input.equals("")) { // if no date given, today will be returned
                    Date today = new Date();
                    return dateFormat.format(today);
                }
                try {
                    return dateFormat.format(dateFormat.parse(input));
                } catch (ParseException | IllegalArgumentException e) {
                    System.out.println("Invalid date format. Please try again: ");
                }
            } catch (IOException ioe) {
                System.out.println("IO error trying to read date");
                System.exit(1);
            }
        }
    }
}
