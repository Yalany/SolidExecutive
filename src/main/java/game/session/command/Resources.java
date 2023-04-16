package game.session.command;

import game.session.Command;
import game.session.SessionOperator;

public class Resources implements Command {
    @Override
    public boolean haveIntent(String intent) {
        return Command.INTENTS_RESOURCES.contains(intent);
    }

    @Override
    public void execute(SessionOperator contextOperator) {

    }
}
