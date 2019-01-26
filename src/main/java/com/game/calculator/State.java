package com.game.calculator;

import com.game.calculator.operation.Operation;
import com.game.calculator.operation.Transform;

import java.util.List;
import java.util.Objects;

public class State {
    private final long value;
    private final int moves;
    private final long goal;
    private final List<Operation> operations;
    private final List<Transform> transforms;
    private final Long store;

    public State(long value, int moves, long goal, List<Operation> operations, List<Transform> transforms, Long store) {
        this.value = value;
        this.moves = moves;
        this.goal = goal;
        this.operations = operations;
        this.transforms = transforms;
        this.store = store;
    }

    public long getValue() {
        return value;
    }

    public int getMoves() {
        return moves;
    }

    public long getGoal() {
        return goal;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public List<Transform> getTransforms() {
        return transforms;
    }

    public Long getStore() {
        return store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return value == state.value &&
                // moves == state.moves &&
                // goal == state.goal &&
                // Objects.equals(operations, state.operations) &&
                Objects.equals(transforms, state.transforms) &&
                Objects.equals(store, state.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, transforms, store);
    }


    @Override
    public String toString() {
        return "State{" +
                "value=" + value +
                ", moves=" + moves +
                ", goal=" + goal +
                ", operations=" + operations +
                ", transforms=" + transforms +
                ", store=" + store +
                '}';
    }
}
