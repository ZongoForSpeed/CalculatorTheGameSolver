package com.game.calculator.operation;

import com.game.calculator.State;
import com.game.calculator.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class Paste implements Operation {
    @Override
    public State apply(State state) {
        if (state.getStore() == null) {
            return null;
        }
        List<Operation> operations = new ArrayList<>(state.getOperations());
        operations.add(this);
        return new State(Utils.concatenate(state.getValue(), state.getStore()), state.getMoves() - 1, state.getGoal(), operations, state.getTransforms(), state.getStore());
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }

    @Override
    public String toString() {
        return "Paste";
    }
}
