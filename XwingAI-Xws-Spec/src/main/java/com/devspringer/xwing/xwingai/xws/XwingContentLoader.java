package com.devspringer.xwing.xwingai.xws;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by David on 9/26/2015.
 */
public class XwingContentLoader {

    private static XwingContentLoader _instance;

    private JsonObject pilots;
    private JsonObject ships;
    private JsonObject upgrades;

    private XwingContentLoader() {

    }

    public static XwingContentLoader getInstance() {
        if (_instance == null) {
            _instance = new XwingContentLoader();
            _instance.init();
        }
        return _instance;
    }

    private void init() {
        // Read in the files.
        JsonParser parser = new JsonParser();

        try {
            // TODO: Correct how the resources files are looked up.
            JsonReader pilotsReader = new JsonReader(new FileReader("src/main/resources/xws-pilots.json"));
            JsonElement element = parser.parse(pilotsReader);
            pilots = element.getAsJsonObject();
        } catch (FileNotFoundException e) {
            System.err.println("Failed reading in the pilots. Please check configuration.");
            e.printStackTrace();
        }

        try {
            // TODO: Correct how the resources files are looked up.
            JsonReader shipsReader = new JsonReader(new FileReader("src/main/resources/xws-ships.json"));
            JsonElement element = parser.parse(shipsReader);
            ships = element.getAsJsonObject();
        } catch (FileNotFoundException e) {
            System.err.println("Failed reading in the ships. Please check configuration.");
            e.printStackTrace();
        }

        try {
            // TODO: Correct how the resources files are looked up.
            JsonReader upgradesReader = new JsonReader(new FileReader("src/main/resources/xws-upgrades.json"));
            JsonElement element = parser.parse(upgradesReader);
            upgrades = element.getAsJsonObject();
        } catch (FileNotFoundException e) {
            System.err.println("Failed reading in the ships. Please check configuration.");
            e.printStackTrace();
        }
    }


}
