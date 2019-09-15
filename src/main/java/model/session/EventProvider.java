package model.session;

// TODO всегда лепить кнопки "какой месяц" и "сколько ресурсов"
public interface EventProvider {
    Event getEventById(int id);
}
