package game.session.command;

import game.session.Command;
import game.session.SessionOperator;

public class Repeat implements Command {
    @Override
    public boolean haveIntent(String intent) {
        return Command.INTENTS_REPEAT.contains(intent);
    }

    @Override
    public void execute(SessionOperator contextOperator) {
        // literally do nothing
    }
}
