package model.session;

public interface Commands {

//    public final static String INTENT_CATCH_ALL = "catch all";
//    public final static String INTENT_REPEAT = "repeat";
//    public final static String INTENT_MONTH = "month";
//    public final static String INTENT_RESOURCES = "resources";
//    public final static String INTENT_EXIT = "exit";
//    public final static String INTENT_HELP = "help";

    boolean canAcceptCommand(String intent);

    void acceptCommand(String intent, Session context);
}
