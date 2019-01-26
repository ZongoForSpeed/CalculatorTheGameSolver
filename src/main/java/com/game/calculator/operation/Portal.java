package com.game.calculator.operation;

import com.game.calculator.State;
import com.game.calculator.Utils;

import java.util.function.IntUnaryOperator;

public class Portal implements Operation {
    private final int a;
    private final int b;

    public Portal(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public State apply(State state) {
        return new State(Utils.portal(state.getValue(), a, b),
                state.getMoves(),
                state.getGoal(),
                state.getOperations(),
                state.getTransforms(),
                state.getStore());
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }

    @Override
    public String toString() {
        return "Portal";
    }
}
