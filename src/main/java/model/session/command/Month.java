package model.session.command;

import model.session.Command;
import model.session.SessionOperator;

public class Month implements Command {
    @Override
    public boolean haveIntent(String intent) {
        return Command.INTENTS_MONTH.contains(intent);
    }

    @Override
    public void execute(SessionOperator contextOperator) {

    }
}
