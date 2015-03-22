import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;

import java.text.*;
import java.util.Date;

public class OptionsParser {

    private Option inputFilePath;
    private Option outputFilePath;
    private Option date;
    private CommandLine cmd;

    public OptionsParser(String[] args) {
        Options options = new Options();

        inputFilePath = new Option("i", "input", true, "Input csv file path. File format: City Name,Area");
        inputFilePath.setRequired(true);
        options.addOption(inputFilePath);

        outputFilePath = new Option("o", "output", true, "Output csv file path. File format: " +
                "City Name,Area,\"Temp, C\",\"Cloud cover, %\",\"Humidity, %\",\"Pressure, millibars\"");
        outputFilePath.setRequired(true);
        options.addOption(outputFilePath);

        date = new Option("d", "date", true, "The date to return the weather for. Date format: yyyy-MM-dd. " +
                "Default day is today");
        options.addOption(date);

        try {
            CommandLineParser parser = new GnuParser();
            cmd = parser.parse(options, args);
        } catch (MissingOptionException e) {
            System.out.println("Wrong usage");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("App", options, true);
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getInputFilePath() {
        return cmd.getOptionValue(inputFilePath.getOpt());
    }

    public String getOutputFilePath() {
        return cmd.getOptionValue(outputFilePath.getOpt());
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        String argDate = cmd.getOptionValue(date.getOpt());
        if (argDate != null) {
            try {
                return dateFormat.format(dateFormat.parse(argDate));
            } catch (java.text.ParseException | IllegalArgumentException e) {
                System.out.println("Invalid date format.");
                e.printStackTrace();
            }
        }
        Date today = new Date();
        return dateFormat.format(today);
    }
}
