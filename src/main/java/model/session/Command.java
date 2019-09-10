package model.session;

import java.util.ArrayList;
import java.util.List;

public interface Command {

    List<String> INTENTS_REPEAT = new ArrayList<>();
    List<String> INTENTS_MONTH = new ArrayList<>();
    List<String> INTENTS_RESOURCES = new ArrayList<>();
    List<String> INTENTS_EXIT = new ArrayList<>();
    List<String> INTENTS_HELP = new ArrayList<>();

    boolean haveIntent(String intent);

    void execute(SessionOperator contextOperator);
}
