package com.mfsa.product.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * MyStatsApp is a simple console application which computes
 * basic statistics of a series of data values. The application takes
 * a file of data as its single argument.
 *
 * @author Heydrich
 */
public class MyStatApp {
    /**
     * Etapa de interrogación, verificará
     * si una opción en particular está presente o 
     * NO y procesamos el comando en consecuencia.
     *
     * @param args an array of String arguments to be parsed
     */
    public void run(String[] args) {

        CommandLine line = parseArguments(args);
        if (line.hasOption("filename")) {

            System.out.println(line.getOptionValue("filename"));
            String fileName = line.getOptionValue("filename");
//            double[] data = 	readData(fileName);
//            System.out.println(data);
//            calculateAndPrintStats(data);

        } else {
            printAppHelp();
        }

    }

    /**
     * Analiza los argumentos que se le pasen por linea de comando
     *
     * @param args
     * @return <code>CommandLine</code> which represents a list of application
     * arguments.
     */
    private CommandLine parseArguments(String[] args) {

        Options options = getOptions();
        CommandLine line = null;
        CommandLineParser parser = new DefaultParser();
        try {
            line = parser.parse(options, args);
        } catch (ParseException ex) {

            System.err.println(ex);
            printAppHelp();

            System.exit(1);
        }

        return line;
    }
    
    /**
     * Genera las opciones que usaran en la linea de comando
     *
     * @return application <code>Options</code>
     */
    private Options getOptions() {

        Options options = new Options();
        options.addOption("f", "filename", true,"file name to load data from");
        return options;
    }
    
    /**
     * Muestra en pantalla las opciones que fueron dadas de altas
     */
    private void printAppHelp() {

        Options options = getOptions();

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("MFSA Extract Logs", options, true);
    }

    /**
     * Reads application data from a file
     *
     * @param fileName
     * @return array of double values
     */
    private double[] readData(String fileName) {

        List<Double> data = new ArrayList();
        double[] mydata = null;

        try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {

                for (String e : nextLine) {

                    data.add(Double.parseDouble(e));
                }
            }

            mydata = ArrayUtils.toPrimitive(data.toArray(new Double[data.size()]));

        } catch (IOException ex) {

            System.err.println(ex);
            System.exit(1);
        }

        return mydata;
    }

    /**
     * Calculates and prints data statistics
     *
     * @param data input data
     */
    private void calculateAndPrintStats(double[] data) {

        System.out.format("Geometric mean: %f%n", StatUtils.geometricMean(data));
        System.out.format("Arithmetic mean: %f%n", StatUtils.mean(data));
        System.out.format("Max: %f%n", StatUtils.max(data));
        System.out.format("Min: %f%n", StatUtils.min(data));
        System.out.format("Sum: %f%n", StatUtils.sum(data));
        System.out.format("Variance: %f%n", StatUtils.variance(data));
    }
}
