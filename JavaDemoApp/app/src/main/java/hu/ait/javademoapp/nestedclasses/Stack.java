package hu.ait.javademoapp.nestedclasses;

import java.util.List;

public class Stack {

    private List<Double> items;

    public static class NoMoreElementsException extends Exception {
        public NoMoreElementsException(String msg) {
            super(msg);
        }
    }

    public Double pop() throws NoMoreElementsException {
        if (items.size() == 0) {
            throw new Stack.NoMoreElementsException("No more elements in the stack");
        }
        Double lastItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);
        return lastItem;
    }
}

