package iseroshtan.weather.io;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import iseroshtan.weather.data.City;
import iseroshtan.weather.data.Weather;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class WeatherData {

    /**
     * Reads locations from csv file into the list of City instances.
     *
     * @param csvFilePath Csv file path.
     * @return List of City objects.
     * @throws IOException if an I/O error occurs opening the file.
     */
    public static List<City> readLocations(Path csvFilePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(csvFilePath)) {
            Iterator<City> it = new CsvMapper().readerWithSchemaFor(City.class)
                    .readValues(reader);
            List<City> result = new ArrayList<>();
            while(it.hasNext()) {
                result.add(it.next());
            }
            return result;
        }
    }

    /**
     * Writes a collection of City and Weather objects into csv file.
     *
     * @param weatherMap Collection of City and Weather objects.
     * @param csvFilePath Output csv file path.
     * @throws IOException IOException if an I/O error occurs opening the file.
     */
    public static void write(Map<City, Weather> weatherMap, Path csvFilePath) throws IOException {
        try (Writer writer = Files.newBufferedWriter(csvFilePath)) {
            FormatSchema schema = CsvSchema.builder()
                    .addColumn("name")
                    .addColumn("area")
                    .addColumn("temperatureC")
                    .addColumn("humidity")
                    .addColumn("windSpeed")
                    .addColumn("pressure")
                    .setUseHeader(true)
                    .build();

            ObjectMapper mapper = new CsvMapper();
            mapper.writer(schema).writeValue(writer, WeatherEntity.createCollection(weatherMap));
        }
    }

    private static final class WeatherEntity {
        @JsonUnwrapped
        private final City city;
        @JsonUnwrapped
        private final Weather weather;

        private WeatherEntity(City city, Weather weather) {
            this.city = city;
            this.weather = weather;
        }

        private static List<WeatherEntity> createCollection(Map<City, Weather> weatherMap) {
            List<WeatherEntity> weatherEntities = new ArrayList<>();
            for (Object object : weatherMap.entrySet()) {
                Map.Entry pair = (Map.Entry) object;
                weatherEntities.add(new WeatherEntity((City) pair.getKey(), (Weather) pair.getValue()));
            }
            return weatherEntities;
        }

        public City getCity() {
            return city;
        }

        public Weather getWeather() {
            return weather;
        }
    }
}
