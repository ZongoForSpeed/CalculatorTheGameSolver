package com.game.calculator.operation;

import com.game.calculator.State;

import java.util.function.IntUnaryOperator;

public interface Operation {
    State apply(State state);

    Operation transform(IntUnaryOperator op);
}
