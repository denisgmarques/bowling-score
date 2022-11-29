package com.bowling.model;

import java.util.List;
import java.util.Map;

public interface Lane {
    void computeBall(String player, String result);
    Map<String, List<Frame>> getPlayersAndFrames();
}
