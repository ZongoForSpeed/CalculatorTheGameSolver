package com.game.calculator;

import com.game.calculator.operation.Addition;
import com.game.calculator.operation.AdditionTransformOperation;
import com.game.calculator.operation.Conversion;
import com.game.calculator.operation.DecimalShift;
import com.game.calculator.operation.Division;
import com.game.calculator.operation.Inv10;
import com.game.calculator.operation.Mirror;
import com.game.calculator.operation.Multiplication;
import com.game.calculator.operation.Negation;
import com.game.calculator.operation.Numbers;
import com.game.calculator.operation.Operation;
import com.game.calculator.operation.Paste;
import com.game.calculator.operation.Portal;
import com.game.calculator.operation.Power;
import com.game.calculator.operation.Reverse;
import com.game.calculator.operation.ShiftLeft;
import com.game.calculator.operation.ShiftRight;
import com.game.calculator.operation.Square;
import com.game.calculator.operation.Store;
import com.game.calculator.operation.Sum;
import com.game.calculator.operation.Transform;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver {
    private static List<Operation> solve(int goal, int moves, int value, List<Operation> operations, Portal portal) {
        State start = new State(value, moves, goal, Collections.emptyList(), Collections.emptyList(), null);

        Set<State> seen = new HashSet<>();
        seen.add(start);

        Deque<State> states = new ArrayDeque<>();
        states.add(start);

        // int move = 0;
        while (!states.isEmpty()) {
            State state = states.pop();
            List<Operation> operationsTransformed = operations.stream().map(operation -> {
                for (Transform transform : state.getTransforms()) {
                    operation = transform.transform(operation);
                }
                return operation;
            }).collect(Collectors.toList());
            for (Operation operation : operationsTransformed) {
                State next = operation.apply(state);
                if (next == null || seen.contains(next) || Math.abs(next.getValue()) > 1000000) {
                    continue;
                }

                if (portal != null) {
                    next = portal.apply(next);
                }

                if (next.getGoal() == next.getValue()) {
                    return next.getOperations();
                }

                seen.add(next);

                if (next.getMoves() > 0) {
                    states.add(next);
                }
            }

            // move++;
        }

        return Collections.emptyList();
    }

    public static String solve(int goal, int moves, int value, String operations, Portal portal) {
        List<Operation> operationList = parse(operations);
        List<Operation> solve = solve(goal, moves, value, operationList, portal);
        return solve.stream().map(Objects::toString).collect(Collectors.joining(" "));
    }

    private static List<Operation> parse(String s) {
        List<Operation> operations = new ArrayList<>();
        String[] strings = s.split(" ");
        for (String op : strings) {
            Operation operation = parseOperation(op);
            if (operation != null) {
                operations.add(operation);
            }
        }

        return operations;
    }

    private static Operation parseOperation(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        if (s.contains("=>")) {
            String[] split = s.split("=>");
            return new Conversion(split[0], split[1]);
        }

        if (s.equals("+/-")) {
            return new Negation();
        }

        switch (s.charAt(0)) {
            case '+':
                return new Addition(Integer.valueOf(s.substring(1)));
            case '-':
                return new Addition(-Integer.valueOf(s.substring(1)));
            case '*':
                return new Multiplication(Integer.valueOf(s.substring(1)));
            case '/':
                return new Division(Integer.valueOf(s.substring(1)));
            case '<':
                if (s.equals("<<")) {
                    return new DecimalShift();
                }
                return new ShiftLeft();
            case '>':
                return new ShiftRight();
            case 'x':
                if (s.charAt(1) == '²') {
                    return new Square();
                } else {
                    return new Power(Integer.valueOf(s.substring(1)));
                }
            case 'P':
                return new Paste();
            case 'R':
                return new Reverse();
            case 'S':
                if ("Store".equals(s)) {
                    return new Store();
                }
                return new Sum();
            case 'M':
                return new Mirror();
            case '[':
                return new AdditionTransformOperation(Integer.valueOf(s.substring(1, s.length() - 1)));
            case 'I':
                return new Inv10();
        }

        if (StringUtils.isNumeric(s)) {
            return new Numbers(Integer.valueOf(s));
        }

        return null;
    }
}
