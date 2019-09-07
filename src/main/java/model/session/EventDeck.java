package model.session;

import java.util.stream.IntStream;

class EventDeck {

    private final int[] events;
    private final int size;
    private final boolean isEndless;

    private int pointer; // points at next player event

    EventDeck() {
        this(60, true);
    }

    EventDeck(int size, boolean isEndless) {
        this.size = size;
        this.pointer = 0;
        this.events = new int[size];
        this.isEndless = isEndless;
        IntStream.range(0, size - 1).forEach(i -> this.events[i] = 0);
    }

    // puts event in place of first random event starting from specified offset
    void shuffleIn(int eventId, int offset) {
        if (offset < 0)
            throw new IllegalArgumentException("offset can't be lower than 0");
        if (offset >= size)
            throw new IllegalArgumentException("offset can't be greater or equal than deck size");

        var currentPointer = pointer;

        // resolving case when event wanted to be shuffled in from very top of deck
        if (offset == 0) {
            if (events[pointer] == 0) {
                events[pointer] = eventId;
                return;
            }
            movePointerBy(1);
        }

        movePointerBy(offset);

        // traversing deck in search of room for event
        while (!((events[pointer] == 0) || (pointer == currentPointer)))
            movePointerBy(1);

        // if pointers match then there is no room in deck for event
        if (pointer == currentPointer)
            return;

        events[pointer] = eventId;
        pointer = currentPointer;
    }

    void putOnTop(int eventId) {
        movePointerBy(-1);
        events[pointer] = eventId;
    }

    int pop() {
        var event = events[pointer];
        if (isEndless) events[pointer] = 0;
        else events[pointer] = -1;
        movePointerBy(1);
        return event;
    }

    private void movePointerBy(int steps) {
        if (steps < -1)
            throw new IllegalArgumentException("pointer steps can't be lower than -1");

        if (steps == -1) {
            if (pointer + steps < 0)
                pointer += size;
            pointer += steps;
        }
        if (steps > 0) {
            if (pointer + steps >= size)
                pointer -= size;
            pointer += steps;
        }
    }

    public enum Type {
        TUTORIAL("Tutorial"),
        STANDARD("Standard");

        private boolean isEndless;
        private int size;

        Type(String preset) {
            switch (preset) {
                case "Tutorial":
                    isEndless = false;
                    size = 20;
                    break;
                case "Standard":
                    isEndless = true;
                    size = 60;
                    break;
                default:
                    break;
            }
        }

        public boolean isEndless() {
            return isEndless;
        }

        public int getSize() {
            return size;
        }
    }
}
