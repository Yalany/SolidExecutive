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

    String getName() {
        return name;
    }

    boolean contains(String intent) {
        return intents.contains(intent);
    }

    void activate(SessionOperator operator) {
        operations.forEach(o -> o.activate(operator));
    }
}
