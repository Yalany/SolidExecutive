package model.session;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Session {

    // milliseconds since last action to remove session from cache
    private final static long TIMEOUT_DELAY = 300000;

    private SessionOperator sessionOperator;

    private EventProvider eventProvider;
    private Commands commands;

    private Event currentEvent;
    private EventDeck eventDeck;
    private GameResources gameResources;
    private int currentMonth;

    private User user;

    public Session(EventProvider eventProvider,
                   Commands commands,
                   Event currentEvent,
                   EventDeck eventDeck,
                   GameResources gameResources,
                   int currentMonth,
                   User user) {
        this.sessionOperator = new SessionOperator(this);
        this.eventProvider = eventProvider;
        this.commands = commands;
        this.currentEvent = currentEvent;
        this.eventDeck = eventDeck;
        this.gameResources = gameResources;
        this.currentMonth = currentMonth;
        this.user = user;

    }

    public Session(int userId) {
        this(null, null, null, new EventDeck(), null, 0, new User(userId));
        this.sessionOperator = new SessionOperator(this);

    }

    public Session setEventProvider(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
        return this;
    }

    public Session setCommands(Commands commands) {
        this.commands = commands;
        return this;
    }

    public Session setGameResources(GameResources gameResources) {
        this.gameResources = gameResources;
        return this;
    }

    public Session setEventDeck(EventDeck eventDeck) {
        this.eventDeck = eventDeck;
        return this;
    }

    public Session setDefaultEventDeck() {
        this.eventDeck = new EventDeck();
        return this;
    }


    public int getId() {
        return user.getId();
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

    void newGame(EventProvider gameEventProvider, Commands gameCommands) {
        this.eventProvider = gameEventProvider;
        this.commands = gameCommands;
        reset();
    }

    void endGame(EventProvider menuEventProvider, Commands menuCommands) {
        this.eventProvider = menuEventProvider;
        this.commands = menuCommands;
        reset();
    }

    private void reset() {
        this.eventDeck = new EventDeck();
        this.currentMonth = 0;
        this.currentEvent = eventProvider.getEventById(eventDeck.pop(), this);
    }

    void exitGame() {

    }

    private void setCurrentEvent(int eventId) {
        currentEvent = eventProvider.getEventById(eventId, this);
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
}
