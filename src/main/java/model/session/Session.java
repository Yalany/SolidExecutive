package model.session;

import java.util.List;

@SuppressWarnings("unused")
public class Session {

    private final SessionOperator sessionOperator;

    private final EventProvider eventProvider;
    private final Commands commands;

    private final EventDeck eventDeck;
    private final GameResources gameResources;

    private Event currentEvent;
    private int currentMonth;

    private final User user;

    private Session(EventProvider eventProvider, Commands commands, EventDeck eventDeck, GameResources gameResources, User user) {
        sessionOperator = new SessionOperator(this);
        this.eventProvider = eventProvider;
        this.commands = commands;
        this.eventDeck = eventDeck;
        this.gameResources = gameResources;
        this.currentEvent = eventProvider.getEventById(eventDeck.pop(), this);
        this.user = user;
    }


    public int getCurrentMonth() {
        return currentMonth;
    }

    public Session resetMonth() {
        currentMonth = 0;
        return this;
    }

    public Session nextMonth() {
        currentMonth++;
        return this;
    }


    private void setCurrentEvent(int eventId) {
        currentEvent = eventProvider.getEventById(eventId, this);
    }

    public void acceptUserIntent(String intent) {
        if (currentEvent.canAcceptIntent(intent)) {
            currentEvent.acceptIntent(intent);
            setCurrentEvent(eventDeck.pop());
            return;
        }
        if (commands.canAcceptCommand(intent)) {
            commands.acceptCommand(intent, this);
        }

        // TODO: actions if intent is unacceptable
    }

    public String getEventText() {
        return currentEvent.getText();
    }

    public List<String> getButtonsText() {
        return currentEvent.getButtonsText();
    }

    public String getId() {
        return user.getId();
    }

    // for operator
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

        private User user;

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

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Session build() {
            return new Session(eventProvider, commands, eventDeck, gameResources, user);
        }
    }
}
