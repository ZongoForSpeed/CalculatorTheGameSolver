package com.game.calculator.operation;

import com.game.calculator.State;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class AdditionTransformOperation implements Operation {
    private final int value;

    public AdditionTransformOperation(int value) {
        this.value = value;
    }

    @Override
    public State apply(State state) {
        List<Operation> operations = new ArrayList<>(state.getOperations());
        operations.add(this);
        List<Transform> transforms = new ArrayList<>(state.getTransforms());
        transforms.add(operation -> operation.transform(this::apply));
        return new State(state.getValue(), state.getMoves() - 1, state.getGoal(), operations, transforms, state.getStore());
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }

    private int apply(int v) {
        if (v < 0) {
            return -apply(-v);
        }

        return v + value;
    }

    @Override
    public String toString() {
        return "[" + (value > 0 ? "+" : "") + value + ']';
    }
}
