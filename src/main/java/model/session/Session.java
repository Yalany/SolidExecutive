package model.session;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Session {

    // milliseconds since last action to remove session from cache
    private final static long TIMEOUT_DELAY = 300000;

    private SessionOperator sessionOperator;

    // session content providers
    private EventProvider eventProvider;
    private Commands commands;

    // session state
    private Event currentEvent;
    private EventDeck eventDeck;
    private GameResources gameResources;
    private int currentMonth;

    // user specific data
    private boolean isSubscribed;
    private long subscriptionEndTime;

    public Session(EventProvider eventProvider, Commands commands, GameResources gameResources) {
        this(eventProvider, new EventDeck(), commands, gameResources);
    }

    public Session(EventProvider eventProvider, EventDeck deck, Commands commands, GameResources gameResources) {
        this.sessionOperator = new SessionOperator(this);

        this.eventProvider = eventProvider;
        this.commands = commands;

        this.currentEvent = eventProvider.getEventById(1, this);
        this.eventDeck = deck;
        this.gameResources = gameResources;
        this.currentMonth = 0;
    }

    public void acceptUserIntent(String intent) {
        if (currentEvent.canAcceptIntent(intent)) {
            currentEvent.acceptIntent(intent, sessionOperator);
            setCurrentEvent(eventDeck.pop());
            return;
        }

        if (commands.canAcceptCommand(intent))
            commands.acceptCommand(intent, this);

        // TODO: actions if intent is unacceptable
    }

    public String getEventText() {
        return currentEvent.getText();
    }

    public List<String> getButtonsText() {
        return currentEvent.getButtonsText();
    }

    public int getCurrentMonth() {
        return currentMonth;
    }


    public void setEventProvider(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public void setEventDeck(EventDeck eventDeck) {
        this.eventDeck = eventDeck;
    }

    public void setGameResources(GameResources gameResources) {
        this.gameResources = gameResources;
    }

    public void startTimeout() {
        timeoutTimer.cancel();
        timeoutTimer = new Timer();
        Timeout timeout = new Timeout();
        timeoutTimer.schedule(timeout, TIMEOUT_DELAY);
    }


    private Timer timeoutTimer = new Timer();

    private static class Timeout extends TimerTask {

        Timeout() {
        }

        @Override
        public void run() {

        }
    }

    // for operator
    EventDeck getEventDeck() {
        return eventDeck;
    }

    GameResources getGameResources() {
        return gameResources;
    }

    void nextMonth() {
        currentMonth++;
    }

    void exitGame() {

    }


    private void setCurrentEvent(int eventId) {
        currentEvent = eventProvider.getEventById(eventId, this);
    }
}
