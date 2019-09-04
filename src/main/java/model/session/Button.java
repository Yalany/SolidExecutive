package model.session;

import java.util.List;

class Button {

    private String name;
    private List<String> intents;
    private List<Operation> operations;

    Button(String name, List<String> intents, List<Operation> operations) {
        this.name = name;
        this.intents = intents;
        this.operations = operations;
    }

    Button setName(String name) {
        this.name = name;
        return this;
    }

    Button addIntent(String intent) {
        intents.add(intent);
        return this;
    }

    Button addOperation(Operation operation) {
        operations.add(operation);
        return this;
    }

    String getName() {
        return name;
    }

    boolean contains(String intent) {
        return intents.contains(intent);
    }

    void activate(SessionOperator operator) {
        operations.forEach(o -> o.activate(operator));
    }

    // TODO: check if option can be used with current player resources
    boolean doesFitSession(Session session) {
        return false;
    }

    // TODO: check if option requires subscription
    boolean isPaid() {
        return false;
    }
}
