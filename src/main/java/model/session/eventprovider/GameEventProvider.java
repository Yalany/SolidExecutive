package model.session.eventprovider;

import model.session.Event;
import model.session.EventProvider;
import model.session.Session;

import java.util.HashMap;

public class GameEventProvider implements EventProvider {

    private HashMap<Integer, Event> events = new HashMap<>();

    public Event getEventById(int id, Session forSession) {
        return null;
    }
}
