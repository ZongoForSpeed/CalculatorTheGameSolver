package com.game.calculator.operation;

import com.game.calculator.Utils;

import java.util.function.IntUnaryOperator;

public class Mirror extends AbstractOperation {

    @Override
    protected boolean preCheck(long value) {
        return Math.abs(value) < 10000;
    }

    @Override
    protected long apply(long value) {
        return Utils.mirror(value);
    }

    @Override
    public String toString() {
        return "Mirror";
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}
