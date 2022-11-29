package com.bowling.model.impl;

import com.bowling.model.Frame;
import com.bowling.model.Lane;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultLane implements Lane {
    Map<String, List<Frame>> playersAndFrames = new HashMap<>();

    public Map<String, List<Frame>> getPlayersAndFrames() {
        return this.playersAndFrames;
    }

    public void computeBall(String player, String result) {
        if (playersAndFrames.containsKey(player)) {
            List<Frame> frames = playersAndFrames.get(player);
            Frame frame = frames.get(frames.size()-1);

            if (!frame.isCompleted()) {
                frame.registerBall(result);
            } else {
                // New frame 1st roll
                Frame newFrame = new DefaultFrame(frame.getFrameNumber() + 1, frame);
                newFrame.registerBall(result);
                frames.add(newFrame);
                playersAndFrames.put(player, frames);
            }
        } else {
            // New player 1st roll
            List<Frame> frames = new LinkedList();
            DefaultFrame frame = new DefaultFrame(1);
            frame.registerBall(result);
            frames.add(frame);
            playersAndFrames.put(player, frames);
        }
    }
}
