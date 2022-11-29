package com.bowling.model;

public interface Frame {
    void computePoints(String label);
    void registerBall(String result);
    boolean isCompleted();
    int getFrameNumber();
    int getPoints();
    boolean isStrike();
    boolean isSpare();
    void addPoints(int pts);
    Frame getPreviousFrame();
    String formattedLabels();
}
