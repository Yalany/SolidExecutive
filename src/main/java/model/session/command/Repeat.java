package model.session.command;

import model.session.Command;
import model.session.SessionOperator;

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
