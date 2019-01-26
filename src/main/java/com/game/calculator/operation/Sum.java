package com.game.calculator.operation;

import java.util.function.IntUnaryOperator;

public class Sum extends AbstractOperation {
    private static long somme(long n) {
        long resultat = 0;
        while (n != 0) {
            resultat += n % 10;
            n /= 10;
        }

        return resultat;
    }

    @Override
    public String toString() {
        return "Sum";
    }

    @Override
    protected long apply(long value) {
        return somme(value);
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        return this;
    }
}
