import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    public List<CityWeather> readCsvFile(String fileName) {

        List<CityWeather> citiesList = new ArrayList<>();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader()
                    .parse(bufferedReader);
            for (CSVRecord record : records) {
                citiesList.add(new CityWeather(record.get(0), record.get(1)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File '" + fileName + "' not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return citiesList;
    }
}
