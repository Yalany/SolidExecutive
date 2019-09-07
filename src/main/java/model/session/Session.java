package model.session;

import java.util.List;

@SuppressWarnings("unused")
public class Session {

    private SessionOperator sessionOperator;

    private EventProvider eventProvider;
    private Commands commands;

    private Event currentEvent;
    private EventDeck eventDeck;
    private GameResources gameResources;
    private int currentMonth;

    private User user;

    private Session() {
        // private constructor
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

    public String getId() {
        return user.getId();
    }

    // for operator
    EventDeck getEventDeck() {
        return eventDeck;
    }

    GameResources getGameResources() {
        return gameResources;
    }

    // builder
    public static Builder builder() {
        return new Session().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setUser(String id) {
            Session.this.user = new User(id);
            return this;
        }

        public Builder setEventProvider(EventProvider eventProvider) {
            Session.this.eventProvider = eventProvider;
            return this;
        }

        public Builder setCommands(Commands commands) {
            Session.this.commands = commands;
            return this;
        }

        public Builder setEventDeck(String preset) {
            if ("Standard".equals(preset))
                Session.this.eventDeck = new EventDeck();
            else if ("Subscribed".equals(preset))
                Session.this.eventDeck = new EventDeck();
            else if ("Tutorial".equals(preset))
                Session.this.eventDeck = new TutorialEventDeck();
            return this;
        }

        public Builder setGameResources(String preset) {
            Session.this.gameResources = new GameResources();
            for (GameResources.Type type : GameResources.Type.values())
                if ("Easy".equals(preset))
                    Session.this.gameResources.addType(type.getName(), type.getEasyValue());
                else if ("Normal".equals(preset))
                    Session.this.gameResources.addType(type.getName(), type.getDefaultValue());
                else if ("Hard".equals(preset))
                    Session.this.gameResources.addType(type.getName(), type.getHardValue());
            return this;
        }

        public Session build() {
            Session.this.sessionOperator = new SessionOperator(Session.this);
            Session.this.currentEvent = Session.this.eventProvider.getEventById(Session.this.eventDeck.pop(), Session.this);
            Session.this.currentMonth = 0;
            return Session.this;
        }
    }
}
