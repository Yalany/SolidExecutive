package model.session;

import java.util.List;

public class Commands {

//    public final static String INTENT_CATCH_ALL = "catch all";
//    public final static String INTENT_REPEAT = "repeat";
//    public final static String INTENT_MONTH = "month";
//    public final static String INTENT_RESOURCES = "resources";
//    public final static String INTENT_EXIT = "exit";
//    public final static String INTENT_HELP = "help";

    private List<Command> commands;

    boolean canAcceptCommand(String intent) {
        return commands.stream()
                .anyMatch(c -> c.haveIntent(intent));
    }

    void acceptCommand(String intent, Session context) {
        commands.stream()
                .filter(c -> c.haveIntent(intent))
                .forEach(c -> c.execute(context));
    }
}
