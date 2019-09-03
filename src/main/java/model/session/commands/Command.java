package model.session.commands;

import model.session.Session;

public interface Command {

    boolean haveIntent(String intent);

    void execute(Session context);
}
