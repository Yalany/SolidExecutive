package model.session.eventprovider;

import model.session.Button;
import model.session.Event;
import model.session.EventProvider;
import model.session.operation.EndSession;
import model.session.operation.InsertNextEvent;
import model.session.operation.LoadGame;
import model.session.operation.StartGame;

public class TestEventProvider implements EventProvider {

    @Override
    public Event getEventById(int id) {
        var event0 = Event.builder()
                .setText("Добро пожаловать в город N! Последний мэр города не оправдал возложенных на него надежд." +
                        " Оправдает ли их новый мэр в Вашем лице?")
                .addButton(Button.builder()
                        .setName("Да")
                        .addIntent("да")
                        .addIntent("оправдает")
                        .addIntent("оправдаю")
                        .addIntent("конечно")
                        .addIntent("разумеется")
                        .addIntent("само собой")
                        .addOperation(new InsertNextEvent(1))
                        .build())
                .addButton(Button.builder()
                        .setName("Нет")
                        .addIntent("нет")
                        .addIntent("без шансов")
                        .addIntent("не оправдает")
                        .addIntent("не оправдаю")
                        .addIntent("вряд ли")
                        .addOperation(new EndSession())
                        .build())
                .build();

        var event1 = Event.builder()
                .setText("Если Вы не заканчивали ускоренный курс эффективного мэра, то можете сделать это прямо сейчас!" +
                        " Желаете пройти курс или Вы уже готовы вступить в должность мэра города N?")
                .addButton(Button.builder()
                        .setName("Пройти курс")
                        .addIntent("пройти курс")
                        .addIntent("желаю пройти курс")
                        .addIntent("обучение")
                        .addIntent("пройду курс")
                        .addOperation(new StartGame("tutorial"))
                        .build())
                .addButton(Button.builder()
                        .setName("Вступить в должность")
                        .addIntent("вступить в должность")
                        .addIntent("начать игру")
                        .addIntent("я готов")
                        .addIntent("готов")
                        .addOperation(new StartGame("default"))
                        .build())
                .build();

        var event2 = Event.builder()
                .setText("С возвращением, господин мэр! Мы всё ещё ждём Ваших решений по делам города." +
                        " Желаете вернуться к оставленным делам или воспользуетесь шансом начать всё заново?")
                .addButton(Button.builder()
                        .setName("Продолжить")
                        .addIntent("продолжить")
                        .addIntent("вернуться")
                        .addIntent("к оставленным делам")
                        .addIntent("загрузить")
                        .addIntent("дальше")
                        .addOperation(new LoadGame())
                        .build())
                .addButton(Button.builder()
                        .setName("Заново")
                        .addIntent("заново")
                        .addIntent("начать заново")
                        .addIntent("новая игра")
                        .addIntent("новый город")
                        .addOperation(new StartGame("default"))
                        .build())
                .build();

        switch (id) {
            case 0:
                return event0;
            case 1:
                return event1;
            case 2:
                return event2;
            default:
                return null;
        }
    }
}
