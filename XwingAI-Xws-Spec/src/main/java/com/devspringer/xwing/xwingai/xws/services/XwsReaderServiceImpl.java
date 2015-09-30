package com.devspringer.xwing.xwingai.xws.services;

import com.devspringer.xwing.xwingai.common.dto.Pilot;
import com.devspringer.xwing.xwingai.common.dto.Ship;
import com.devspringer.xwing.xwingai.common.dto.XwingList;
import com.devspringer.xwing.xwingai.common.services.XwsReaderService;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The XwsReaderServiceImpl class is an implementation of the XwsReaderService.
 */
public class XwsReaderServiceImpl implements XwsReaderService {

    private final Map<String, List<Ship>> versionToShipMap = new HashMap<>();
    private final Map<String, List<Pilot>> versionToPilotMap = new HashMap<>();

    @Override
    public XwingList readInXwsList(String jsonXws) {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonXws);
        JsonObject rootObject = jsonElement.getAsJsonObject();

        XwingList resultList = null;

        if (rootObject.get("version") != null && !rootObject.get("version").isJsonNull()) {
            String version = rootObject.get("version").getAsString();
            switch (version) {
                case "0.3.0" :
                    resultList = parseForVersion030(rootObject);
                    break;
                default:
                    // TODO: Log error.
                    System.err.println("Could not find parser method for specified version.");
            }
        }
        return resultList;
    }

    @Override
    public List<Ship> getAllShips(String version) {
        List<Ship> resultList = versionToShipMap.get(version);
        if (resultList == null) {
            Type listType = new TypeToken<ArrayList<Ship>>() {}.getType();
            try {
                // TODO: Load correct version.
                FileReader reader = new FileReader("src/main/resources/ships-final.json");
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(reader);
                Gson gson = new Gson();
                resultList = gson.fromJson(element.getAsJsonArray(), listType);
                reader.close();
                versionToShipMap.put(version, resultList);
            } catch (FileNotFoundException e) {
                // TODO
                e.printStackTrace();
            } catch (IOException e) {
                // TODO
                e.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public List<Pilot> getAllPilots(String version) {
        List<Pilot> resultList = versionToPilotMap.get(version);
        if (resultList == null) {
            Type listType = new TypeToken<ArrayList<Pilot>>() {}.getType();
            try {
                // TODO: Load correct version.
                FileReader reader = new FileReader("src/main/resources/pilot-abilities.json");
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(reader);
                Gson gson = new Gson();
                resultList = gson.fromJson(element.getAsJsonArray(), listType);
                reader.close();
                versionToPilotMap.put(version, resultList);
            } catch (FileNotFoundException e) {
                // TODO
                e.printStackTrace();
            } catch (IOException e) {
                // TODO
                e.printStackTrace();
            }
        }
        return resultList;
    }

    private XwingList parseForVersion030(JsonObject rootObject) {
        XwingList resultList = new XwingList();
        resultList.setName(getProperty("name", rootObject));
        resultList.setFaction(getProperty("faction", rootObject));
        JsonElement pilotsElement = rootObject.get("pilots");
        if (pilotsElement != null && !pilotsElement.isJsonNull()) {
            JsonArray pilotsArray = pilotsElement.getAsJsonArray();
            for (int i = 0; i < pilotsArray.size(); i++) {
                JsonObject pilot = pilotsArray.get(i).getAsJsonObject();
            }
        }
        return null;
    }

    private String getProperty(String propertyName, JsonObject rootObject) {
        String result = null;
        if (rootObject.get(propertyName) != null && !rootObject.get(propertyName).isJsonNull()) {
            result = rootObject.get(propertyName).getAsString();
        }
        return result;
    }
}
