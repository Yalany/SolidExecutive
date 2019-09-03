package model.session;

import java.util.HashMap;

public class EventProvider {

    private HashMap<Integer, Event> events;

    // session used for resource validation, event won't have options for which player don't have resources
    Event getEventById(int id, Session forSession) {
        return null;
    }
}
