package iseroshtan.weather;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static iseroshtan.weather.Args.parseArgs;
import static org.junit.Assert.assertEquals;

public class ArgsTest {

    private final String resourcesFolderPath = "src" + File.separator + "test" + File.separator + "resources";
    private final String validInputFilePath = resourcesFolderPath + File.separator + "input.csv";
    private final String validOutputFilePath = resourcesFolderPath + File.separator + "output.csv";

    @Test(expected = ParseException.class)
    public void emptyArgs() throws Exception {
        parseArgs(new String[]{});
    }

    @Test(expected = ParseException.class)
    public void missingRequiredArgument() throws Exception {
        parseArgs(new String[]{"-i", validInputFilePath, "-d", "2015-01-01"});
    }

    @Test
    public void missingOptionalArgument() throws Exception {
        Args args = parseArgs(new String[] { "-i", validInputFilePath, "-o", validOutputFilePath});
        assertEquals(Paths.get(validInputFilePath), args.getInputFilePath());
        assertEquals(Paths.get(validOutputFilePath), args.getOutputFilePath());
        assertEquals(new Date(), args.getDate());
    }

    @Test
    public void testArgs() throws Exception {
        String date = "2015-02-03";
        Args args = parseArgs(new String[] { "-i", validInputFilePath, "-o", validOutputFilePath, "-d", date});
        assertEquals(Paths.get(validInputFilePath), args.getInputFilePath());
        assertEquals(Paths.get(validOutputFilePath), args.getOutputFilePath());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(dateFormat.parse(date), args.getDate());
    }

    @Test(expected = java.text.ParseException.class)
    public void invalidDateFormat() throws Exception {
        parseArgs(new String[] { "-i", validInputFilePath, "-o", validOutputFilePath, "-d", "2015/20/02"});
    }

    @Test(expected = NoSuchFileException.class)
    public void notExistingInputFile() throws Exception {
        parseArgs(new String[] { "-i", "", "-o", validOutputFilePath});
    }

    @Test(expected = IOException.class)
    public void invalidOutputFilePath() throws Exception {
        parseArgs(new String[] { "-i", validInputFilePath, "-o", ""});
    }
}
