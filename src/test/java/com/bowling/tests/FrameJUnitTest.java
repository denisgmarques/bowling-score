package com.bowling.tests;

import com.bowling.exception.MissingPreviousFrameException;
import com.bowling.exception.OutOfRangeFrameNumberException;
import com.bowling.model.impl.DefaultFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameJUnitTest {
    @Test
    void badFrameRange_testCase1() {
        Exception exception = assertThrows(OutOfRangeFrameNumberException.class, () -> {
            new DefaultFrame(0);
        });
    }

    @Test
    void badPreviousFrame_testCase2() {
        Exception exception = assertThrows(MissingPreviousFrameException.class, () -> {
            new DefaultFrame(2);
        });
    }

    @Test
    void completed_testCase3() {
        DefaultFrame frame = new DefaultFrame(1);
        frame.registerBall("7");
        frame.registerBall("2");

        assertTrue(frame.isCompleted());
    }
}
