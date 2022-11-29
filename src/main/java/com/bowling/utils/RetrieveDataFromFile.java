package com.bowling.utils;

import com.bowling.Main;
import com.bowling.exception.InvalidFileFormatException;
import com.bowling.exception.MissingDataException;
import com.bowling.model.impl.DefaultLane;
import com.bowling.model.Lane;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class RetrieveDataFromFile {

    public static Lane loadFile(String path) throws FileNotFoundException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(path);

        if (is == null) {
            throw new FileNotFoundException("File '" + path + "' does not exists");
        }

        DefaultLane lane = new DefaultLane();

        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!Pattern.matches("^([aA0-zZ0 ]+)\\t([F0-9]{1})([0]{0,1})$", line)) throw new InvalidFileFormatException("Invalid format in this line " + line);
                String[] parts = line.split("\\t");
                lane.computeBall(parts[0], parts[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lane.getPlayersAndFrames().isEmpty()) throw new MissingDataException("File is empty");
        return lane;
    }
}
