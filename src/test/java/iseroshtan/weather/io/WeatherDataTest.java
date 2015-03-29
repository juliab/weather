package iseroshtan.weather.io;

import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static iseroshtan.weather.io.WeatherData.*;

public class WeatherDataTest {

    private final String resourcesFolderPath = "src" + File.separator + "test" + File.separator + "resources";
    private final String inputFilePath = resourcesFolderPath + File.separator + "input.csv";
    private final String outputFilePath = resourcesFolderPath + File.separator + "output.csv";


    @Test
    public void readLocationsTest() throws IOException {
        List<City> cities = readLocations(Paths.get(inputFilePath));
        assertEquals(new City("Dnipropetrovsk", "Dnipropetrovsk Oblast"), cities.get(0));
        assertEquals(new City("Odessa", "Odessa Oblast"), cities.get(1));
    }

    @Test
    public void writeTest() throws IOException {
        Map<City, Weather> map = new HashMap<>();
        map.put(new City("Donetsk", "Donetsk"), new Weather("-9", "87", "10", "1029"));
        write(map, Paths.get(outputFilePath));
        List<String> fileContent = Files.readAllLines(Paths.get(outputFilePath));
        assertEquals("name,area,temperatureC,humidity,windSpeed,pressure", fileContent.get(0));
        assertEquals("Donetsk,Donetsk,-9,87,10,1029", fileContent.get(1));
    }

    @Test
    public void emptyLocations() throws IOException {
        assertEquals(0, readLocations(Paths.get(resourcesFolderPath + File.separator + "empty.csv")).size());
    }
}
