package com.game.calculator.operation;

import com.game.calculator.Utils;

import java.util.function.IntUnaryOperator;

public class Power extends AbstractOperation {
    private final int exposant;

    public Power(int exposant) {
        this.exposant = exposant;
    }

    @Override
    public String toString() {
        return "x^" + exposant;
    }

    @Override
    protected long apply(long value) {
        return Utils.power(value, exposant);
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return new Power(op.applyAsInt(exposant));
    }
}