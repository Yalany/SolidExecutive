package model.session;

import java.util.ArrayList;
import java.util.List;

public class Commands {

//    public final static String INTENT_CATCH_ALL = "catch all";
//    public final static String INTENT_REPEAT = "repeat";
//    public final static String INTENT_MONTH = "month";
//    public final static String INTENT_RESOURCES = "resources";
//    public final static String INTENT_EXIT = "exit";
//    public final static String INTENT_HELP = "help";

    private final List<Command> commands;

    private Commands(List<Command> commands) {
        this.commands = commands;
    }

    boolean canAcceptCommand(String intent) {
        return commands.stream()
                .anyMatch(c -> c.haveIntent(intent));
    }

    void acceptCommand(String intent, Session context) {
        commands.stream()
                .filter(c -> c.haveIntent(intent))
                .forEach(c -> c.execute(context));
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
