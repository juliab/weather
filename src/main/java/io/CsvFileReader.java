package io;

import data.City;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    public List<City> readCsvFile(Path filePath) {

        List<City> cities = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader()
                    .parse(reader);
            for (CSVRecord record : records) {
                cities.add(new City(record.get(0), record.get(1)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filePath + "' not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        }
        return cities;
    }
}
