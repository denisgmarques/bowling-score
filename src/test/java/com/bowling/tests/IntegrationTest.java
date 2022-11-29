package com.bowling.tests;

import com.bowling.exception.DataFileNotFoundException;
import com.bowling.exception.ExtraScoreException;
import com.bowling.exception.InvalidFileFormatException;
import com.bowling.exception.MissingDataException;
import com.bowling.model.Frame;
import com.bowling.model.Lane;
import datasources.TextFileLaneDatasource;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTest {
    @Test
    public void positiveScores_testCase1() throws IOException {
        String file = "positive/scores.txt";
        Lane lane = new TextFileLaneDatasource(file).getLane();

        // First Player
        Iterator<String> iterator = lane.getPlayersAndFrames().keySet().iterator();
        String firstPlayerName = iterator.next();
        List<Frame> frames = lane.getPlayersAndFrames().get(firstPlayerName);

        int sum = frames.stream()
                .mapToInt(ob ->(ob.getPoints()))
                .reduce(0, (a,b) -> a+b);

        assertEquals(sum, 167);

        // Second Player
        String secondPlayerName = iterator.next();
        frames = lane.getPlayersAndFrames().get(secondPlayerName);

        sum = frames.stream()
                .mapToInt(ob ->(ob.getPoints()))
                .reduce(0, (a,b) -> a+b);

        assertEquals(sum, 151);
    }

    @Test
    public void positivePerfect_testCase2() throws IOException {
        String file = "positive/perfect.txt";
        Lane lane = new TextFileLaneDatasource(file).getLane();

        String firstPlayerName = lane.getPlayersAndFrames().keySet().iterator().next();
        List<Frame> frames = lane.getPlayersAndFrames().get(firstPlayerName);

        int sum = frames.stream()
                .mapToInt(ob ->(ob.getPoints()))
                .reduce(0, (a,b) -> a+b);
        assertEquals(sum, 300);
    }

    @Test
    public void negativeExtraScore_testCase3() throws IOException {
        String file = "negative/extra-score.txt";

        Exception exception = assertThrows(ExtraScoreException.class, () -> {
            Lane lane = new TextFileLaneDatasource(file).getLane();
        });
    }

    @Test
    public void fileNotFound_testCase4() throws IOException {
        String file = "negative/FILENOTFOUND.txt";

        Exception exception = assertThrows(DataFileNotFoundException.class, () -> {
            Lane lane = new TextFileLaneDatasource(file).getLane();
        });
    }

    @Test
    public void emptyFile_testCase5() throws IOException {
        String file = "negative/empty.txt";

        Exception exception = assertThrows(MissingDataException.class, () -> {
            Lane lane = new TextFileLaneDatasource(file).getLane();
        });
    }

    @Test
    public void freeText_testCase6() throws IOException {
        String file = "negative/free-text.txt";

        Exception exception = assertThrows(InvalidFileFormatException.class, () -> {
            Lane lane = new TextFileLaneDatasource(file).getLane();
        });
    }

    @Test
    public void invalidScore_testCase7() throws IOException {
        String file = "negative/invalid-score.txt";

        Exception exception = assertThrows(InvalidFileFormatException.class, () -> {
            Lane lane = new TextFileLaneDatasource(file).getLane();
        });
    }

    @Test
    public void negativeScore_testCase8() throws IOException {
        String file = "negative/negative.txt";

        Exception exception = assertThrows(InvalidFileFormatException.class, () -> {
            Lane lane = new TextFileLaneDatasource(file).getLane();
        });
    }
}
