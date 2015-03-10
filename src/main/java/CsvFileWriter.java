import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Object[] FILE_HEADER = { "City Name", "Subdivision", "Temp, C", "Cloud cover, %",
            "Humidity, %", "Pressure, millibars" };

    public static void writeCsvFile(List<CityWeather> cities, String fileName) {

        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {
            fileWriter = new FileWriter(fileName);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            csvFilePrinter.printRecord(FILE_HEADER);

            for (CityWeather cityWeather : cities) {
                List<String> weatherDataRecord = new ArrayList<>();
                weatherDataRecord.add(cityWeather.getCityName());
                weatherDataRecord.add(cityWeather.getDistrict());
                weatherDataRecord.add(cityWeather.getTempC());
                weatherDataRecord.add(cityWeather.getCloudCover());
                weatherDataRecord.add(cityWeather.getHumidity());
                weatherDataRecord.add(cityWeather.getPressure());
                csvFilePrinter.printRecord(weatherDataRecord);
            }

            System.out.println("CSV file " + fileName + " was created successfully");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                if (csvFilePrinter != null) {
                    csvFilePrinter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter");
                e.printStackTrace();
            }
        }
    }
}
