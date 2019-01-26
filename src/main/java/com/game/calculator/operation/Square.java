package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class Square extends AbstractOperation {
    @Override
    protected long apply(long value) {
        return value * value;
    }

    @Override
    public String toString() {
        return "x²";
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return new Power(op.applyAsInt(2));
    }
}
