package model.session.commands;

import model.session.Commands;
import model.session.Session;

public class MenuCommands implements Commands {
    @Override
    public boolean canAcceptCommand(String intent) {
        return false;
    }

    @Override
    public void acceptCommand(String intent, Session context) {

    }
}
