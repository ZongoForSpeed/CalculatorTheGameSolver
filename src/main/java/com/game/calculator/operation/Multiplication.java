package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class Multiplication extends AbstractOperation {
    private final int value;

    public Multiplication(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "*" + value;
    }

    @Override
    protected long apply(long value) {
        return value * this.value;
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return new Multiplication(op.applyAsInt(value));
    }
}
