package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class Division extends AbstractOperation {
    private final int value;

    public Division(int value) {
        this.value = value;
    }

    @Override
    protected long apply(long value) {
        return value / this.value;
    }

    @Override
    protected boolean preCheck(long value) {
        return value % this.value == 0;
    }

    @Override
    public String toString() {
        return "/" + value;
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return new Division(op.applyAsInt(value));
    }
}
