package model.session.commands;

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
