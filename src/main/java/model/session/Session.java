package model.session;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class Session {

    private SessionOperator sessionOperator;

    private EventProvider eventProvider;
    private Commands commands;

    private Event currentEvent;
    private EventDeck eventDeck;
    private GameResources gameResources;
    private int currentMonth;

    private final User user;

    public Session(EventProvider eventProvider, Commands commands, int userId) {
        this(eventProvider, commands, null, new EventDeck(), null, 0, new User(userId));
    }

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

    public Session setProcessingContext(EventProvider eventProvider, Commands commands) {
        this.eventProvider = eventProvider;
        this.commands = commands;
        return this;
    }

    public Session setEventDeck(EventDeck eventDeck) {
        this.eventDeck = eventDeck;
        setCurrentEvent(eventDeck.pop());
        return this;
    }

    public Session setGameResources(GameResources gameResources) {
        this.gameResources = gameResources;
        return this;
    }

    public Session nextMonth() {
        currentMonth++;
        return this;
    }

    public Session resetMonth() {
        currentMonth = 0;
        return this;
    }

    private void setCurrentEvent(int eventId) {
        currentEvent = eventProvider.getEventById(eventId, this);
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

    public int getId() {
        return user.getId();
    }

    // for operator
    EventDeck getEventDeck() {
        return eventDeck;
    }

    GameResources getGameResources() {
        return gameResources;
    }

    void exitGame() {

    }
}
