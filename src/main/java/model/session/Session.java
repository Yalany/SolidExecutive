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

    public Session() {
        this.sessionOperator = new SessionOperator(this);

    }

    public Session(int userId) {
        this.sessionOperator = new SessionOperator(this);
        this.user = new User(userId);
        this.eventDeck = new EventDeck();
        this.currentMonth = 0;
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
