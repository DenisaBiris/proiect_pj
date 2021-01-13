package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectWriter;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtility {

    public static void writeToFile(List<Coffee> coffeeList)  {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        File file = new File("src/main/resources/coffeeList.json");

        try {
            writer.writeValue(file, coffeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Coffee> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/coffeeList.json")));
            return mapper.readValue(json, new TypeReference<List<Coffee>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
