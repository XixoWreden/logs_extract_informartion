package com.mfsa.product.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFileLineByLineController {

	public static void main(String[] args) {

		String fName = "logs/mfsa_service_01.log";
		System.out.println("Leer el archivo: " + fName);
		System.out.println();
		// Method #1 - Read all lines as a Stream
//		fileStreamUsingFiles(fName);

        // Method #2 - Read file with a filter
		System.out.println();
		filterFileData(fName);
 
        
 
        // Method #3 - In Java8, 'BufferedReader' has the 'lines()' method which returns the file content as a Stream
		System.out.println();
		fileStreamUsingBufferedReader(fName);

	}

	// Method #1 - Read all lines as a Stream
	private static void fileStreamUsingFiles(String fileName) {
		try {
			Stream<String> lines = Files.lines(Paths.get(fileName));
			System.out.println("<!-----Read all lines as a Stream-----!>");
            lines.forEach(System.out :: println);
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	// Method #2 - Read file with a filter
	private static void filterFileData(String fileName) {
		try {
			Stream<String> lines = Files.lines(Paths.get(fileName)).filter(line -> line.startsWith("P")||line.contains("MFSAMethodMonitorInterceptor.?:?"));
			System.out.println("<!-----Filtering the file data using Java8 filtering-----!>");
			lines.forEach(System.out::println);
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	// Method #3 - In Java8, 'BufferedReader' has the 'lines()' method which returns
	// the file content as a Stream
	private static void fileStreamUsingBufferedReader(String fileName) {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
			Stream<String> lines = br.lines().map(str -> str.toUpperCase());
			System.out.println("<!-----Read all lines by using BufferedReader-----!>");
			lines.forEach(System.out::println);
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
