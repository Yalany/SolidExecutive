package model.session;

import java.util.ArrayList;
import java.util.List;

public final class Commands {

    private final List<Command> commands;

    private Commands(List<Command> commands) {
        this.commands = commands;
    }

    boolean canAcceptCommand(String intent) {
        return commands.stream()
                .anyMatch(command -> command.haveIntent(intent));
    }

    void acceptCommand(String intent, SessionOperator contextOperator) {
        commands.stream()
                .filter(command -> command.haveIntent(intent))
                .forEach(command -> command.execute(contextOperator));
    }

    // builder
    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {

        private List<Command> commands;

        private Builder() {
            commands = new ArrayList<>();
        }

        public Builder addCommand(Command command) {
            commands.add(command);
            return this;
        }

        public Commands build() {
            return new Commands(commands);
        }
    }
}
