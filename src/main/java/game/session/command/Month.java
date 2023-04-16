package game.session.command;

import game.session.Command;
import game.session.SessionOperator;

public class Month implements Command {
    @Override
    public boolean haveIntent(String intent) {
        return Command.INTENTS_MONTH.contains(intent);
    }

    @Override
    public void execute(SessionOperator contextOperator) {

    }
}
