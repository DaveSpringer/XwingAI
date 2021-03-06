package com.devspringer.xwing.xwingai.xws;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The XwingSpecEnhancer class is responsible for expanding the basic xws to include things such as pilot skill.
 */
public class XwingSpecEnhancer {

    public void enhancePilots() throws IOException {
        // Read in the XWS pilots.
        JsonParser parser = new JsonParser();
        JsonReader pilotsReader = new JsonReader(new FileReader("src/main/resources/xws-pilots.json"));
        JsonElement element = parser.parse(pilotsReader);
        JsonObject pilots = element.getAsJsonObject();

        // Build the voidstate id to cannonical name map.
        Map<Integer, String> shipIdToCanonicalMap = new HashMap<>();
        JsonReader shipsReader = new JsonReader(new FileReader("src/main/resources/xws-ships.json"));
        JsonArray ships = parser.parse(shipsReader).getAsJsonObject().getAsJsonArray("ships");
        for (JsonElement ship : ships) {
            JsonObject shipObject = ship.getAsJsonObject();
            shipIdToCanonicalMap.put(shipObject.get("id").getAsInt(), shipObject.get("canonical").getAsString());
        }

        // Read in the rebel pilots.
        JsonReader rebelPilotsReader = new JsonReader(new FileReader("src/main/resources/rebel-pilots.json"));
        JsonArray rebelPilots = parser.parse(rebelPilotsReader).getAsJsonArray();

        // Enhance the rebel pilots.
        JsonObject rebelPilotsXws = pilots.getAsJsonObject("rebel").getAsJsonObject("ships");
        enhanceCurrentFactionPilots(shipIdToCanonicalMap, rebelPilots, rebelPilotsXws);

        // Read in the imperial pilots.
        JsonReader imperialPilotsReader = new JsonReader(new FileReader("src/main/resources/imperial-pilots.json"));
        JsonArray imperialPilots = parser.parse(imperialPilotsReader).getAsJsonArray();

        // Enhance the imperial pilots.
        JsonObject imperialPilotsXws = pilots.getAsJsonObject("imperial").getAsJsonObject("ships");
        enhanceCurrentFactionPilots(shipIdToCanonicalMap, imperialPilots, imperialPilotsXws);

        // Read in the scum pilots.
        JsonReader scumPilotsReader = new JsonReader(new FileReader("src/main/resources/scum-pilots.json"));
        JsonArray scumPilots = parser.parse(scumPilotsReader).getAsJsonArray();

        // Enhance the scum pilots.
        JsonObject scumPiltosXws = pilots.getAsJsonObject("scum").getAsJsonObject("ships");
        enhanceCurrentFactionPilots(shipIdToCanonicalMap, scumPilots, scumPiltosXws);

        FileWriter writer = new FileWriter("src/main/resources/xws-enhanced.json");
        Gson gson = new Gson();
        String jsonString = gson.toJson(pilots);
        writer.write(jsonString);
    }

    private void enhanceCurrentFactionPilots(Map<Integer, String> shipIdToCanonicalMap, JsonArray currentPilots, JsonObject currentPilotsXws) {
        for (int i = 0; i < currentPilots.size(); i++) {
            JsonObject currentPilot = currentPilots.get(i).getAsJsonObject();
            if (currentPilot.get("id").getAsString().equals("all") || currentPilot.get("parent").getAsString().equals("all")) {
                continue;
            }
            String pilotKey = currentPilot.get("canonical").getAsString();
            Integer shipTypeId = currentPilot.get("ship_type_id").getAsInt();
            if (shipIdToCanonicalMap.get(shipTypeId) != null) {
                String shipCanonicalName = shipIdToCanonicalMap.get(shipTypeId);
                JsonElement nullCheck = currentPilotsXws.get(shipCanonicalName);
                if (nullCheck == null) {
                    continue;
                }
                JsonObject shipXws = nullCheck.getAsJsonObject();
                JsonObject pilotToEnhance = shipXws.getAsJsonObject("pilots").getAsJsonObject(pilotKey);
                addSkillToPilot(currentPilot, "pilotskill", pilotToEnhance, "pilot");
                addSkillToPilot(currentPilot, "primary", pilotToEnhance, "primary");
                addSkillToPilot(currentPilot, "agility", pilotToEnhance, "agility");
                addSkillToPilot(currentPilot, "hull", pilotToEnhance, "hull");
                addSkillToPilot(currentPilot, "shields", pilotToEnhance, "shields");
                System.err.println("Enhancement?" + pilotToEnhance);
            } else {
                System.err.println("Unexpected ship type id: " + shipTypeId + "\nProblem object: " + currentPilot.toString());
            }
        }
    }

    private void addSkillToPilot(JsonObject currentPilot, String skill, JsonObject pilotToEnhance, String newPropertyName) {
        if (currentPilot == null) return;
        if (pilotToEnhance == null) return;
        JsonElement skillElement = currentPilot.get(skill);
        if (skillElement == null) return;
        if (skillElement instanceof JsonNull) return;
        String newValue = skillElement.getAsString();
        pilotToEnhance.addProperty(newPropertyName, newValue);
    }

    public void extractAllPilotAbilities(String pilotsDirectory, String outputFileName) throws IOException {
        JsonParser parser = new JsonParser();
        JsonArray resultArray = new JsonArray();

        // Read in the rebel pilots.
        JsonReader rebelPilotsReader = new JsonReader(new FileReader(pilotsDirectory + "rebel-pilots.json"));
        JsonArray rebelPilots = parser.parse(rebelPilotsReader).getAsJsonArray();

        extractPilotAbilities(rebelPilots, resultArray, "rebel");

        // Read in the imperial pilots.
        JsonReader imperialPilotsReader = new JsonReader(new FileReader(pilotsDirectory + "imperial-pilots.json"));
        JsonArray imperialPilots = parser.parse(imperialPilotsReader).getAsJsonArray();

        extractPilotAbilities(imperialPilots, resultArray, "imperial");

        // Read in the scum pilots.
        JsonReader scumPilotsReader = new JsonReader(new FileReader(pilotsDirectory + "scum-pilots.json"));
        JsonArray scumPilots = parser.parse(scumPilotsReader).getAsJsonArray();

        extractPilotAbilities(scumPilots, resultArray, "scum");

        File outputFile = new File(outputFileName);
        outputFile.createNewFile();

        FileWriter writer = new FileWriter(outputFile);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(resultArray);
        writer.write(jsonResult);
        writer.close();
    }

    private void extractPilotAbilities(JsonArray pilots, JsonArray mergedPilots, String faction) {
        Map<Integer, String> shipIdToNameMap = new HashMap<>();
        for (int i = 0; i < pilots.size(); i++) {
            JsonObject currentPilot = pilots.get(i).getAsJsonObject();
            JsonElement ability = currentPilot.get("ability");
            if (ability != null) {
                JsonObject newPilotElement = new JsonObject();
                newPilotElement.addProperty("id", currentPilot.get("canonical").getAsString());
                newPilotElement.addProperty("name", currentPilot.get("name").getAsString());
                newPilotElement.addProperty("skill", currentPilot.get("pilot").getAsString());
                newPilotElement.addProperty("faction", faction);
                if (!ability.isJsonNull()) {
                    newPilotElement.addProperty("ability", ability.getAsString());
                }
                Integer shipId = currentPilot.get("ship_type_id").getAsInt();
                if (shipIdToNameMap.get(shipId) != null) {
                    newPilotElement.addProperty("shipName", shipIdToNameMap.get(shipId));
                }
                mergedPilots.add(newPilotElement);
            } else if (currentPilot.get("parent") != null) {
                String shipIdString = currentPilot.get("id").getAsString();
                if (shipIdString.contains("_")) {
                    Integer value = Integer.valueOf(shipIdString.split("_")[1]);
                    shipIdToNameMap.put(value, currentPilot.get("name").getAsString());
                }
            }
        }
    }

    public void reformatShipCards(String inputFile, String outputFile) throws IOException {
        // Read in the XWS ships.
        JsonParser parser = new JsonParser();
        JsonReader pilotsReader = new JsonReader(new FileReader(inputFile));
        JsonElement element = parser.parse(pilotsReader);
        pilotsReader.close();

        JsonArray resultArray = new JsonArray();

        JsonObject rootObject = element.getAsJsonObject();
        JsonObject ships = rootObject.get("ships").getAsJsonObject();
        for (Map.Entry<String, JsonElement> ship : ships.entrySet()) {
            JsonObject shipObject = ship.getValue().getAsJsonObject();
            shipObject.addProperty("id", shipObject.get("name").getAsString());
            resultArray.add(shipObject);
        }

        FileWriter writer = new FileWriter(outputFile);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(resultArray);
        writer.write(jsonResult);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        String pilotsDirectory = "xwingai-xws-spec/src/main/resources/";
        XwingSpecEnhancer enhancer = new XwingSpecEnhancer();
        String resultFile = "xwingai-xws-spec/src/main/resources/pilot-abilities.json";
        enhancer.extractAllPilotAbilities(pilotsDirectory, resultFile);

        // Modify the format of the ships
        String shipCardsFile = "xwingai-xws-spec/src/main/resources/xws-ships-cards.json";
        String resultShipsFile = "xwingai-xws-spec/src/main/resources/ships-final.json";
        enhancer.reformatShipCards(shipCardsFile, resultShipsFile);
    }
}
