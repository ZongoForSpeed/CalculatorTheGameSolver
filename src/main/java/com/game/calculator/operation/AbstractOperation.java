package com.game.calculator.operation;

import com.game.calculator.State;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOperation implements Operation {
    public State apply(State state) {
        if (!preCheck(state.getValue())) {
            return null;
        }
        List<Operation> operations = new ArrayList<>(state.getOperations());
        operations.add(this);
        return new State(apply(state.getValue()), state.getMoves() - 1, state.getGoal(), operations, state.getTransforms(), state.getStore());
    }

    protected abstract long apply(long value);

    protected boolean preCheck(long value) {
        return true;
    }
}
