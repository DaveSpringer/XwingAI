package com.devspringer.xwing.xwingai.xws.raw;

import java.io.*;

/**
 * Created by dspringer on 9/28/2015.
 */
public class RawToJson {

    public static void convertRawToJson(final String inputFilePath, final String outputFilePath) {
        // Validate input file.
        File inputFile = new File(inputFilePath);
        if (!inputFile.isFile()) {
            throw new RawConverterException("Could not find expected input file: " + inputFilePath);
        }

        // Open input file.
        BufferedReader reader;
        try {
            FileReader fileReader = new FileReader(inputFilePath);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new RawConverterException(e.getMessage(), e);
        }

        // Open writer.
        File outputFile = new File(outputFilePath);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new RawConverterException("Could not create new output file path: " + outputFilePath);
        }
        if (!outputFile.canWrite()) {
            throw new RawConverterException("Could not write out to file: " + outputFilePath);
        }

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(outputFile);
        } catch (IOException e) {
            throw new RawConverterException("Could not open file writer.", e);
        }
        BufferedWriter writer = new BufferedWriter(fileWriter);

        // Begin converting JSON elements.
        try {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                String[] splitLine = nextLine.replaceAll("^\\s+", "").split(":");
                if (splitLine.length == 2 && !splitLine[0].startsWith("\"")) {
                    nextLine = "\"" + splitLine[0] + "\":" + splitLine[1];
                }
                if (nextLine.contains(".canonicalize()")) {
                    nextLine = nextLine.replaceAll(".canonicalize\\(\\)", "");
                }
                writer.write(nextLine);
                writer.newLine();
                nextLine = reader.readLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RawToJson.convertRawToJson("xwingai-xws-spec/src/main/resources/raw/xws-ships-cards.raw", "xwingai-xws-spec/src/main/resources/xws-ships-cards.json");
    }
}
