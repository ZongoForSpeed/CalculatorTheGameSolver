package com.game.calculator.operation;

import com.game.calculator.Utils;

import java.util.function.IntUnaryOperator;

public class Numbers extends AbstractOperation {
    private final int value;

    public Numbers(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    protected long apply(long value) {
        return Utils.concatenate(value, this.value);
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return new Numbers(op.applyAsInt(value));
    }
}
