package model;

import model.session.*;
import model.session.command.Repeat;
import model.session.operation.ChangeResource;

final class Repository {

    static User getUser(String id) {
        return new User(id);
    }

    static Session getSession(String id) {
        return Session.builder()
                .build();
    }

    static EventProvider getEventProvider(String type) {
        return (id, context) -> Event.builder()
                .setText("Это тестовое событие. В нём всего два варианта ответа," +
                        " первый портит вашу репутацию у властей на 10%, второй на столько же улучшает. type=" + type)
                .addButton(Button.builder()
                        .setName("Первый вариант")
                        .addIntent("1")
                        .addIntent("первый вариант")
                        .addOperation(new ChangeResource("gov", -10))
                        .setSession(context)
                        .build())
                .addButton(Button.builder()
                        .setName("Второй вариант")
                        .addIntent("2")
                        .addIntent("второй вариант")
                        .addOperation(new ChangeResource("gov", +10))
                        .setSession(context)
                        .build())
                .build();
    }

    static Commands getCommands(String type) {
        return Commands.builder()
                .addCommand(new Repeat())
                .build();
    }

    static EventDeck getEventDeck(String preset) {
        return new EventDeck(60, true);
    }

    static GameResources getGameResources(String preset) {
        return GameResources.builder()
                .addResource("gov", "одобрение администрацией", 75)
                .addResource("civ", "лояльность населения", 50)
                .addResource("money", "бюджет", 15000)
                .addResource("infrastructure", "состояние инфраструктуры", 200000)
                .build();
    }
}
