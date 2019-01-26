package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class Negation extends AbstractOperation {

    @Override
    protected long apply(long value) {
        return -value;
    }

    @Override
    public String toString() {
        return "+/-";
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}
