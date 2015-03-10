import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    public List<CityWeather> readCsvFile(String fileName) {

        List<CityWeather> citiesList = new ArrayList<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader()
                    .parse(br);
            for (CSVRecord record : records) {
                citiesList.add(new CityWeather(record.get(0), record.get(1)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File '" + fileName + "' not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        }
        return citiesList;
    }
}
