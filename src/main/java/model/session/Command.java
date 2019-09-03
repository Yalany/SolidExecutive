package model.session;

public interface Command {

    boolean haveIntent(String intent);

    void execute(Session context);
}
