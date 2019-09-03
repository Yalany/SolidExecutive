package model.session.command;

import model.session.Command;
import model.session.Session;

public class Repeat implements Command {
    @Override
    public boolean haveIntent(String intent) {
        return false;
    }

    @Override
    public void execute(Session context) {

    }
}
