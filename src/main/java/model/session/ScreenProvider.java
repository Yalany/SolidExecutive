package model.session;

// TODO всегда лепить кнопки "какой месяц" и "сколько ресурсов"
public interface ScreenProvider {
    Screen getEventById(int id);
}
