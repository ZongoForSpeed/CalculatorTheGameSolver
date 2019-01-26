package com.game.calculator.operation;

import com.game.calculator.Utils;

import java.util.function.IntUnaryOperator;

public class Reverse extends AbstractOperation {
    @Override
    public String toString() {
        return "Reverse";
    }

    @Override
    protected long apply(long value) {
        return Utils.reverse(value);
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}