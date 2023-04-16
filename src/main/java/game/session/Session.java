package game.session;

import java.util.List;

public final class Session {

    private final SessionOperator sessionOperator;

    private final ScreenProvider screenProvider;
    private final Commands commands;

    private final EventDeck eventDeck;
    private final GameResources gameResources;

    private Screen currentScreen;
    private int currentMonth;

    private Session(ScreenProvider screenProvider, Commands commands, EventDeck eventDeck, GameResources gameResources) {
        sessionOperator = new SessionOperator(this);
        this.screenProvider = screenProvider;
        this.commands = commands;
        this.eventDeck = eventDeck;
        this.gameResources = gameResources;
        this.currentMonth = 0;
        endTurn();
    }

    private void endTurn() {
        currentScreen = screenProvider.getEventById(eventDeck.pop());
    }

    // input
    public void acceptUserIntent(String intent) {
        if (currentScreen.canAcceptIntent(intent)) {
            currentScreen.acceptIntent(intent, sessionOperator);
            endTurn();
            return;
        }
        if (commands.canAcceptCommand(intent)) {
            commands.acceptCommand(intent, sessionOperator);
        }

        // TODO: actions if intent is unacceptable (Catch All)
    }

    // output
    public String getEventText() {
        return currentScreen.getText();
    }

    public List<String> getButtonsText() {
        return currentScreen.getButtonsText();
    }


    public void nextMonth() {
        currentMonth++;
    }

    int getCurrentMonth() {
        return currentMonth;
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

        private ScreenProvider screenProvider;
        private Commands commands;
        private EventDeck eventDeck;
        private GameResources gameResources;

        private Builder() {
        }

        public Builder setScreenProvider(ScreenProvider screenProvider) {
            this.screenProvider = screenProvider;
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
            return new Session(screenProvider, commands, eventDeck, gameResources);
        }
    }
}
