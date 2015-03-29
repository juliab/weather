package iseroshtan.weather;

import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
import java.util.Date;

public final class Args {

    /**
     * The short and long name of argument that contains input csv file path.
     */
    public static final String INPUT_FILE_SHORT_NAME = "i";
    public static final String INPUT_FILE_NAME = "input";

    /**
     * The short and long name of argument that contains output csv file path.
     */
    public static final String OUTPUT_FILE_SHORT_NAME = "o";
    public static final String OUTPUT_FILE_NAME = "output";

    /**
     * The short and long name of argument that contains date for weather information to be gathered on.
     */
    public static final String DATE_SHORT_NAME = "d";
    public static final String DATE_NAME = "date";

    private final Path inputFilePath;
    private final Path outputFilePath;
    private final Date date;

    /**
     * Create arguments.
     *
     * @param inputFilePath input csv file (cities list) path.
     * @param outputFilePath output csv file (weather information for each city) path.
     * @param date date for weather information to be gathered on.
     */
    public Args(Path inputFilePath, Path outputFilePath, Date date) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.date = date;
    }

    /**
     * Returns input csv file path.
     *
     * @return file path.
     */
    public Path getInputFilePath() {
        return inputFilePath;
    }

    /**
     * Returns output csv file path.
     *
     * @return file path.
     */
    public Path getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Returns date for weather information to be gathered on.
     *
     * @return date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Parses command-line arguments.
     *
     * @param args command-line arguments.
     * @return parsed arguments.
     * @throws ParseException if command-line arguments cannot be parsed.
     * @throws java.text.ParseException if provided date cannot be parsed.
     */
    public static Args parseArgs(String[] args) throws ParseException, java.text.ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(getOptions(), args);

        Path inputFilePath = getFilePath(cmd.getOptionValue(INPUT_FILE_NAME));
        Path outputFilePath = getFilePath(cmd.getOptionValue(OUTPUT_FILE_NAME));
        Date date = parseDate(cmd.getOptionValue(DATE_NAME));

        return new Args(inputFilePath, outputFilePath, date);
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption(newOption(INPUT_FILE_SHORT_NAME, INPUT_FILE_NAME, "Input csv file path. File format: " +
                "City Name,Area", true));
        options.addOption(newOption(OUTPUT_FILE_SHORT_NAME, OUTPUT_FILE_NAME, "Output csv file path. File format: " +
                "City Name,Area,\"Temp, C\",\"Cloud cover, %\",\"Humidity, %\",\"Pressure, millibars\"", true));
        options.addOption(newOption(DATE_SHORT_NAME, DATE_NAME, "The date to return the weather for. Date format: " +
                "yyyy-MM-dd. Default day is today", false));
        return options;
    }

    private static Option newOption(String shortName, String name, String description, boolean isRequired) {
        Option option = new Option(shortName, name, true, description);
        option.setRequired(isRequired);
        return option;
    }

    private static Path getFilePath(String path) {
        return Paths.get(path);
    }

    private static Date parseDate(String date) throws java.text.ParseException {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        } else {
            return new Date();
        }
    }

    /**
     * Prints help for application.
     */
    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("weather", getOptions(), true);
    }
}
