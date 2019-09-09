package model.session;

import java.util.List;

public final class Session {

    private final SessionOperator sessionOperator;

    private final EventProvider eventProvider;
    private final Commands commands;

    private final EventDeck eventDeck;
    private final GameResources gameResources;

    private Event currentEvent;
    private int currentMonth;

    private Session(EventProvider eventProvider, Commands commands, EventDeck eventDeck, GameResources gameResources) {
        sessionOperator = new SessionOperator(this);
        this.eventProvider = eventProvider;
        this.commands = commands;
        this.eventDeck = eventDeck;
        this.gameResources = gameResources;
        this.currentMonth = 0;
        nextEvent();
    }

    private void nextEvent() {
        currentEvent = eventProvider.getEventById(eventDeck.pop(), this);
    }

    // input
    public void acceptUserIntent(String intent) {
        if (currentEvent.canAcceptIntent(intent)) {
            currentEvent.acceptIntent(intent);
            nextEvent();
            return;
        }
        if (commands.canAcceptCommand(intent)) {
            commands.acceptCommand(intent, this);
        }

        // TODO: actions if intent is unacceptable (Catch All)
    }

    // output
    public String getEventText() {
        return currentEvent.getText();
    }

    public List<String> getButtonsText() {
        return currentEvent.getButtonsText();
    }


//    public void nextMonth() {
//        currentMonth++;
//    }

    int getCurrentMonth() {
        return currentMonth;
    }

    SessionOperator getOperator() {
        return sessionOperator;
    }

    EventDeck getEventDeck() {
        return eventDeck;
    }

    GameResources getGameResources() {
        return gameResources;
    }


    // builder
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private EventProvider eventProvider;
        private Commands commands;
        private EventDeck eventDeck;
        private GameResources gameResources;

        private Builder() {
        }

        public Builder setEventProvider(EventProvider eventProvider) {
            this.eventProvider = eventProvider;
            return this;
        }

        public Builder setCommands(Commands commands) {
            this.commands = commands;
            return this;
        }

        public Builder setEventDeck(EventDeck eventDeck) {
            this.eventDeck = eventDeck;
            return this;
        }

        public Builder setGameResources(GameResources gameResources) {
            this.gameResources = gameResources;
            return this;
        }

        public Session build() {
            return new Session(eventProvider, commands, eventDeck, gameResources);
        }
    }
}
