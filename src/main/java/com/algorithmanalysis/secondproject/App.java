package com.algorithmanalysis.secondproject;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.algorithmanalysis.secondproject.algorithms.Genetic;
import com.algorithmanalysis.secondproject.storage.LoadJson;
import com.algorithmanalysis.secondproject.storage.LoadJson.ParsedData;
import com.algorithmanalysis.secondproject.utils.ErrorCodes;

/**
 * Project: Algorithm Analysis Project 2
 * File Name: App.java
 *
 * Purpose: Main class for the project
 */
public class App {
    public static void main(String[] args) throws IOException, ParseException {
        String fileName = "data/data5.json"; // File name
        ParsedData parsedData = LoadJson.fromFile(fileName); // Load the data from the file

        Genetic genetic = new Genetic(); // Create a new genetic object
        genetic.setPopulation(parsedData.alleles); // Set the population
        genetic.setPopulationSize(parsedData.population); // Set the population size
        genetic.setTotalOfProfessors(parsedData.alleles.size() / parsedData.courses); // Set the total of professors
        genetic.setTotalOfCourses(parsedData.courses); // Set the total of courses

        // Check if the chromosomes were created successfully
        ErrorCodes error = genetic.createChromosomes();
        switch (error) {
            case ERROR_INCAPABLE: // The program can't generate the desired result
                System.out.println("The program can't generate the desired result, reason: Invalid data");
                break;
            case MAX_ATTEMPTS_EXCEEDED: // The program couldn't generate the desired result in the maximum attempts
                System.out.println("The program can't generate the desired result, reason: Maximum attempts exceeded");
                break;
            case NO_ERROR: // The program generated the desired result
                System.out.println("The program generated the desired result for the file: " + fileName);
                System.out.println("Chromosomes: " + genetic);
                break;
        }
    }
}
