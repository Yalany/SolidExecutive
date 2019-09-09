package model.session;

import java.util.ArrayList;
import java.util.List;


final class Button {

    private final String name;
    private final List<String> intents;
    private final List<Operation> operations;

    private Button(final String name, final List<String> intents, final List<Operation> operations) {
        this.name = name;
        this.intents = intents;
        this.operations = operations;
    }

    String getName() {
        return name;
    }

    boolean containsIntent(String intent) {
        return intents.contains(intent);
    }

    void activate(Session context) {
        operations.forEach(o -> o.activate(context.getOperator()));
    }

    // builder
    static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String name;
        private List<String> intents;
        private List<Operation> operations;
        private Builder() {
            intents = new ArrayList<>();
            operations = new ArrayList<>();
        }

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder addIntent(String intent) {
            intents.add(intent);
            return this;
        }

        Builder addOperation(Operation operation) {
            operations.add(operation);
            return this;
        }

        Button build() {
            if (name == null)
                throw new IllegalStateException("text can't be null");
            if (intents.isEmpty())
                throw new IllegalStateException("should have at least one intent");
            if (operations.isEmpty())
                throw new IllegalStateException("should have at least one operation");
            return new Button(name, intents, operations);
        }
    }
}
