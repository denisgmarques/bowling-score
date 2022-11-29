package com.bowling.model.impl;

import com.bowling.exception.ExtraScoreException;
import com.bowling.exception.MissingPreviousFrameException;
import com.bowling.exception.OutOfRangeFrameNumberException;
import com.bowling.model.Frame;
import com.bowling.utils.Constants;
import lombok.Getter;
import lombok.Setter;

public class DefaultFrame implements Frame {
    private static final int MIN_FRAME = 1;
    private static final int MAX_FRAME = 10;

    @Getter
    private Frame previousFrame;
    @Getter
    private int frameNumber;

    @Getter @Setter
    private String label1;
    @Getter @Setter
    private String label2;
    @Getter @Setter
    private String label3;
    private int points;

    public int getPoints() {
        return this.points;
    }

    private int rolls;

    public DefaultFrame(Integer frameNumber) {
        this(frameNumber, null);
    }

    public DefaultFrame(Integer frameNumber, Frame previousFrame) {
        if (frameNumber == null || frameNumber < MIN_FRAME) {
            throw new OutOfRangeFrameNumberException("Frame number must be between 1 and 10");
        }

        if (frameNumber > MAX_FRAME) {
            throw new ExtraScoreException("Extra score was detected");
        }

        if (frameNumber != MIN_FRAME && previousFrame == null) {
            throw new MissingPreviousFrameException("Only the first frame has no previous frame");
        }

        this.frameNumber = frameNumber;
        this.previousFrame = previousFrame;
    }

    public void registerBall(String result) {
        this.rolls++;

        switch (rolls) {
            case 1:
                label1 = result;
                break;
            case 2:
                label2 = result;
                break;
            case 3:
                label3 = result;
                break;
        }

        computePoints(result);
    }

    public void computePoints(String label) {
        int pts = getPointsFor(label);
        this.points += pts;

        // Compute strike / spares
        if (isNotFirst()) {
            if (rolls == 1 && (previousFrame.isStrike() || previousFrame.isSpare())) {
                previousFrame.addPoints(pts);

                Frame prevPrevious = previousFrame.getPreviousFrame();
                if (previousFrame.isStrike() && prevPrevious != null && prevPrevious.isStrike()) {
                    prevPrevious.addPoints(pts);
                }
            } else if (rolls == 2 && previousFrame.isStrike()) {
                previousFrame.addPoints(pts);
            }
        }
    }

    public boolean isCompleted() {
        // Tenth frame
        if (this.frameNumber == MAX_FRAME) {
            if (rolls == 3) return true;

            // Strike or Spare, has 3rd ball
            if (rolls == 2 && points < 10) {
                return true;
            }
        } else {
            if (rolls == 2) {
                return true;
            } else if (rolls == 1 && points == 10) {
                return true;
            }
        }

        return false;
    }

    public String formattedLabels() {
        // STRIKE
        if (rolls == 1 && label1.equals("10")) {
            return "\t" + Constants.STRIKE + "\t";
        }

        // SPARE
        if (rolls == 2 && (getPointsFor(label1) + getPointsFor(label2)) == 10) {
            return label1 + "\t" + Constants.SPARE + "\t";
        }

        // TENTH FRAME EXTRA BALL
        if (rolls == 3) {
            return getExtraBallPrintLabels(label1, label2, label3);
        } else {
            return label1 + "\t" + label2 + "\t";
        }
    }

    private String getExtraBallPrintLabels(String label1, String label2, String label3) {
        /**
         * Spare:
         *  Case 1:   X    9   /
         *  Case 2:   9    /   X
         */

        // Case 1
        if (getPointsFor(label2) + getPointsFor(label3) == 10) {
            return labelOrStrike(label1) + "\t" + label2 + "\t" + Constants.SPARE;
        }

        // Case 2
        if (getPointsFor(label1) + getPointsFor(label2) == 10) {
            return label1 + "\t" + Constants.SPARE + "\t" + labelOrStrike(label3);
        }

        return labelOrStrike(label1) + "\t" + labelOrStrike(label2) + "\t" + labelOrStrike(label3);
    }

    private String labelOrStrike(String label) {
        return label.equals("10") ? Constants.STRIKE : label;
    }

    public void addPoints(int pts) {
        this.points += pts;
    }

    private int getPointsFor(String label) {
        if (label == null) return 0;
        if (label.equals(Constants.FAULT)) return 0;
        return Integer.valueOf(label);
    }

    public boolean isSpare() {
        return (rolls > 1 && points >= 10);
    }

    public boolean isStrike() {
        return (rolls == 1 && points >= 10);
    }

    private boolean isNotFirst() {
        return (frameNumber > 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultFrame frame = (DefaultFrame) o;
        return frameNumber == frame.frameNumber;
    }
}
