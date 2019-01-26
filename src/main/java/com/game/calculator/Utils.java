package com.game.calculator;

public class Utils {
    public static long concatenate(long a, long b) {
        if (a == 0) {
            return b;
        }
        return Long.valueOf("" + a + b);
    }

    public static long reverse(long s) {
        if (s < 0) {
            return -reverse(-s);
        }
        StringBuilder sb = new StringBuilder();
        String toString = sb.append(s).reverse().toString();
        return Long.valueOf(toString);
    }

    public static long inv10(long s) {
        StringBuilder sb = new StringBuilder();
        for (char c : String.valueOf(s).toCharArray()) {
            sb.append(inv10(c));
        }

        return Long.valueOf(sb.toString());
    }

    private static char inv10(char c) {
        switch (c) {
            case '1':
                return '9';
            case '2':
                return '8';
            case '3':
                return '7';
            case '4':
                return '6';
            case '5':
                return '5';
            case '6':
                return '4';
            case '7':
                return '3';
            case '8':
                return '2';
            case '9':
                return '1';
            case '0':
                return '0';
        }
        return c;
    }

    public static long mirror(long value) {
        if (value < 0) {
            return -mirror(-value);
        }

        return Long.valueOf(value + new StringBuilder().append(value).reverse().toString());
    }

    public static long power(long a, int b) {
        int result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result *= a;
            }
            b >>= 1;
            a *= a;
        }
        return result;
    }

    public static long portal(long value, int a, int b) {
        long pa = power(10, a);
        long pb = power(10, b);
        while (value >= pa) {
            long gauche = value / pa;
            value %= pa;
            while (gauche > 0) {
                value += (gauche % 10) * pb;
                gauche /= 10;
            }
        }

        return value;
    }
}
