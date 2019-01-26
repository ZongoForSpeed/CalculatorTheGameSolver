package com.game.calculator.operation;

import com.game.calculator.State;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class Store implements Operation {
    @Override
    public State apply(State state) {
        if (state.getValue() < 0) {
            return null;
        }
        List<Operation> operations = new ArrayList<>(state.getOperations());
        operations.add(this);
        return new State(state.getValue(), state.getMoves(), state.getGoal(), operations, state.getTransforms(), state.getValue());
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }

    @Override
    public String toString() {
        return "Store";
    }
}
