package com.game.calculator.operation;

import com.game.calculator.Utils;

import java.util.function.IntUnaryOperator;

public class Inv10 extends AbstractOperation {
    @Override
    protected long apply(long value) {
        return Utils.inv10(value);
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }

    @Override
    public String toString() {
        return "Inv10";
    }
}
