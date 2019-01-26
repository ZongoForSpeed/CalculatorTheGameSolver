package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class Addition extends AbstractOperation {
    private final int value;

    public Addition(int value) {
        this.value = value;
    }

    @Override
    protected long apply(long value) {
        return value + this.value;
    }

    @Override
    public String toString() {
        if (value >= 0) {
            return "+" + value;
        } else {
            return "" + value;
        }
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return new Addition(op.applyAsInt(value));
    }
}
