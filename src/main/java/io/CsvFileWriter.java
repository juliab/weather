package io;

import data.City;
import data.Weather;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvFileWriter {
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Object[] FILE_HEADER = { "city_name", "area", "temperature_C", "humidity",
            "windspeed", "pressure" };

    public static void writeCsvFile(Map<City, Weather> weatherMap, Path filePath) {

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath);
             CSVPrinter csvFilePrinter = new CSVPrinter(writer, csvFileFormat)) {

            csvFilePrinter.printRecord(FILE_HEADER);

            Iterator it = weatherMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                List<String> weatherDataRecord = new ArrayList<>();
                weatherDataRecord.add(((City) pair.getKey()).getName());
                weatherDataRecord.add(((City) pair.getKey()).getArea());
                Weather weather = (Weather) pair.getValue();
                if (weather != null) {
                    weatherDataRecord.add(weather.getTemperatureC());
                    weatherDataRecord.add(weather.getHumidity());
                    weatherDataRecord.add(weather.getWindSpeed());
                    weatherDataRecord.add(weather.getPressure());
                }
                csvFilePrinter.printRecord(weatherDataRecord);
                it.remove();
            }

            System.out.println("CSV file " + filePath + " was created successfully");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        }
    }
}
