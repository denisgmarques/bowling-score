package com.bowling;

import com.bowling.exception.DataFileNotFoundException;
import com.bowling.model.Lane;
import com.bowling.model.ScoreBoard;
import com.bowling.model.impl.DefaultScoreBoard;
import com.bowling.utils.PrintSplashScreen;
import datasources.TextFileLaneDatasource;

public class Main {
    public static void main(String[] args) {
        PrintSplashScreen.sysoutPrint();

        String[] files = { "scores.txt", "perfect.txt", "scores-spare-extra-ball.txt"};

        ScoreBoard sb = new DefaultScoreBoard();

        for (String file: files) {
            System.out.println("\n\nLoading file: " + file);
            System.out.println("--------------------------------------------------------\n");

            try {
                // Load data
                Lane lane = new TextFileLaneDatasource(file).getLane();

                // Print Score Board
                sb.print(lane);
            } catch (DataFileNotFoundException e) {
                System.out.println("File not found");
            }

        }
    }
}