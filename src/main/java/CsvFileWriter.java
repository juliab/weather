import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Object[] FILE_HEADER = { "City Name", "Area", "Temp, C", "Cloud cover, %",
            "Humidity, %", "Pressure, millibars" };

    public static void writeCsvFile(List<City> cities, String filePath) {

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        Path path = Paths.get(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path);
             CSVPrinter csvFilePrinter = new CSVPrinter(writer, csvFileFormat)) {

            csvFilePrinter.printRecord(FILE_HEADER);

            for (City city: cities) {
                List<String> weatherDataRecord = new ArrayList<>();
                weatherDataRecord.add(city.getName());
                weatherDataRecord.add(city.getArea());
                Weather weather = city.getWeather();
                if (weather != null) {
                    weatherDataRecord.add(weather.getTempC());
                    weatherDataRecord.add(weather.getCloudCover());
                    weatherDataRecord.add(weather.getHumidity());
                    weatherDataRecord.add(weather.getPressure());
                }
                csvFilePrinter.printRecord(weatherDataRecord);
            }

            System.out.println("CSV file " + filePath + " was created successfully");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        }
    }
}
