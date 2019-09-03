package model.session.commands;

import model.session.Commands;
import model.session.Session;

public class GameCommands implements Commands {
    @Override
    public boolean canAcceptCommand(String intent) {
        return false;
    }

    @Override
    public void acceptCommand(String intent, Session context) {

    }
}
