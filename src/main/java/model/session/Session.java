package model.session;

import java.util.List;

public class Session {

    private SessionOperator sessionOperator;

    // session content providers
    private EventProvider eventProvider;
    private Commands commands;

    // session state
    private Event currentEvent;
    private EventDeck eventDeck;
    private GameResources resources;
    private int currentMonth;

    // user specific data
    private boolean isSubscribed;
    private long subscriptionEndTime;

    public Session(EventProvider eventProvider, Commands commands, GameResources resources) {
        this(eventProvider, new EventDeck(), commands, resources);
    }

    public Session(EventProvider eventProvider, EventDeck deck, Commands commands, GameResources resources) {
        this.sessionOperator = new SessionOperator(this);

        this.eventProvider = eventProvider;
        this.commands = commands;

        this.currentEvent = eventProvider.getEventById(1, this);
        this.eventDeck = deck;
        this.resources = resources;
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


    public void setEventProvider(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public void startTimeout() {

    }


    private void setCurrentEvent(int eventId) {
        currentEvent = eventProvider.getEventById(eventId, this);
    }

    public void setEventDeck(EventDeck eventDeck) {
        this.eventDeck = eventDeck;
    }

    public void setResources(GameResources resources) {
        this.resources = resources;
    }

    void nextMonth() {
        currentMonth++;
    }


    EventDeck getEventDeck() {
        return eventDeck;
    }

    GameResources getResources() {
        return resources;
    }

    void exitGame() {

    }


    int getCurrentMonth() {
        return currentMonth;
    }
}
