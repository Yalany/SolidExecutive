package game;

import game.session.*;
import game.session.command.Repeat;
import game.session.eventprovider.TestScreenProvider;

final class Repository {

    static User getUser(String id) {
        return new User(id);
    }

    static Session getSession(User forUser) {
        return Session.builder()
                .setScreenProvider(null) //
                .setCommands(null)      //
                .setEventDeck(null)     //
                .setGameResources(null) //
                .build();
    }

    static Session newSession(User forUser) {
        return Session.builder()
                .setScreenProvider(Repository.getEventProvider(forUser.isSubscribed() ? "gamePlus" : "game"))
                .setCommands(Repository.getCommands("game"))
                .setEventDeck(Repository.getEventDeck("standard"))
                .setGameResources(Repository.getGameResources("standard"))
                .build();
    }

    private static ScreenProvider getEventProvider(String type) {
        return new TestScreenProvider();
    }

    private static Commands getCommands(String type) {
        return Commands.builder()
                .addCommand(new Repeat())
                .build();
    }

    private static EventDeck getEventDeck(String preset) {
        return new EventDeck(60, true);
    }

    private static GameResources getGameResources(String preset) {
        return GameResources.builder()
                .addResource("gov", "одобрение администрацией", 75)
                .addResource("civ", "лояльность населения", 50)
                .addResource("money", "бюджет", 15000)
                .addResource("infrastructure", "состояние города", 200000)
                .build();
    }
}
