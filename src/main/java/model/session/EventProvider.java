package model.session;

public interface EventProvider {
    Event getEventById(int id, Session context);
}
