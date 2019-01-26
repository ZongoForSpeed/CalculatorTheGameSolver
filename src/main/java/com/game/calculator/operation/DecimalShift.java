package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class DecimalShift extends AbstractOperation {
    @Override
    public String toString() {
        return "<<";
    }

    @Override
    protected long apply(long value) {
        return value / 10;
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}
