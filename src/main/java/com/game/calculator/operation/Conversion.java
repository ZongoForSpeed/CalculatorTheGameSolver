package com.game.calculator.operation;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class Conversion extends AbstractOperation {
    private final String a;
    private final String b;

    public Conversion(String a, String b) {
        this.a = a;
        this.b = b;
    }

    private static long conversion(long value, String a, String b) {
        return Long.valueOf(String.valueOf(value).replace(a, b));
    }

    @Override
    public String toString() {
        return a + "=>" + b;
    }

    @Override
    protected long apply(long value) {
        return conversion(value, a, b);
    }

    @Override
    public Operation transform(IntUnaryOperator op) {
        Function<String, String> f = s -> {
            Integer of = op.applyAsInt(Integer.valueOf(s));
            return of.toString();
        };
        return new Conversion(f.apply(a), f.apply(b));
    }
}
