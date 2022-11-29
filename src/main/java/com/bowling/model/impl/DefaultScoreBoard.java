package com.bowling.model.impl;

import com.bowling.model.Frame;
import com.bowling.model.Lane;
import com.bowling.model.ScoreBoard;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultScoreBoard implements ScoreBoard {

    @Override
    public void print(Lane lane) {
        // Getting players
        for (String player: lane.getPlayersAndFrames().keySet()) {
            List<Frame> playerFrames = lane.getPlayersAndFrames().get(player);

            // Line 1 - Print Frame Header
            String frameHeader = playerFrames.stream()
                    .map(Frame::getFrameNumber)
                    .map(f -> f.toString())
                    .collect(Collectors.joining("\t\t"));
            System.out.println("Frame\t\t" + frameHeader);

            // Line 2 - Print player name
            System.out.println(player);

            // Line 3 - Print Pinfalls
            String pinfallsHeader = playerFrames.stream()
                    .map(Frame::formattedLabels)
                    .collect(Collectors.joining(""));
            System.out.println("Pinfalls\t" + pinfallsHeader);

            // Line 4 - Print score
            int sum = 0;
            System.out.print("Score\t\t");
            for (Frame frame: playerFrames) {
                sum += frame.getPoints();
                System.out.print(sum + "\t\t");
            }
            System.out.println("");
        }
    }
}
