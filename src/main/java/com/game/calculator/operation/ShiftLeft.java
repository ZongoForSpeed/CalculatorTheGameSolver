package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class ShiftLeft extends AbstractOperation {
    private static long shiftLeft(long value) {
        if (value < 0) {
            return -shiftLeft(-value);
        }

        String s = String.valueOf(value);
        return Long.valueOf(s.substring(1) + s.charAt(0));
    }

    @Override
    protected long apply(long value) {
        return shiftLeft(value);
    }


    @Override
    public String toString() {
        return "<Shift";
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}
