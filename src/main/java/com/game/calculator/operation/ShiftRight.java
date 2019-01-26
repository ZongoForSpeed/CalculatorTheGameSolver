package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class ShiftRight extends AbstractOperation {
    private static long shiftRight(long value) {
        if (value < 0) {
            return -shiftRight(-value);
        }

        String s = String.valueOf(value);
        return Integer.valueOf(s.charAt(s.length() - 1) + s.substring(0, s.length() - 1));
    }

    @Override
    protected long apply(long value) {
        return shiftRight(value);
    }


    @Override
    public String toString() {
        return "Shift>";
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}
